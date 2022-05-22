package com.eraybarisbahadir.benim_musteri_test;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class musteriTicketCreate extends AppCompatActivity {



    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    FirebaseFirestore db;
    String [] items={};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_creation);
        
        

        db = FirebaseFirestore.getInstance();
        db.collection("kurum")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("", document.getId() + " => " + document.getData());
                                String name =document.getString("Name");
                                String name1=autoCompleteTxt.getText().toString().trim();
                                if (name.equalsIgnoreCase(name1)){



                                }



                            }
                        } else {
                            Log.d("" ,"Error getting documents: ", task.getException());
                        }

                      
                    }
                });
        

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();


        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.mybackbutton);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        //here I put kurum list codes
        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.kurum_list,items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
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

}
