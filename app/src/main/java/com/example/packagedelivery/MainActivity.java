package com.example.packagedelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btn,btn2;
    EditText number,password;
    DatabaseReference dbRef;

    String username,pass,dbname,dbpass;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        number = findViewById(R.id.mnumber);//user name
        password = findViewById(R.id.upass);// password





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = number.getText().toString();
                pass = password.getText().toString();


                if(username.isEmpty()){
                    number.setError("Enter Mobile Number");
                    number.requestFocus();


                }
                else if(pass.isEmpty()){

                    password.setError("Enter Password");
                    password.requestFocus();

                }
                else{


                    //checking database
                    dbRef = FirebaseDatabase.getInstance().getReference().child("User/" + username);
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            if(dataSnapshot.getValue() != null){

                                dbname = dataSnapshot.child("mobile").getValue().toString();


                                dbpass = dataSnapshot.child("password").getValue().toString();


                                if (dbname.equals(username) && dbpass.equals(pass)) {

                                    Toast.makeText(getApplicationContext(),"Login successfull",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Home.class);
                                    intent.putExtra("userName",username);
                                    startActivity(intent);

                                } else {


                                    Toast.makeText(getApplicationContext(), "Password Wrong", Toast.LENGTH_SHORT).show();

                                }


                            }
                            else{

                                Toast.makeText(getApplicationContext(),"You have to Register",Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });




                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);

            }
        });






    }
}