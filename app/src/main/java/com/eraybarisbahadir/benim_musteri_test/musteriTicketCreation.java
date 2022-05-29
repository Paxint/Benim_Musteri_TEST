package com.eraybarisbahadir.benim_musteri_test;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.eraybarisbahadir.benim_musteri_test.databinding.ActivityMusteriTicketCreationBinding;
import com.eraybarisbahadir.benim_musteri_test.model.Kurum;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class musteriTicketCreation extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<Kurum> kurumArrayList;
    DocumentReference ref;
    Button btn_ticket_send;
    EditText input_eposta_tel;
    EditText input_konu;
    TextView ticketNumbertxt;
    int ticketNum;
    List<String> tempname = new ArrayList<String>();
    ActivityMusteriTicketCreationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusteriTicketCreationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        firebaseFirestore = FirebaseFirestore.getInstance();
        kurumArrayList = new ArrayList<>();
        btn_ticket_send = findViewById(R.id.btn_ticket_send);
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        input_konu = findViewById(R.id.input_konu);
        input_eposta_tel = findViewById(R.id.input_eposta_tel);
        getData();
        int ticketNumber = new Random().nextInt(900000)+10000;
        ref = firebaseFirestore.collection("talep").document();
        binding.btnTicketSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_eposta_tel.getText().toString().equals("")) {
                    Toast.makeText(musteriTicketCreation.this, "E-posta boş bırakılamaz.", Toast.LENGTH_SHORT).show();

                } else if (autoCompleteTxt.getText().toString().equals("")) {
                    Toast.makeText(musteriTicketCreation.this, "Kurum boş bırakılamaz.", Toast.LENGTH_SHORT).show();

                } else if (input_konu.getText().toString().equals("")) {
                    Toast.makeText(musteriTicketCreation.this, "Konu boş bırakılamaz.", Toast.LENGTH_SHORT).show();

                } else {

                    ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            if (documentSnapshot.exists()) {
                                Toast.makeText(musteriTicketCreation.this, "Bu talep oluşturulamaz lütfen bilgileri kontrol ediniz.", Toast.LENGTH_LONG).show();
                            } else {
                                Map<String, Object> reg_entry = new HashMap<>();
                                reg_entry.put("Email", input_eposta_tel.getText().toString());
                                reg_entry.put("Detail", input_konu.getText().toString());
                                reg_entry.put("Company", autoCompleteTxt.getText().toString());
                                reg_entry.put("ID", ticketNumber);

                                firebaseFirestore.collection("talep")
                                        .add(reg_entry)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(musteriTicketCreation.this, "Başarıyla gönderildi", Toast.LENGTH_SHORT);
                                                String ticketNum = String.valueOf(ticketNumber);

                                                setContentView(R.layout.activity_ticket_creation_success);
                                                TextView ticketNumbertxt = (TextView) findViewById(R.id.ticketNumbertxt);
                                                ticketNumbertxt.setText(ticketNum);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(musteriTicketCreation.this, "Hata!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    });
                }
            }
        });
        // Üst bar çağırılıyor
        ActionBar actionBar = getSupportActionBar();
        // Geri dön butonu customize ediliyor
        actionBar.setHomeAsUpIndicator(R.drawable.mybackbutton);
        // Action bar üzerinde göster
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Kurum Dropdown listesi için
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.kurum_list, tempname);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String items = parent.getItemAtPosition(position).toString();
            }
        });
    }

    // Geri butonunu çalıştıran function
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void getData() {
        firebaseFirestore.collection("kurum").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Toast.makeText(musteriTicketCreation.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();

                        // Zorunlu casting yapıldı
                        String name =(String) data.get("Name");
                        tempname.add(name);

                    }
                }
            }
        });
    }
}