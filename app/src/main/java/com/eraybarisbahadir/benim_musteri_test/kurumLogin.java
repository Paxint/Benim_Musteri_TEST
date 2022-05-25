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

import com.eraybarisbahadir.benim_musteri_test.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class kurumLogin extends AppCompatActivity implements View.OnClickListener{

    Button login;
    Button register;
    EditText pwd;
    TextView email;
    ProgressBar progress;
    FirebaseFirestore db;
    private FirebaseAuth auth;
    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        email = findViewById(R.id.txt_email);
        pwd = findViewById(R.id.txt_pwd);
        progress = findViewById(R.id.prg_bar);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        db = FirebaseFirestore.getInstance();
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        auth=FirebaseAuth.getInstance();






    }



            public void onClick(View v){
            switch(v.getId()){
                case R.id.btn_login:
                    String loginmailcheck = binding.txtEmail.getText().toString();
                    String loginpasscheck= binding.txtPwd.getText().toString();

                    if (loginmailcheck.equals("")|| loginpasscheck.equals((""))){
                    Toast.makeText(this,"E-posta veya şifre boş bırakılamaz!",Toast.LENGTH_SHORT).show();
                    } else {
                    auth.signInWithEmailAndPassword(loginmailcheck,loginpasscheck).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(kurumLogin.this, "Başarıyla giriş yapıldı", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(kurumLogin.this, Tickets.class);
                            startActivity(intent);
                            finish();
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(kurumLogin.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

                    }
                    break;
                case R.id.btn_register:
                    Intent register_view=new Intent(kurumLogin.this, kurumSignUp.class);
                    startActivity(register_view);
                    break;
            }


        }
}

