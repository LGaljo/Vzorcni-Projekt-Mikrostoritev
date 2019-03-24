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
        ComponentName serviceComponent = new ComponentName(context, GetQuestionsJobService.class);

        JobInfo info = new JobInfo.Builder(0, serviceComponent)
            .setMinimumLatency(10 * 1000)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setOverrideDeadline(40 * 1000)
            .setPersisted(true)
            .build();
        
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(info);
        
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job failed");
        }
    }

    public void cancelJob() {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
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
