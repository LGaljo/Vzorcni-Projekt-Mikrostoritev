package si.lg.vzorcniprojekt;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

public class DataClass extends JobService {
    private static final String TAG = "MainActivity";

    public static void scheduleJob(Context context) {
        ComponentName serviceComponentQuestions = new ComponentName(context, GetQuestionsJobService.class);

        JobInfo infoQs = new JobInfo.Builder(0, serviceComponentQuestions)
            .setMinimumLatency(1 * 1000)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setOverrideDeadline(4 * 1000)
            .setPersisted(true)
            .build();

        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int resultCodeQs = jobScheduler.schedule(infoQs);
        
        if (resultCodeQs == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job 0 scheduled");
        } else {
            Log.d(TAG, "Job 0 failed");
        }

    }

    public static void cancelJob(Context context) {
        JobScheduler scheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(0);
        Log.d(TAG, "Job canceled");
    }

    @Override
    public boolean onStartJob(final JobParameters params) {
        return true;
    }

    @Override
    public boolean onStopJob(final JobParameters params) {
        return true;
    }
}
