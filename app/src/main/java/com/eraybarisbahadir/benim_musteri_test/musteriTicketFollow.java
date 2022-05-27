package com.eraybarisbahadir.benim_musteri_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.eraybarisbahadir.benim_musteri_test.databinding.ActivityMusteriTicketCreationBinding;
import com.eraybarisbahadir.benim_musteri_test.databinding.ActivityMusteriTicketFollowBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class musteriTicketFollow extends AppCompatActivity {

    Button buttonticketsearch;
    EditText editTicketFollow;
    EditText pwd_ID;
    FirebaseFirestore db;
    ActivityMusteriTicketFollowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusteriTicketFollowBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.mybackbutton);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        db = FirebaseFirestore.getInstance();
        pwd_ID = findViewById(R.id.editTicketFollow);
        buttonticketsearch = findViewById(R.id.buttonticketsearch);

        getTicketDetailsData();
        binding.buttonticketsearch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View view){

        db.collection("talep").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
                        long a = doc.getLong("ID");
                        String a1 = pwd_ID.getText().toString().trim();
                        long deger = Long.valueOf(a1);

                        String ticketNum = String.valueOf(deger);
                        String companyName =(String) doc.get("Company");
                        String emailName =(String) doc.get("Email");
                        String topicName =(String) doc.get("Detail");

                        if ( a==deger ) {
                            Toast.makeText(musteriTicketFollow.this, "Talep bulundu.", Toast.LENGTH_SHORT);

                            setContentView(R.layout.activity_ticket_details);
                            TextView ticket_no = (TextView) findViewById(R.id.ticket_no);
                            ticket_no.setText(ticketNum);

                            TextView company = (TextView) findViewById(R.id.company);
                            company.setText(companyName);

                            TextView contact = (TextView) findViewById(R.id.contact);
                            contact.setText(emailName);

                            TextView detail = (TextView) findViewById(R.id.detail);
                            detail.setText(topicName);

                        } else {

                            Toast.makeText(musteriTicketFollow.this, "Talep bulunamadÄ±.", Toast.LENGTH_SHORT);
                        }

                    }
                }
            }
        });
    }
    });

}

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getTicketDetailsData() {
        db.collection("talep").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Toast.makeText(musteriTicketFollow.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();

                        // Zorunlu casting yapıldı


                    }
                }
            }
        });
    }
}