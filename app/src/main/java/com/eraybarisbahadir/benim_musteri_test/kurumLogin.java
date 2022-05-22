package com.eraybarisbahadir.benim_musteri_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class kurumLogin extends AppCompatActivity implements View.OnClickListener{

    Button login;
    Button register;
    EditText pwd;
    TextView email;
    ProgressBar progress;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.txt_email);
        pwd = findViewById(R.id.txt_pwd);
        progress = findViewById(R.id.prg_bar);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        db = FirebaseFirestore.getInstance();
        login.setOnClickListener(this);
        register.setOnClickListener(this);

    }
        public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_login:
                    if(email.getText().toString().equals("")){
                        Toast.makeText(kurumLogin.this, "E-posta boş bırakılamaz.", Toast.LENGTH_SHORT).show();
                    }else if( pwd.getText().toString().equals("")){
                        Toast.makeText(kurumLogin.this, "Şifre boş bırakılamaz.", Toast.LENGTH_SHORT).show();
                    }
                    db.collection("kurum")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if(task.isSuccessful()){
                                        for(QueryDocumentSnapshot doc : task.getResult()){
                                            String a=doc.getString("Email");
                                            String b=doc.getString("Password");
                                            String a1=email.getText().toString().trim();
                                            String b1=pwd.getText().toString().trim();
                                            if(a.equalsIgnoreCase(a1) & b.equalsIgnoreCase(b1)) {
                                                Intent home = new Intent(kurumLogin.this, Tickets.class);
                                                startActivity(home);
                                                Toast.makeText(kurumLogin.this, "Giriş başarılı!", Toast.LENGTH_SHORT).show();
                                                break;
                                            }

                                            else
                                                Toast.makeText(kurumLogin.this, "Giriş başarısız, e-posta ya da şifre yanlış.", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }
                                }
                            });
                    break;
                case R.id.btn_register:
                    Intent register_view=new Intent(kurumLogin.this, kurumSignUp.class);
                    startActivity(register_view);
                    break;
            }

        }
}

