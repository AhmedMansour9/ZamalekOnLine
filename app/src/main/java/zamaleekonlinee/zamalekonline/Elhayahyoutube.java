package zamaleekonlinee.zamalekonline;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by HP on 06/01/2017.
 */
public class Elhayahyoutube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    static    String DEVELOPER_KEY="AIzaSyAiHRah9SmXG5Kyb7qeNauQyi-4XQ8HT50";
    private YouTubePlayerView youTubePlayer;
    private static String YOUTUBE_VIDEO_CODE="5kIJYrB7saU";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.elhayahyoutube);

        youTubePlayer = (YouTubePlayerView) findViewById(R.id.IDelhayah);
        youTubePlayer.initialize(OnsportYoutube.DEVELOPER_KEY, this);


    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            youTubePlayer.loadVideo(Elhayahyoutube.YOUTUBE_VIDEO_CODE);
            youTubePlayer.cueVideo(Elhayahyoutube.YOUTUBE_VIDEO_CODE);

            // Hiding player controls
//            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }

    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {

            Toast.makeText(this, "sory", Toast.LENGTH_LONG).show();
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            youTubePlayer.initialize(Elhayahyoutube.DEVELOPER_KEY, this);
        }
    }
    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.IDonsport);
    }
}
