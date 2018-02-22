package com.example.nasa.hexcodecolor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    int colorcount=10;
    ImageView img;
    Button set;
    EditText hexcode;
    Firebase fb_db;
    ArrayList<HexAdapter> adapters = new ArrayList<>();
    ItemAdapter itemarrayadapter;
    RecyclerView recyclerView;
    GridLayoutManager layoutManager = new GridLayoutManager(this,2);
    String color;
    String baseurl ="https://database-b5b79.firebaseio.com/";

    @Override
    protected void onResume() {
        super.onResume();
        itemarrayadapter.setOnItemClickListener(new ItemAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v)
            {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                Toast.makeText(MainActivity.this, "card clicked",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        img=(ImageView)findViewById(R.id.img);
        set=(Button)findViewById(R.id.setcolor);
        hexcode=(EditText)findViewById(R.id.code);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

//to be removed
        adapters.add(0,new HexAdapter("#159874"));
        itemarrayadapter = new ItemAdapter(R.layout.card,adapters);
        recyclerView.setAdapter(itemarrayadapter);
//above this line

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = hexcode.getText().toString();

                if(color.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please Enter a Hex Color Code", Toast.LENGTH_SHORT).show();
                }
                else if(color.length()==6)
                {
                    adapters.add(1,new HexAdapter("#"+color));
                    itemarrayadapter = new ItemAdapter(R.layout.card,adapters);
                    recyclerView.setAdapter(itemarrayadapter);

                    new MyTask().execute();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Requires a valid 6 digit HEX CODE", Toast.LENGTH_SHORT).show();
                }
                hexcode.setText(null);
            }
        });
    }

    public class MyTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings) {

            fb_db = new Firebase(baseurl+"HexCode/");
            fb_db.addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    fb_db = new Firebase(baseurl);

                    fb_db.child("Color").child("C"+colorcount).setValue(color);
                    colorcount++;
                }

                @Override
                public void onCancelled(FirebaseError firebaseError)
                {

                }
            });
            return null;
        }
    }
}
