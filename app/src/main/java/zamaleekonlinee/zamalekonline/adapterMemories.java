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
 * Created by HP on 06/01/2017.
 */
public class adapterMemories  extends BaseAdapter  {
    TextView textcode;
    Activity activity;
    String [] songoss;
    TextView textmomry;
    LayoutInflater inflater;

    List<HashMap<String, String>> arra= new ArrayList<HashMap<String,String>>();

    HashMap<String,String> hashMa=new HashMap<String, String>();
    List<HashMap<String, String>> arrayfilter=new ArrayList<>();

    public adapterMemories(Activity activity, int itemmenumusic, List<HashMap<String, String>> arr){
        this.activity =activity;
      this.arrayfilter=arr;
        this.arra=arr;

    }


    @Override
    public int getCount() {
        return arra.size();
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
        View vv=inflater.inflate(R.layout.itemmemories, null);
        hashMa=arra.get(position);

        textmomry=(TextView)vv.findViewById(R.id.TexTMemorieS);

        textcode=(TextView)vv.findViewById(R.id.textcodee);

        textmomry.setText(hashMa.get("textMomries"));
        textcode.setText(hashMa.get("CodeYoutube"));





        return vv;
    }



}
