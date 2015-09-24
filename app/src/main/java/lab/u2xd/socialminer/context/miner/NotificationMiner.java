package lab.u2xd.socialminer.context.miner;

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by ysb on 2015-09-23.
 */
public class NotificationMiner extends NotificationListenerService {

    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Notification Miner", "Service Created");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Log.e("Notification Miner","Notification Posted");
        Notification notis = sbn.getNotification();
        Log.e("Notification Miner", notis.extras.getString(Notification.EXTRA_TEXT));
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.e("Notification Miner", "Notification Removed");
    }
}
