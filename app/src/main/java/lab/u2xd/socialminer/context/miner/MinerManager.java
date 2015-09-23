package lab.u2xd.socialminer.context.miner;

import android.content.Context;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.Telephony;
import android.util.Log;

import lab.u2xd.socialminer.context.miner.callback.Queryable;

/**
 * Created by yim on 2015-08-31.
 *
 * MinerManager는 요청을 받거나 필요한 경우 Miner들에게 데이터를 받고
 * 그 데이터를 converter에게 넘겨 context 데이터로 일원화 시킨 후
 * ContextManager에게 전달한다.
 */
public class MinerManager implements Queryable {

    final static private String[] CALL_PROJECTION
            = { CallLog.Calls.TYPE, CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION };
    final static private String[] SMS_PROJECTION
            = { Telephony.Sms._ID, Telephony.Sms.THREAD_ID, Telephony.Sms.ADDRESS, Telephony.Sms.DATE, Telephony.Sms.DATE_SENT, Telephony.Sms.BODY };

    private Context context;

    private PhoneLogMiner minerCallLog;
    private PhoneLogMiner minerSMS;
    private NotificationMiner minerNotification;

    private ElementConverter converter;

    public MinerManager(Context context, ElementConverter converter) {
        this.context = context;

        minerCallLog = new PhoneLogMiner(CallLog.Calls.CONTENT_URI, CALL_PROJECTION, PhoneLogMiner.DEFAULT_SORT_ORDER);
        minerSMS = new PhoneLogMiner(Uri.parse("content://sms"), SMS_PROJECTION, PhoneLogMiner.DEFAULT_SORT_ORDER);
        minerNotification = new NotificationMiner();

        this.converter = converter;
    }

    public void queryCallData() {
        minerCallLog.queryRawData(context, this);
    }

    public void querySMSData() {
        minerSMS.queryRawData(context, this);
    }

    public void queryAllData() {
        minerCallLog.queryRawData(context, this);
        minerSMS.queryRawData(context, this);
    }

    @Override
    public void receiveData(Object sender) {
        if(sender.equals(minerCallLog)) {

        } else if(sender.equals(minerSMS)) {

        } else {
            Log.e("Miner Corp.", "Unknown Sender!");
        }
    }


    //-----------------//
    @Deprecated
    private void testshowReceivedData(Object sender) {
        String console = "";
        Log.e("MinerManager", "Data Writed!");
        if(sender.equals(minerSMS)) {
            //for (int i = 0; i < minerSMS.getQueriedDataCount(); i++) {
            for (int i = 0; i < 2; i++) {
                String[] temp = minerSMS.getQueriedResult(i);
                console += "[" + i + "] ";
                for (int j = 0; j < temp.length; j++) {
                    console += temp[j] + ", ";
                    if (j == temp.length - 1) {
                        console += "\r\n";
                    }
                }
                Log.e("Minermanager", console);
            }
        }
    }
}
