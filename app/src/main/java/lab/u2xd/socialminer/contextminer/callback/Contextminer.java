package lab.u2xd.socialminer.contextminer.callback;

import android.content.Context;

/**
 * Created by yim on 2015-09-07.
 */
public interface Contextminer {
    void readLog(Context context);
    int getListCount();
    String[] getQueriedResult(int index);
}
