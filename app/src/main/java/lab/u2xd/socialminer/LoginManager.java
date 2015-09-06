package lab.u2xd.socialminer;

import android.provider.CallLog;
import android.view.View;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

/**
 * 로그인 작업 처리 매니저
 * Created by ysb on 2015-08-25.
 */
public class LoginManager implements View.OnClickListener, FacebookCallback {

    public LoginManager() {

    }

    @Override
    public void onClick(View v) {

    }

    /** 페이스북 로그인 성공 시 불리는 CallBack 함수
     *
     * @param o
     */
    @Override
    public void onSuccess(Object o) {

    }

    /** 페이스북 로그인 취소 시 불리는 CallBack 함수
     *
     */
    @Override
    public void onCancel() {

    }

    /** 페이스북 로그인 실패 시 불리는 CallBack 함수
     *
     * @param e
     */
    @Override
    public void onError(FacebookException e) {

    }
}
