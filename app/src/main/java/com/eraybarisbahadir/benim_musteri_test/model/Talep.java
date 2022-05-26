package com.eraybarisbahadir.benim_musteri_test.model;

public class Talep {

    public String ticket_id;
    public String user_email;
    public String details;


    public Talep(String ticket_id, String user_email, String details) {
        this.ticket_id = ticket_id;
        this.user_email = user_email;
        this.details = details;

    }
    public String getTicket_id(){
        return ticket_id;
    }
    public String getUser_email(){
        return user_email;
    }
    public String getDetails(){
        return details;
    }}
