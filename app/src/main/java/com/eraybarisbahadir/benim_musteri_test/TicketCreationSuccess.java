package com.eraybarisbahadir.benim_musteri_test;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TicketCreationSuccess extends musteriTicketCreation {

    public TextView ticketNumbertxt;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_creation_success);





        System.out.println(ticketNum);

        db = FirebaseFirestore.getInstance();


        db.collection("talep")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                String a = doc.getString("Email");
                                String b = doc.getString("Detail");
                                String a1 = input_eposta_tel.getText().toString().trim();
                                String b1 = input_konu.getText().toString().trim();
                                TextView ticketNumbertxt =findViewById(R.id.ticketNumbertxt);

                                if (a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                                    Toast.makeText(TicketCreationSuccess.this, "Başarılı", Toast.LENGTH_SHORT).show();
                                    ticketNumbertxt.setText(String.valueOf(ticketNum));

                                }
                            }
                        }
                    }


      /* Intent yolla = getIntent();
        String ticketNum = yolla.getStringExtra ("ticketNum");
        System.out.println(ticketNum);

        TextView ticketNumbertxt = (TextView)findViewById(R.id.ticketNumbertxt);
        ticketNumbertxt.setText(ticketNum);
        setContentView(ticketNumbertxt);


       */

                });
    }
}
