package com.eraybarisbahadir.benim_musteri_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TicketCreationSuccess extends musteriTicketCreation {

    TextView ticketNumbertxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_creation_success);


        Intent yolla = getIntent();
        String ticketNum = yolla.getStringExtra ("ticketNum");
        System.out.println(ticketNum);

        TextView ticketNumbertxt = (TextView)findViewById(R.id.ticketNumbertxt);
        ticketNumbertxt.setText(ticketNum);
        setContentView(ticketNumbertxt);


    }
}
