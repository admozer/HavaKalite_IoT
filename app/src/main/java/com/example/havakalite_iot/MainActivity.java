package com.example.havakalite_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference okuma = database.getReference("ODALAR");
    DatabaseReference devre = database.getReference("KALITE");

    TextView textView;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        devre.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue(Integer.class) > 100) {
                        textView2.append("ODA1 : " + dataSnapshot.getValue() + " hava kalitesi kötü havalandırma açılıyor...." + "\n\n");
                    } else {
                        textView2.append("ODA1 : "  + dataSnapshot.getValue() + " hava kalitesi iyi havalandırma kapatılıyor...." + "\n\n");
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        okuma.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> keys = dataSnapshot.getChildren();
                for (DataSnapshot key:keys) {
                    if(key.getValue(Integer.class) > 100){
                        textView2.append(key.getKey()+ " : " + key.getValue()+ " hava kalitesi kötü havalandırma açılıyor...." +"\n\n");
                    }
                    else{
                        textView2.append(key.getKey()+ " : " + key.getValue()+ " hava kalitesi iyi havalandırma kapatılıyor...." +"\n\n");
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}