package lab.u2xd.socialminer.context.miner;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

import lab.u2xd.socialminer.context.miner.callback.Queryable;

/**
 * Created by ysb on 2015-09-11.
 */
public class PhoneLogMiner {

    final public static String DEFAULT_SORT_ORDER = "date DESC";

    private String[] qeuryProjection;
    private Uri contentUri;
    private String sortOrder;

    private Context context;
    private Cursor curBasic;
    private ArrayList<String[]> listQueriedResult;

    private Queryable callback;

    public PhoneLogMiner(Uri ContentURI, String[] QeuryProjection, String SortOrder) {
        listQueriedResult = new ArrayList<String[]>();
        contentUri = ContentURI;
        qeuryProjection = QeuryProjection;
    }

    private void readLog() {
        curBasic = context.getContentResolver().query(contentUri, qeuryProjection, null, null, sortOrder);
        Log.e("PhoneLogMiner", "Data Reading Complete : " + contentUri.toString() + ", " + sortOrder);
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
        Log.e("PhoneLogMiner", "Data Reading...");
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
    public String[][] getQueriedResult() {
        String[][] temp = new String[listQueriedResult.size()][];
        for(int i = 0; i < listQueriedResult.size(); i++) {
            temp[i] = listQueriedResult.get(i);
        }
        return null;
    }
}
