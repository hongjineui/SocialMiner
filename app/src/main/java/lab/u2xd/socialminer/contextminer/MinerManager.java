package lab.u2xd.socialminer.contextminer;

import android.content.Context;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.Telephony;
import android.util.Log;
import android.widget.TextView;

import java.util.Objects;

import lab.u2xd.socialminer.contextminer.callback.Queryable;

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

    private PhoneMiner minerCallLog;
    private PhoneMiner minerSMS;

    public MinerManager(Context context) {
        this.context = context;

        minerCallLog = new PhoneMiner(CallLog.Calls.CONTENT_URI, CALL_PROJECTION, PhoneMiner.DEFAULT_SORT_ORDER);
        minerSMS = new PhoneMiner(Uri.parse("content://sms"), SMS_PROJECTION, PhoneMiner.DEFAULT_SORT_ORDER);
        //Deprecated
        callLoader = new CallMiner();
        smsLoader = new SMSMiner();
    }

    public void queryAllData() {
        minerCallLog.queryRawData(context, this);
        minerSMS.queryRawData(context, this);
    }

    @Override
    public void receiveData(Object sender) {
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

    //Deprecated Elements
    private CallMiner callLoader;
    private SMSMiner smsLoader;

    public void queryCallList() {
        callLoader.readLog(context);
    }
    public void querySMSList() {
        smsLoader.readLog(context);
    }

    public String[] getCall_Elements(int index) {
        return callLoader.getQueriedResult(index);
    }
    public String[] getSMS_Elements(int index) {
        return smsLoader.getQueriedResult(index);
    }


    public int getReadCallCount() {
        return callLoader.getListCount();
    }
    public int getReadSMSCount() {
        return smsLoader.getListCount();
    }
}
