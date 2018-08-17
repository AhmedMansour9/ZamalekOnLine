package zamaleekonlinee.zamalekonline;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HP on 14/05/2017.
 */
public class adapternews  extends BaseAdapter {
    TextView textmusic;
    Activity activity;
    String [] songoss;
    TextView textlinks;
    LayoutInflater inflater;
    List<HashMap<String, String>> arraa= new ArrayList<HashMap<String,String>>();

    HashMap<String,String> hashMa=new HashMap<String, String>();

    public adapternews (Activity activity, int itemmenumusic, List<HashMap<String, String>> arr){
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
        inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.itemnews, null);
        hashMa = arraa.get(position);
        textlinks = (TextView) v.findViewById(R.id.text);

        textmusic = (TextView) v.findViewById(R.id.textnews);
//        textlinks.setText(hashMa.get("link"));
        textmusic.setText(hashMa.get("NEW"));


        return v;
    }
}
