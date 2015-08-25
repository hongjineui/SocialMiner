package lab.u2xd.socialminer.facebook;

import android.provider.CallLog;
import android.view.View;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

/**
 * Created by ysb on 2015-08-25.
 */
public class LoginManager implements View.OnClickListener, FacebookCallback {

    final static private String[] CALL_PROJECTION = { CallLog.Calls.TYPE,
            CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER,
            CallLog.Calls.DATE,        CallLog.Calls.DURATION };

    public LoginManager() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException e) {

    }
}
