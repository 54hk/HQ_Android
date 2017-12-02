package project.hq.com.hq_android.ui.activity;

import android.content.Intent;

import project.hq.com.hq_android.R;
import project.hq.com.hq_android.ui.base.BaseActivity;
/**
 * Created by Json on 2017/12/2.
 */
public class SplashActivity extends BaseActivity {

    @Override
    public int initLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initData() {
        threadIntent();
    }

    @Override
    protected boolean hodeWindow() {
        return true;
    }


    private void threadIntent() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent it = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(it);
                            finish();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
