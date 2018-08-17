package zamaleekonlinee.zamalekonline;


import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Songs extends AppCompatActivity implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {
     ArrayList<HashMap<String, String>> songsL = new ArrayList<HashMap<String, String>>();

    FragmentMusic fragm;
    private ImageButton btnPlay;
    public ImageButton btnForward;
    private ImageButton btnBackward;
    private ImageButton btnNext;
    private ImageButton btnPrevious;
    private static final String TAG = null;
    int position;
    private TextView texttitle;
    HashMap<String, String> hmmn;
    private int progressStatus = 0;
     SeekBar songProgressBar;
    private TextView songTitleLabel;
    private TextView songCurrentDurationLabel;
    private TextView songTotalDurationLabel;
    // Media Player
    HashMap<String,String>has=new HashMap<String, String>();
    MediaPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();
   Notification.Builder noti;
    Intent playtintent;
    HashMap<String,String> hashMa=new HashMap<String, String>();
    private Handler handler = new Handler();
    private Utilities utils;
    private int seekForwardTime = 5000; // 5000 milliseconds
    private int seekBackwardTime = 5000; // 5000 milliseconds
    private int currentSongIndex=0;

    private ImageButton btnpause;
    ProgressDialog mProgressDialog;

    songsmanger songs;
    int idd;

    HashMap<String ,String> hmm;
    private boolean isRepeat = false;
    private ArrayList<HashMap<String, String>> songsListt = new
            ArrayList<HashMap<String, String>>();
    String [] songslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewsong);
        utils = new Utilities();
        mp = new MediaPlayer();
       songs=new songsmanger(this);



        Intent iin= getIntent();
        Bundle b = iin.getExtras();
//        String title=b.getString("titlee");
        idd=b.getInt("songInde");


        songProgressBar = (SeekBar) findViewById(R.id.seeek);
        songTitleLabel = (TextView) findViewById(R.id.textsog);
        songProgressBar.setOnSeekBarChangeListener(this); // Important
        btnForward = (ImageButton) findViewById(R.id.forward);
        btnBackward = (ImageButton) findViewById(R.id.backword);
//
        btnPlay = (ImageButton) findViewById(R.id.playyy);
        btnBackward = (ImageButton) findViewById(R.id.backword);
        btnNext = (ImageButton) findViewById(R.id.Next);
        btnPrevious = (ImageButton) findViewById(R.id.previous);
        texttitle = (TextView) findViewById(R.id.textsog);
        songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
        songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);
        Toast.makeText(getApplication(),"Play Song and wait while loading ,,",Toast.LENGTH_LONG).show();


//        songTitleLabel.setText(title);
        mp.setOnCompletionListener(this); // Important
//        SharedPreferences prefs = getSharedPreferences("DATA", MODE_PRIVATE);
//        idd = prefs.getInt("ID", 0);

        songsL=songs.getPlayList();

         mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
             @Override
             public boolean onError(MediaPlayer mp, int what, int extra) {
                 onErrorr(mp,what,extra);

                 return true;
             }
         });


        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // check for already playing

                if(mp.isPlaying()){
                    if(mp!=null){
                        mp.pause();
                        // Changing button image to play button

                        btnPlay.setImageResource(android.R.drawable.ic_media_play);
                        btnPlay.setBackground(getResources().getDrawable(R.color.red));
                    }
                }else{
                    // Resume song
                    if(mp!=null){

                        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo actiNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        if (actiNetworkInfo != null) {


                            btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                            btnPlay.setBackground(getResources().getDrawable(R.color.red));
                            playSong(idd);


                    } else if (actiNetworkInfo == null) {

                        Snackbar.make(arg0, "No internet connection!", Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColorStateList(R.color.white))

                                .show();



                    }


                        // Changing button image to pause button


                    }
                }

            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo actiNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (actiNetworkInfo != null) {
                    btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                    btnPlay.setBackground(getResources().getDrawable(R.color.red));
                if (idd > 0) {
                    playSong(idd - 1);
                    idd = idd - 1;
                } else {
                    // play last song
                    playSong(songsL.size() - 1);
                    idd = songsL.size() - 1;
                }
                } else if (actiNetworkInfo == null) {
//                    AlertDialog.Builder alertDialog = newsssssss AlertDialog.Builder(Songs.this);
//
//                    // Setting Dialog Title
//                    alertDialog.setTitle("Sorry");
//
//                    // Setting Dialog Message
//                    alertDialog.setMessage("You Should Connect Network First");
//
//                    // On pressing Settings button
//
//
//                    alertDialog.show();
                    Snackbar.make(arg0, "No internet connection!", Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColorStateList(R.color.white))

                            .show();



                }
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // get current song position
                int currentPosition = mp.getCurrentPosition();
                // check if seekForward time is lesser than song duration
                if (currentPosition + seekForwardTime <= mp.getDuration()) {
                    // forward song
                    mp.seekTo(currentPosition + seekForwardTime);
                } else {
                    // forward to end position
                    mp.seekTo(mp.getDuration());
                }
            }
        });

        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // get current song position
                int currentPosition = mp.getCurrentPosition();
                // check if seekBackward time is greater than 0 sec
                if (currentPosition - seekBackwardTime >= 0) {
                    // forward song
                    mp.seekTo(currentPosition - seekBackwardTime);
                } else {
                    // backward to starting position
                    mp.seekTo(0);
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // check if next song is there or not
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo actiNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (actiNetworkInfo != null) {
                    btnPlay.setImageResource(android.R.drawable.ic_media_pause);
                    btnPlay.setBackground(getResources().getDrawable(R.color.red));


                    if (idd < (songsL.size() - 1)) {
                        playSong(idd + 1);
                        idd = idd + 1;

                    } else {
                        // play first song
                        playSong(0);
                        idd = 0;

                    }

                } else if (actiNetworkInfo == null) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Songs.this);

                    // Setting Dialog Title
                    alertDialog.setTitle("Sorry");

                    // Setting Dialog Message
                    alertDialog.setMessage("You Should Connect Network First");

                    // On pressing Settings button


                    alertDialog.show();


                }
            }
        });
        }




    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromTouch) {


    }
    public boolean onErrorr(MediaPlayer mp, int what, int extra) {


//        }
        return false;
    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if(mp.isPlaying()){
//            mp.pause();
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      mp.stop();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mp.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }


//
//
//    }
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mp.stop();
//
//
//    }


    /**
         * On Song Playing completed
         * if repeat is ON play same song again
         * if shuffle is ON play random song
         */


    @Override
    public void onCompletion(MediaPlayer arg0) {
        // check for repeat is ON or OFF
//        if(isRepeat){
//            // repeat is on play same song again
//            playSong(currentSongIndex);
//        } else if(isShuffle){
//            // shuffle is on - play a random song
//            Random rand = newsssssss Random();
//            currentSongIndex = rand.nextInt((songsList.size() - 1) - 0 + 1) + 0;
//            playSong(currentSongIndex);
//        } else{
//            // no repeat or shuffle ON - play next song

//        btnPlay.setImageResource(android.R.drawable.ic_media_pause);
//        btnPlay.setBackground(getResources().getDrawable(R.color.red));

            if (idd < (songsL.size() - 1)) {

                playSong(idd + 1);
                idd = idd + 1;

            } else {
                // play first song
                playSong(0);
                idd = 0;

        }

    }



/**
 * Update timer on seekbar
 */
    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }
    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mp.getDuration();
            long currentDuration = mp.getCurrentPosition();
            // Displaying Total Duration time
            songTotalDurationLabel.setText("" + utils.milliSecondsToTimer(
                    totalDuration));
            // Displaying time completed playing
            songCurrentDurationLabel.setText(
                    "" + utils.milliSecondsToTimer(currentDuration));
            // Updating progress bar
            int progress =
                    (int) (utils.getProgressPercentage(currentDuration, totalDuration));
            //Log.d("Progress", ""+progress);
            songProgressBar.setProgress(progress);
            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };


/**
 * Back button click event
 * Plays previous song by currentSongIndex - 1
 * */




/**
 * Receiving song index from playlist view
 * and play the song
 * */
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100){
            currentSongIndex = data.getExtras().getInt("songIndex");
            // play selected song
            playSong(currentSongIndex);
        }
    }


    /**
     * Function to play a song
     * //         * @param songIndex - index of song
     */


    public void  playSong(int songIndex){


        // Play song
        try {
//            Intent iin= getIntent();
//            Bundle b = iin.getExtras();


            mp.reset();
            mp.setDataSource(songsL.get(songIndex).get("link"));
            mp.prepare();
           mp.start();
          noti=new Notification.Builder(getApplicationContext());
            String songTitle = songsL.get(songIndex).get("txt");
            songTitleLabel.setText(songTitle);

            // set Progress bar values
            songProgressBar.setProgress(0);
            songProgressBar.setMax(100);

            // Updating progress bar
            updateProgressBar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}