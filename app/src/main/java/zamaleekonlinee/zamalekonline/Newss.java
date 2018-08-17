package zamaleekonlinee.zamalekonline;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Newss extends Fragment {
    private Firebase fire;

    ListView list;
    adapternews arrayAdapt;
    HashMap<String, String> hash;

    ArrayList<HashMap<String,String>> arra=new ArrayList<>();

    public Newss() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fire.setAndroidContext(getActivity());


//

        // Inflate the layout for this fragment


//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference ref = database.getReference("Series");
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//                for (DataSnapshot seriesSnapShot : dataSnapshot.getChildren()) {
//
//                }
////                    series.setStory(seriesSnapShot.child("Story").getValue(String.class));
//
//
////                    seriesEpisodesList.add(seriesEpisodes);
//
////                mGridData.add(series);
//
////            mGridAdapter.setGridData(mGridData);
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//            }
//

//        });
        FirebaseDatabase mDatabase= FirebaseDatabase.getInstance();
        mDatabase.getReference().child("News").child("new").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = (ListView) getActivity().findViewById(R.id.listnews);

                list.setAdapter(null);
//                HashMap<String,String> news =(HashMap<String,String>)dataSnapshot.getValue();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String s = data.getValue(String.class);
                    hash = new HashMap<String, String>();
                    hash.put("NEW", s);
                    arra.add(0,hash);
                }
                adapternews adapter = new adapternews(getActivity(), R.layout.itemnews, arra);
                list.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Firebase mRef;
        mRef = new Firebase("https://lastproject-150216.firebaseio.com/News/new");

//




    /* fire.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

//     String value = (String) dataSnapshot.child("News").getValue();
                for (DataSnapshot seriesSnapShot : dataSnapshot.getChildren()) {
                    hash = new HashMap<String, String>();

//                    HashMap<String,String> news =(HashMap<String,String>)dataSnapshot.getValue();

                    String vale= seriesSnapShot.child("new").getValue(String.class);
                    hash.put("NEW", vale);

                    arra.add(0, hash);

                }*/


//
/*
                try {
                    if (dataSnapshot.getValue() != null) {
                        Map<String, String> value = (Map<String, String>) dataSnapshot.child("new").getValue();
                        for (Map.Entry<String, String> entry : value.entrySet()) {
//                            String _key = entry.getKey();
                            String _value = entry.getValue();
                            // TODO add value in list

                            hash.put("NEW", _value);
                            arra.add(0, hash);
                        }
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
*/
//                  تi
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });

        return inflater.inflate(R.layout.fragment_newss, container, false);

    }

}
