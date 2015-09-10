package lab.u2xd.socialminer.contextminer;

import android.content.Context;

/**
 * Created by yim on 2015-08-31.
 */
public class MinerManager {

    private CallMiner callLoader;
    private SMSMiner smsLoader;

    private Context context;

    public MinerManager(Context context) {
        this.context = context;

        callLoader = new CallMiner();
        smsLoader = new SMSMiner();
    }

    public void queryCallList() {
        callLoader.readLog(context);
    }

    public void querySMSList() {
        smsLoader.readLog(context);
    }

    public String[] readCall(int index) {
        return callLoader.getQueriedResult(index);
    }
    public String[] readSMS(int index) {
        return smsLoader.getQueriedResult(index);
    }


    public int getReadCallCount() {
        return callLoader.getListCount();
    }
    public int getReadSMSCount() {
        return smsLoader.getListCount();
    }
}
