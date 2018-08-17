package zamaleekonlinee.zamalekonline;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 08/01/2017.
 */
public class songsmanger extends Activity {
    public ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    Context con;
    public songsmanger(Context context){
     this.con=context;
    }
    public ArrayList<HashMap<String, String>> getPlayList(){
        String[] songsnam =con.getResources().getStringArray(R.array.itemMusic);
        String[] songlin =con.getResources().getStringArray(R.array.LinkSongs);

        for (int i = 0; i < songlin.length; i++) {
            HashMap<String, String> son = new HashMap<String, String>();
            son.put("link", songlin[i]);
            son.put("txt", songsnam[i]);


            // Adding each song to SongList
            songsList.add(son);

        }
        // return songs list array
        return songsList;
    }

}
