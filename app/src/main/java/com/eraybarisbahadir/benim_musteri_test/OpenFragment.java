package com.eraybarisbahadir.benim_musteri_test;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eraybarisbahadir.benim_musteri_test.databinding.ActivityTicketBinding;
import com.eraybarisbahadir.benim_musteri_test.model.Talep;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenFragment extends Fragment {


    private FirebaseFirestore firebaseFirestore;
    ArrayList<Talep> talepArrayList;
    List<String> talep = new ArrayList<String>();
    String[] talepArray = talepArrayList.toArray(new String[0]);



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OpenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OpenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OpenFragment newInstance(String param1, String param2) {
        OpenFragment fragment = new OpenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_open,container,false);
        getticketData();

        talepArrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();

        RecyclerView rv = new RecyclerView(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new TalepAdapter(talepArray));
        return rv;
    }


    public class TalepAdapter extends RecyclerView.Adapter<TalepHolder> {
        private String[] dataSource;
        public TalepAdapter(String[] dataArgs){
            dataSource = dataArgs;
        }

        @Override
        public TalepHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new TextView(parent.getContext());
            TalepHolder viewHolder = new TalepHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(TalepHolder holder, int position) {
            holder.recycler_view_ticket.setText(dataSource[position]);
        }

        @Override
        public int getItemCount() {
            return dataSource.length;
        }
    }

    public static class TalepHolder extends RecyclerView.ViewHolder{
        public TextView recycler_view_ticket;
        public TalepHolder(View itemView) {
            super(itemView);
            recycler_view_ticket = (TextView) itemView;
        }
    }



    private void getticketData() {
        firebaseFirestore.collection("talep").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {

                }
                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();

                        // Zorunlu casting yapıldı
                        String ticket_id = (String) data.get("ID");
                        String user_email = (String) data.get("Email");
                        String details = (String) data.get("Detail");
                        Timestamp date = (Timestamp) data.get("Date");

                        Talep talep = new Talep(ticket_id,user_email,details,date);
                        talepArrayList.add(talep);

                    }

                    }

        }


                });



        };

}