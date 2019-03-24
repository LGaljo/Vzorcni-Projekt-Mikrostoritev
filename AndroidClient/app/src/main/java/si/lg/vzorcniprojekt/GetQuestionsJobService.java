package si.lg.vzorcniprojekt;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import si.lg.vzorcniprojekt.Objects.SaveObj;
import si.lg.vzorcniprojekt.Tasks.ScheduledTaskGetQuestions;

public class GetQuestionsJobService extends JobService {
    private static final String TAG = "GetQuestionsJobService";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        task(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job stopped");
        return false;
    }

    private void task(JobParameters params) {
        if (SaveObj.tags != null) {
            ScheduledTaskGetQuestions scheduledTaskGetQuestions = new ScheduledTaskGetQuestions(getApplicationContext());
            Thread t = new Thread(scheduledTaskGetQuestions);
            t.start();
            jobFinished(params, false);
        }
    }
}