package lab.u2xd.socialminer.contextminer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import lab.u2xd.socialminer.contextminer.callback.Queryable;

/**
 * Created by ysb on 2015-09-11.
 */
public class PhoneMiner {

    final public static String DEFAULT_SORT_ORDER = "date DESC";

    private String[] qeuryProjection;
    private Uri contentUri;
    private String sortOrder;

    private Context context;
    private Cursor curBasic;
    private ArrayList<String[]> listQueriedResult;

    private Queryable callback;

    public PhoneMiner(Uri ContentURI, String[] QeuryProjection, String SortOrder) {
        listQueriedResult = new ArrayList<String[]>();
        contentUri = ContentURI;
        qeuryProjection = QeuryProjection;
    }

    private void readLog() {
        curBasic = context.getContentResolver().query(contentUri, qeuryProjection, null, null, sortOrder);
        Log.e("PhoneMiner", "Data Reading Complete : " + contentUri.toString() + ", " + sortOrder);
        listQueriedResult.clear();

        if(curBasic.moveToFirst()) {
            for(int j = 0; j < curBasic.getCount(); j++) {
                String[] temp = new String[qeuryProjection.length];

                for (int i = 0; i < qeuryProjection.length; i++) {
                    temp[i] = curBasic.getString(i);
                }
                listQueriedResult.add(temp);
                curBasic.moveToNext();
            }
        }
        callback.receiveData(this);
    }

    public void queryRawData(Context context, Queryable callback) {
        Log.e("PhoneMiner", "Data Reading...");
        this.context = context;
        Thread worker = new Thread(new Runnable() {
            @Override
            public void run() {
                readLog();
            }
        });
        worker.start();
        this.callback = callback;
    }

    public int getQueriedDataCount() {
        return listQueriedResult.size();
    }

    public String[] getQueriedResult(int index) {
        return listQueriedResult.get(index);
    }
}
