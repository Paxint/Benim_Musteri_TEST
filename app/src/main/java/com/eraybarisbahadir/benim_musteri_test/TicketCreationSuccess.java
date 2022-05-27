/* package com.eraybarisbahadir.benim_musteri_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TicketCreationSuccess extends AppCompatActivity {

    private ImageButton btn_forward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_creation_success);

        ImageButton btn_forward = (ImageButton) findViewById(R.id.btn_forward);


        btn_forward.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent= new Intent(TicketCreationSuccess.this,CustomerChooseActivity.class);
                startActivity(intent);
            }

        });

    }


}

 */
