package com.eraybarisbahadir.benim_musteri_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eraybarisbahadir.benim_musteri_test.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class kurumSignUp extends AppCompatActivity {
    private ActivitySignUpBinding binding;

    Button reg_registration;
    Button signin_view;
    EditText reg_name;
    EditText reg_email;
    EditText reg_password;
    EditText reg_conf_pwd;
    TextView signin;
    FirebaseFirestore firebaseFirestore;
    DocumentReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        reg_registration = findViewById(R.id.btn_reg);
        reg_name = findViewById(R.id.reg_name);
        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_password);
        reg_conf_pwd = findViewById(R.id.reg_conpwd);
        signin = findViewById(R.id.signin);
        signin_view=findViewById(R.id.signin_view);
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();


        firebaseFirestore = FirebaseFirestore.getInstance();

        ref = firebaseFirestore.collection("kurum").document();


        reg_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reg_name.getText().toString().equals("")) {
                    Toast.makeText(kurumSignUp.this, "Kurum adı boş bırakılamaz.", Toast.LENGTH_SHORT).show();

                } else if (reg_email.getText().toString().equals("")) {
                    Toast.makeText(kurumSignUp.this, "E-posta boş bırakılamaz.", Toast.LENGTH_SHORT).show();

                } else if (reg_password.getText().toString().equals("")) {
                    Toast.makeText(kurumSignUp.this, "Şifre boş bırakılamaz.", Toast.LENGTH_SHORT).show();

                } else if (!reg_conf_pwd.getText().toString().equals(reg_password.getText().toString())) {
                    Toast.makeText(kurumSignUp.this, "Şifreler eşleşmiyor.", Toast.LENGTH_SHORT).show();

                } else {

                    ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            if (documentSnapshot.exists()) {
                                Toast.makeText(kurumSignUp.this, "Bu adrese kayıtlı bir hesap zaten var.", Toast.LENGTH_SHORT).show();
                            } else {
                                Map<String, Object> reg_entry = new HashMap<>();
                                reg_entry.put("Name", reg_name.getText().toString());

                                firebaseFirestore.collection("kurum")
                                        .add(reg_entry)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {

                                            }
                                        });

                                String email = binding.regEmail.getText().toString();
                                String pass = binding.regPassword.getText().toString();

                                auth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(kurumSignUp.this, "Başarıyla kayıt olundu!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(kurumSignUp.this, Tickets.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(kurumSignUp.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                }
                signin_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(kurumSignUp.this, kurumLogin.class);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}