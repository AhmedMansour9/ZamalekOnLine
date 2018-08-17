package zamaleekonlinee.zamalekonline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by HP on 21/12/2016.
 */
public class SpLash_Screen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Thread timer=new Thread(){
            @Override
            public void run() {
                super.run();
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                finally {
                Intent intMainActivity=new Intent(SpLash_Screen.this,MainActivity.class);
                startActivity(intMainActivity);
            }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
