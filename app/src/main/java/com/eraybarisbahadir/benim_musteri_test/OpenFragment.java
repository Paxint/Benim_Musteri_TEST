package com.eraybarisbahadir.benim_musteri_test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eraybarisbahadir.benim_musteri_test.model.Talep;
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


    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;

    List<Talep> itemList = new ArrayList<>();




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
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_open,container,false);

        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //getticketData();
        recyclerView.setAdapter(new ItemAdapter(getticketData(),getContext()));

        return view;
    }

    private List<Talep> getticketData() {
        firebaseFirestore.collection("talep").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                }
                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Map<String, Object> dataK = snapshot.getData();
                        // Zorunlu casting yapıldı
                        String ticket_id = String.valueOf(dataK.get("ID"));
                        String user_email = (String) dataK.get("Email");
                        String details = (String) dataK.get("Detail");
                        Talep talep = new Talep(ticket_id,user_email,details);
                        itemList.add(talep);
                    }
                    }

        }
                });
return itemList;
        };
}