package lab.u2xd.socialminer.contextminer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony.Sms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import lab.u2xd.socialminer.contextminer.callback.Contextminer;

/**
 * Created by yim on 2015-09-07.
 */
public class SMSMiner implements Contextminer, Runnable {

    final static private String[] SMS_PROJECTION
            = { Sms._ID, Sms.THREAD_ID, Sms.ADDRESS, Sms.DATE, Sms.DATE_SENT, Sms.BODY };
    //final static private String[] SMS_PROJECTION = { "*" };

    private Uri smsPoint;
    private ContentResolver smsLoader;

    private Cursor curBasicSMS;
    private int iSMSCount = 0;
    private ArrayList<String[]> listQueriedResult;

    public SMSMiner() {
        smsPoint = Uri.parse("content://sms");
        listQueriedResult = new ArrayList<String[]>();
    }

    @Override
    public void readLog(Context context) {
        smsLoader = context.getContentResolver();
        curBasicSMS = smsLoader.query(smsPoint, SMS_PROJECTION, null, null, Sms.DEFAULT_SORT_ORDER);
        iSMSCount = curBasicSMS.getCount();
        listQueriedResult.clear();

        if(curBasicSMS.moveToFirst()) {
            for(int j = 0; j < curBasicSMS.getCount(); j++) {
                String[] temp = new String[SMS_PROJECTION.length];

                for (int i = 0; i < SMS_PROJECTION.length; i++) {
                    if(i == 3 || i == 4) {
                        Date date = new Date(curBasicSMS.getLong(i));
                        temp[i] = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
                    } else {
                        temp[i] = curBasicSMS.getString(i);
                    }
                }

                listQueriedResult.add(temp);
                curBasicSMS.moveToNext();
            }
        }
    }

    @Override
    public int getListCount() {
        return iSMSCount;
    }

    @Override
    public String[] getQueriedResult(int index) {
        if(iSMSCount == 0)
            throw new NullPointerException();   //추후 사용자지정 에러 처리할 것

        return listQueriedResult.get(index);
    }

    @Override
    public void run() {

    }
}
