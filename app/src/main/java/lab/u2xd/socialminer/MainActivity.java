package lab.u2xd.socialminer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;

import lab.u2xd.socialminer.context.miner.MinerManager;

public class MainActivity extends AppCompatActivity {

    private CallbackManager fbCallbackManager;

    private MasterManager master;

    private ProgressBar progressBar;
    private LoginButton btnFacebook;
    private Button btnLogin;
    private TextView txtConsole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Application 초기화
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        //주요 객체 초기화
        fbCallbackManager = CallbackManager.Factory.create();
        master = new MasterManager(this);

        //UI initializing
        btnLogin = (Button) findViewById(R.id.button_login);
        btnLogin.setOnClickListener(master);
        btnFacebook = (LoginButton) findViewById(R.id.button_login_facebook);
        btnFacebook.registerCallback(fbCallbackManager, master);
        txtConsole = (TextView) findViewById(R.id.txtResult);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
