package com.example.appllibres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected EditText titol;
    protected Button guardar, img;
    protected ArrayList<Llibre> llibres;
    protected RatingBar valoracio;
    protected CalendarView data;

    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titol = findViewById(R.id.llibre);
        data = findViewById(R.id.data);
        guardar = findViewById(R.id.guardar);
        valoracio = findViewById(R.id.ratingBar);

        llibres = new ArrayList<Llibre>();


        db = FirebaseDatabase.getInstance().getReference().child("Llibres");


        guardar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Llibre llibre = new Llibre(titol.getText().toString(), data.getDate(), Math.round(valoracio.getRating()));
                llibres.add(llibre);

                db.setValue(llibres);
            }
        });

        // Read from the database
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("logTest " ,""+dataSnapshot.getChildrenCount());

                llibres.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Llibre llibre = postSnapshot.getValue(Llibre.class);
                    llibres.add(llibre);
                    Log.i("logTest",llibre.getTitol());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("logTest", "Failed to read value.", error.toException());
            }
        });


    }
}