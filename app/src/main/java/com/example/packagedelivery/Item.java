package com.example.packagedelivery;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.ImageReader;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Item extends AppCompatActivity {

    ImageView im;
    TextView itm,pprice;
    DatabaseReference dataRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        itm = findViewById(R.id.item);
        pprice = findViewById(R.id.price);
        im = findViewById(R.id.imageView);

        String lo;
        im.setImageResource(R.drawable.pizza);
        Intent intent = getIntent();
        String itemname = intent.getStringExtra("ItemName");



        //get from database

        final String itemPrice,location;

        dataRef = FirebaseDatabase.getInstance().getReference().child("Item/"+itemname);
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()){

                    pprice.setText("Price :-"+dataSnapshot.child("Price").getValue().toString());
                    Toast.makeText(getApplicationContext(),""+dataSnapshot.child("location").getValue().toString(),Toast.LENGTH_SHORT).show();




                }
                else{

                    Toast.makeText(getApplicationContext(),"Cannot find Table",Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }
        });









        itm.setText(itemname);




    }
}