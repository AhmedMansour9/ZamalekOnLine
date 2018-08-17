package zamaleekonlinee.zamalekonline;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class sportsfragment extends Fragment {

    Button btnonsport;
    Button btndmcsport;
    Button btnnilesport;
    Button btnelhayah;
    public sportsfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sportsfragment,container, false);

        btndmcsport=(Button)rootView.findViewById(R.id.Dmcsport);
        btnonsport=(Button)rootView.findViewById(R.id.onsport);
        btnnilesport=(Button)rootView.findViewById(R.id.Nilesport);
        btnelhayah=(Button)rootView.findViewById(R.id.Elhayah);

        btnonsport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent inty = new Intent(getActivity(), OnsportYoutube.class);
                    startActivity(inty);

            }
        });

        btndmcsport.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intty=new Intent(getActivity(), Dmcsports.class);
                startActivity(intty);

            }
        });
        btnnilesport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intyy=new Intent(getActivity(),ONEYoutube.class);
                startActivity(intyy);

            }
        });
        btnelhayah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getActivity(),Elhayahyoutube.class);
                startActivity(inten);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

}
