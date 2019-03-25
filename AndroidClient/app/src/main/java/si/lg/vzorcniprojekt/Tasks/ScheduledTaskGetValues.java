package si.lg.vzorcniprojekt.Tasks;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;

import si.lg.vzorcniprojekt.Activities.MainActivity;
import si.lg.vzorcniprojekt.VolleyTool;

import static si.lg.vzorcniprojekt.Objects.SaveObj.baseAPIURL;
import static si.lg.vzorcniprojekt.Objects.SaveObj.questions;

public class ScheduledTaskGetValues implements Runnable {
    private static final String TAG = "ScheduledTaskGetValues";
    private Context context;

    public ScheduledTaskGetValues (Context context) {
        this.context = context;
    }

    public void run() {
        for (int i = 0; i < questions.size(); i++) {
            String url = baseAPIURL + "value/" + questions.get(i).id;
            Log.d(TAG, url);

            VolleyTool vt = new VolleyTool(context, url);

            vt.executeRequest(Request.Method.GET, new VolleyTool.VolleyCallback() {

                @Override
                public void getResponse(String response) {
                    if (response != null) {
                        try {
                            Log.d(TAG, "vprasanje: " + response);
                            MainActivity.box.append("Vprasanje: " + response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
