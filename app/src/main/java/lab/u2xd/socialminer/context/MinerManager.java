package lab.u2xd.socialminer.context;

import android.content.Context;

/**
 * Created by yim on 2015-08-31.
 */
public class MinerManager {

    private Context context;
    private CallLoader callLoader;

    public MinerManager(Context contex) {
        this.context = contex;
        callLoader = new CallLoader();
    }

    public void readCallList() {
        callLoader.readLog(context);
    }

    public String[] readCall(int index) {
        return callLoader.getQueriedResult(index);
    }

    public int getReadCallCount() {
        return callLoader.getCallListCount();
    }
}
