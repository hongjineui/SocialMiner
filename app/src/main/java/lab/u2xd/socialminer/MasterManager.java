package lab.u2xd.socialminer;

import android.content.Context;
import android.view.View;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import lab.u2xd.socialminer.context.miner.ElementConverter;
import lab.u2xd.socialminer.context.miner.MinerManager;
import lab.u2xd.socialminer.context.processor.processManager;

/**
 * Created by ysb on 2015-09-11.
 *
 * 사용자의 요청이나 기타 필요한 경우에 따라 Logining, Mining, Converting, Processing 네 단계를 관리한다.
 */
public class MasterManager implements View.OnClickListener, FacebookCallback {

    private LoginManager loginManager;
    private MinerManager minerManager;
    private processManager processManager;
    private ElementConverter converter;

    public MasterManager(Context context) {
        loginManager = new LoginManager();
        converter = new ElementConverter();
        minerManager = new MinerManager(context, converter);
        processManager = new processManager(converter);
    }

    @Override
    public void onClick(View v) {
        minerManager.queryAllData();
    }


    //페이스 북 로그인 요청을 LoginManager에 넘김 관리
    @Override
    public void onSuccess(Object o) {
        loginManager.onSuccess(o);
    }

    @Override
    public void onCancel() {
        loginManager.onCancel();
    }

    @Override
    public void onError(FacebookException e) {
        loginManager.onError(e);
    }
}
