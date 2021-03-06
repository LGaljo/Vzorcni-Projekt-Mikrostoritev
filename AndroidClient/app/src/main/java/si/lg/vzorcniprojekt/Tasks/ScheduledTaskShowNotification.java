package si.lg.vzorcniprojekt.Tasks;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import si.lg.vzorcniprojekt.Objects.Question;
import si.lg.vzorcniprojekt.Objects.SaveObj;
import si.lg.vzorcniprojekt.R;
import si.lg.vzorcniprojekt.ResponseActivity;

public class ScheduledTaskShowNotification implements Runnable {
    private static final String TAG = "ScheduledTaskShowNotification";
    private Context context;
    private String channelId = "my_channel";
    private String channelName = "Channel Name";

    public ScheduledTaskShowNotification(Context context) {
        this.context = context;
    }

    public void run() {
        Log.d(TAG, "Prikaži obvestila");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int q = (int)(Math.random() * SaveObj.questions.size());

        Question question = SaveObj.questions.get(q);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(question.question)
                .setContentText("Za odgovor se dotaknite obvestila")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        int notificationId = 1;
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        Intent intent = new Intent(context, ResponseActivity.class);
        intent.putExtra("question", question.question);
        intent.putExtra("question_id", question.id);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }
}
