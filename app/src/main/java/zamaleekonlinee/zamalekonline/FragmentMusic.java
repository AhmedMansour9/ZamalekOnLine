package zamaleekonlinee.zamalekonline;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMusic extends Fragment {
    static  String Soong="soong";
    HashMap<String,String> hash=new HashMap<>();
   public ArrayList<HashMap<String,String>> arra=new ArrayList<HashMap<String, String>>();
    public static String linnKK="link";
    public ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
    HashMap<String, String> hm;
    private Button btnSimpleSnackbar;
    boolean isConnected;
    songsmanger songs;
    Adapterlistmusic arrayAdapter;

    public FragmentMusic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listviewmenumusic,
                container, false);



//        String[] songsname = getActivity().getResources().getStringArray(R.array.itemMusic);
//        String[] songlinks = getActivity().getResources().getStringArray(R.array.LinkSongs);
//        for (int i = 0; i < songlinks.length; i++) {
//            hm = newsssssss HashMap<String, String>();
//            hm.put(linnKK, songlinks[i]);
//
//            hm.put("txt", songsname[i]);
//
//            aList.add(hm);
//        }
        songs=new songsmanger(getActivity());
       arra = songs.getPlayList();
        for (int i = 0; i < arra.size(); i++) {
            // creating newsssssss HashMap
            HashMap<String, String> song = arra.get(i);

            // adding HashList to ArrayList
            aList.add(song);
        }


        ListView list = (ListView) rootView.findViewById(R.id.ListMusic);
        arrayAdapter = new Adapterlistmusic(this.getActivity(), R.layout.itemmenumusic, aList);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo actiNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (actiNetworkInfo != null) {

                    int songIndex = position;

                    Intent inty = new Intent(getActivity(), Songs.class);


//                    String contact = ((TextView) view.findViewById(R.id.text)).getText().toString();
                    String title = ((TextView) view.findViewById(R.id.TextMusic)).getText().toString();
                    SharedPreferences.Editor editor =getActivity(). getSharedPreferences("DATA",getActivity(). MODE_PRIVATE).edit();
                    editor.putInt("name", songIndex);
                    editor.putString("text",title);
                    editor.commit();


                    // Sending songIndex to PlayerActivity
                    inty.putExtra("songIndex", songIndex);
////                    inty.putExtra("songPath", contact);
////                    inty.putExtra("titlee", title);
//                    startActivityForResult(inty, 100);
                    inty.putExtra("songInde", songIndex);
                    startActivity(inty);

                } else if (actiNetworkInfo == null) {
//                    AlertDialog.Builder alertDialog = newsssssss AlertDialog.Builder(getActivity());
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
                    Snackbar.make(view, "No internet connection!", Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColorStateList(R.color.white))

                            .show();


                }

            }
        });




        return rootView;
    }




}
