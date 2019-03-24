package si.lg.vzorcniprojekt.Tasks;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import si.lg.vzorcniprojekt.Objects.Question;
import si.lg.vzorcniprojekt.Objects.SaveObj;
import si.lg.vzorcniprojekt.VolleyTool;

public class ScheduledTaskGetQuestions implements Runnable {
    private static final String TAG = "ScheduledTaskGetQuestions";
    private Context context;

    public ScheduledTaskGetQuestions(Context context) {
        this.context = context;
    }

    public void run() {
        // pridobi listo vprasanj in postavi obvestilo

        for (int i = 0; i < SaveObj.tags.size(); i++) {
            String url = "http://192.168.1.153:8080/v1/api/vprasanje/" + SaveObj.tags.get(i).id;
            Log.d(TAG, "run with url: " + url);
            VolleyTool vt = new VolleyTool(context, url);

            vt.executeRequest(Request.Method.GET, new VolleyTool.VolleyCallback() {

                @Override
                public void getResponse(String response) {
                    if (response != null) {
                        try {
                            Log.d(TAG, "getResponse: " + response);
                            JSONArray JSONresponse = new JSONArray(response);
                            for (int i = 0; i < JSONresponse.length(); i++) {
                                JSONObject item = JSONresponse.getJSONObject(i);

                                Question question = new Question();
                                question.id = item.getInt("id");
                                question.question = item.getString("question");
                                question.tag = SaveObj.tags.get(i);

                                SaveObj.questions.add(question);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
