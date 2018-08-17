package zamaleekonlinee.zamalekonline;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Memories extends Fragment {
//    static  String Soong="soong";
    HashMap<String,String> hash=new HashMap<>();
    ArrayList<HashMap<String,String>> arra=new ArrayList<>();
//    public static String linnKK="link";
    ListView lis;
    EditText search;
    ArrayList <HashMap<String,String>> Liist = new ArrayList<HashMap<String,String>>();
    String searchString;
    HashMap<String, String> hh;
    int count_items;
  adapterMemories adaptermemories;
    public Memories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootVie = inflater.inflate(R.layout.fragment_memories,
                container, false);


        String [] TextMomries=getActivity().getResources().getStringArray(R.array.TextMomries);
        String [] CodeeYoutube=getActivity().getResources().getStringArray(R.array.YoutubeMemories);
        search=(EditText)rootVie.findViewById(R.id.inputSearch);
        for(int e=0;e<TextMomries.length;e++) {
            hh = new HashMap<String, String>();
            hh.put("textMomries", TextMomries[e]);

            hh.put("CodeYoutube", CodeeYoutube[e]);

            Liist.add(hh);
        }
         lis=(ListView)rootVie.findViewById(R.id.ListMemories);
        adaptermemories=new adapterMemories(this.getActivity(),R.layout.itemmemories,Liist);
        lis.setAdapter(adaptermemories);
        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), YoutubtStadium.class);



                String code = ((TextView) view.findViewById(R.id.textcodee)).getText().toString();
                String memories = ((TextView) view.findViewById(R.id.TexTMemorieS)).getText().toString();
                // Sending songIndex to PlayerActivity

                intent.putExtra("Code", code);

                startActivity(intent);

            }
        });


        search.addTextChangedListener(new TextWatcher()
        {
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                //get the text in the EditText
                searchString=search.getText().toString();
                int textLength=searchString.length();
                ArrayList<HashMap<String, String>> searchResults = new ArrayList<HashMap<String, String>>();
                for(int i=0;i<Liist.size();i++)
                {
                    String rname = Liist.get(i).get("textMomries").toString();

                    if(textLength<=rname.length())
                    {
                        //compare the String in EditText with Names in the ArrayList
                        if(searchString.equalsIgnoreCase(rname.substring(0,textLength)))
                        {
                            searchResults.add(Liist.get(i));
                            adaptermemories.notifyDataSetChanged();
                            lis.invalidate();
                            adaptermemories=new adapterMemories(getActivity(),R.layout.itemmemories, searchResults);
                            lis.setAdapter(adaptermemories);
                        }
                    }
                }
                if(searchResults.isEmpty())
                {
                    if(count_items==0)
                    {
                    }
                    else
                    {
//                        ll.setVisibility(View.INVISIBLE);
                        Toast toast= Toast.makeText(getActivity(),
                                "No Items Matched", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                System.out.println("before changed");
//                ll.setVisibility(View.VISIBLE);
                lis.invalidate();
                adaptermemories.notifyDataSetChanged();
            }

            public void afterTextChanged(Editable s)
            {
                System.out.println("after changed");
                lis.invalidate();
                adaptermemories.notifyDataSetChanged();
            }
        });


        // Inflate the layout for this fragment
        return rootVie;
    }

}
