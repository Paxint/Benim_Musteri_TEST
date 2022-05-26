package com.eraybarisbahadir.benim_musteri_test.model;

import com.google.firebase.Timestamp;

public class Talep {

    String ticket_id;
    String user_email;
    String details;
    Timestamp date;

    public Talep(String ticket_id, String user_email, String details, Timestamp date) {
        this.ticket_id = ticket_id;
        this.user_email = user_email;
        this.details = details;
        this.date = date;
    }
}
