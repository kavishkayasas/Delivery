package com.example.packagedelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {


    DatabaseReference dbRef;
    EditText ed1,ed2,ed3,ed4,ed5;
    Button btn;

    String mobile,user,email,address,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dbRef = FirebaseDatabase.getInstance().getReference().child("User");

        btn = findViewById(R.id.btnsign);
        ed1 = findViewById(R.id.id1);//mobile number
        ed2 = findViewById(R.id.id2);//user name
        ed3 = findViewById(R.id.id3);//email
        ed4 = findViewById(R.id.id4);//address
        ed5 = findViewById(R.id.id5);//password





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                User std= new User();
                mobile = ed1.getText().toString();
                user = ed2.getText().toString();
                email =ed3.getText().toString();
                address =ed4.getText().toString();
                password =ed5.getText().toString();


                if(mobile.isEmpty()){

                    ed1.setError("Enter Mobile Number");
                    ed1.requestFocus();

                }
                else if(user.isEmpty()){

                    ed2.setError("Enter User Name");
                    ed2.requestFocus();

                }
                else if(email.isEmpty()){

                    ed3.setError("Enter Email");
                    ed3.requestFocus();

                }
                else if(address.isEmpty()){

                    ed4.setError("Enter Address");
                    ed4.requestFocus();

                }
                else if(password.isEmpty()){

                    ed5.setError("Enter Password");
                    ed5.requestFocus();

                }
                else{



                    std.setMobile(ed1.getText().toString().trim());
                    std.setName(ed2.getText().toString().trim());
                    std.setEmail(ed3.getText().toString().trim());
                    std.setAddress(ed4.getText().toString().trim());
                    std.setPassword(ed5.getText().toString().trim());


                    dbRef.child(""+ed1.getText().toString()).setValue(std);

                    Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);




                }



            }
        });









    }
}