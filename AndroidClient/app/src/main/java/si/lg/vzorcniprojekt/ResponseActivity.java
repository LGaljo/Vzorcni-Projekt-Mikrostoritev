package si.lg.vzorcniprojekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import si.lg.vzorcniprojekt.Objects.Question;
import si.lg.vzorcniprojekt.Objects.SaveObj;

import static si.lg.vzorcniprojekt.Objects.SaveObj.questions;

public class ResponseActivity extends AppCompatActivity {
    private Button send;
    private EditText mresponse;
    private Integer value;
    private Question question;
    private TextView question_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if (b != null) {
            String str = (String)b.get("question");
            for (Question q : questions) {
                if (q.question.equals(str)) {
                    question = q;
                    break;
                }
            }
        }

        send = (Button) findViewById(R.id.send_response);
        mresponse = (EditText) findViewById(R.id.response);
        question_box = (TextView) findViewById(R.id.question_title);

        question_box.setText(question.question);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = Integer.parseInt(mresponse.getText().toString());
                Log.d("TAG", "User entered: " + value);
                if (value != null && value >= 0 && value <= 5) {
                    sendReponse(question);
                    finish();
                } else {
                    Toast.makeText(ResponseActivity.this, "Enter value between 0 and 5", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendReponse(Question question) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = SaveObj.baseAPIURL + "vprasanje/" + question.id + "/" + value;

        Log.d("SendResponse ", "POST: " + url);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("Content-Type", "application/json");

                return params;
            }
        };
        queue.add(postRequest);
    }
}
