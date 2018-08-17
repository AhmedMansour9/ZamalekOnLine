package zamaleekonlinee.zamalekonline;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HP on 24/12/2016.
 */
public class Adapterlistmusic extends BaseAdapter {
    TextView textmusic;
    Activity activity;
   String [] songoss;
    TextView textlinks;
    LayoutInflater inflater;
    List<HashMap<String, String>> arraa= new ArrayList<HashMap<String,String>>();

    HashMap<String,String> hashMa=new HashMap<String, String>();


    public Adapterlistmusic(Activity activity, int itemmenumusic, List<HashMap<String, String>> arr){
        this.activity =activity;

       this.arraa=arr;

    }
    @Override
    public int getCount() {
        return arraa.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater=(LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.itemmenumusic, null);
        hashMa=arraa.get(position);
        String [] songsname=v.getResources().getStringArray(R.array.itemMusic);
        textlinks=(TextView)v.findViewById(R.id.text);

        textmusic=(TextView)v.findViewById(R.id.TextMusic);
//        textlinks.setText(hashMa.get("link"));
        textmusic.setText(hashMa.get("txt"));





        return v;
    }
}
