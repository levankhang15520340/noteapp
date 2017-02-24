package uitstart.uit.noteapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;


/**
 * Created by Khang on 2/24/2017.
 */
public class BroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Note n= (Note) intent.getSerializableExtra("note");

        Intent showInfoIntent=new Intent(context,ShowInfoActivity.class);
        showInfoIntent.putExtra("note",intent.getSerializableExtra("note"));
        PendingIntent pendingIntentShowInfo=PendingIntent.getActivity(context,n.getId(),showInfoIntent,PendingIntent.FLAG_ONE_SHOT);

        //Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Uri uri=Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.sound);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        builder.setContentTitle("Bạn có một sự kiện vào lúc này");
        builder.setContentText("Nhấn vào thông báo này để xem chi tiết");
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setContentIntent(pendingIntentShowInfo);
        builder.setSound(uri);
        builder.setAutoCancel(true);

        NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(n.getId(),builder.build());
    }
}