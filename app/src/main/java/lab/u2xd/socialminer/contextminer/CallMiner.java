package lab.u2xd.socialminer.contextminer;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import lab.u2xd.socialminer.contextminer.minerobject.Contextminer;

/**
 * Created by yim on 2015-08-31.
 */
public class CallMiner implements Contextminer {

    final static private String[] CALL_PROJECTION
            = { CallLog.Calls.TYPE, CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER,
                CallLog.Calls.DATE, CallLog.Calls.DURATION };

    private Cursor curBasicCall;
    private int iCallCount = 0;
    private ArrayList<String[]> listQueriedResult;

    public CallMiner() {
        listQueriedResult = new ArrayList<String[]>();
    }

    @Override
    public void readLog(Context context) {
        curBasicCall = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, CALL_PROJECTION, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        iCallCount = curBasicCall.getCount();
        listQueriedResult.clear();

        if(curBasicCall.moveToFirst()) {
            for(int j = 0; j < curBasicCall.getCount(); j++) {
                String[] temp = new String[CALL_PROJECTION.length];

                for (int i = 0; i < CALL_PROJECTION.length; i++) {
                    if(i == 3) {
                        Date date = new Date(curBasicCall.getLong(i));
                        temp[i] = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
                    } else {
                        temp[i] = curBasicCall.getString(i);
                    }
                }
                listQueriedResult.add(temp);
                curBasicCall.moveToNext();
            }
        }
    }

    @Override
    public int getListCount() {
        return iCallCount;
    }

    public String[] getQueriedResult(int index) {
        if(iCallCount == 0)
            throw new NullPointerException();   //추후 사용자지정 에러 처리할 것

        return listQueriedResult.get(index);
    }
}
