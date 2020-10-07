package com.example.packagedelivery;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.ImageReader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    DatabaseReference dataRef,dbref;
    Button btn;
    EditText num;
    String lo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        itm = findViewById(R.id.item);
        pprice = findViewById(R.id.price);
        im = findViewById(R.id.imageView);
        num = findViewById(R.id.number);


        btn = findViewById(R.id.cart);

        im.setImageResource(R.drawable.pizza);
        Intent intent = getIntent();
        String itemname = intent.getStringExtra("ItemName");
        final String number = intent.getStringExtra("mobile");


        Toast.makeText(getApplicationContext(),""+number,Toast.LENGTH_SHORT).show();


        //get from database

        final String itemPrice,location;
        itm.setText(itemname);
        dataRef = FirebaseDatabase.getInstance().getReference().child("Item/"+itemname);
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChildren()){

                    pprice.setText("Price :-"+dataSnapshot.child("Price").getValue().toString());
                    lo = dataSnapshot.child("Price").getValue().toString();
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



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String count = num.getText().toString();
                dbref = FirebaseDatabase.getInstance().getReference().child("Cart/"+number);
                if(count.isEmpty()){

                    num.setError("Enter Count");
                    num.requestFocus();
                }else {

                    Toast.makeText(getApplicationContext(), "thi si" + lo, Toast.LENGTH_SHORT).show();
                    //dbref.child("item Name").setValue(lo);
                    dbref.push().setValue(lo);
                    Toast.makeText(getApplicationContext(), "Count is" + count, Toast.LENGTH_SHORT).show();

                    dbref.push().setValue(count);
                    //dbref.child("Item count").setValue(count);
                }
            }
        });









    }
}