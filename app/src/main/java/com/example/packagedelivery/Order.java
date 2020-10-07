package com.example.packagedelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5,btn6;
    DatabaseReference dbRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Item");

        Intent intent = getIntent();
        final String itemname = intent.getStringExtra("name");
        Toast.makeText(getApplicationContext(),""+itemname,Toast.LENGTH_SHORT).show();




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Item.class);
                String inm ="Pizza";
                intent.putExtra("ItemName","Pizza");
                intent.putExtra("mobile",itemname);
                startActivity(intent);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Item.class);
                intent.putExtra("ItemName","Burger");
                startActivity(intent);


            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Item.class);
                intent.putExtra("ItemName","Submarine Bun");
                startActivity(intent);

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Item.class);
                intent.putExtra("ItemName","BBQ");
                startActivity(intent);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Item.class);
                intent.putExtra("ItemName","Crispy Chicken");
                startActivity(intent);

            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Item.class);
                intent.putExtra("ItemName","Sandwich");
                startActivity(intent);

            }
        });




    }
}