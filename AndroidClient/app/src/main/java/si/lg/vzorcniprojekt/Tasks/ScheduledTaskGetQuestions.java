package si.lg.vzorcniprojekt.Tasks;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import si.lg.vzorcniprojekt.Activities.MainActivity;
import si.lg.vzorcniprojekt.Objects.Question;
import si.lg.vzorcniprojekt.Objects.SaveObj;
import si.lg.vzorcniprojekt.VolleyTool;

import static si.lg.vzorcniprojekt.Objects.SaveObj.baseAPIURL;
import static si.lg.vzorcniprojekt.Objects.SaveObj.questions;
import static si.lg.vzorcniprojekt.Objects.SaveObj.tags;

public class ScheduledTaskGetQuestions implements Runnable {
    private static final String TAG = "ScheduledTaskGetQuestions";
    private Context context;

    public ScheduledTaskGetQuestions(Context context) {
        this.context = context;
    }

    public void run() {
        // pridobi listo vprasanj in postavi obvestilo

        MainActivity.box.clearComposingText();

        for (int i = 0; i < tags.size(); i++) {
            String url = SaveObj.baseAPIURL + "vprasanje/" + tags.get(i).id;
            Log.d(TAG, url);
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
                                question.tag = tags.get(i);

                                SaveObj.questions.add(question);

                                MainActivity.box.append(question.question + "\n");

                                new ScheduledTaskGetValues(context).run();
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
