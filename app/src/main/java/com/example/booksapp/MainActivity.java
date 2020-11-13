package com.example.booksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText textSearch;
    private Button buttonSearch;
    private RecyclerView recylerview;
    private ArrayList<ItemData> values;
    private  ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textSearch = findViewById(R.id.edit_query);
        buttonSearch = findViewById(R.id.button_search);
        recylerview = findViewById(R.id.view_recycler_buku);
        values = new ArrayList<>();
        itemAdapter = new ItemAdapter(this, values);
        recylerview.setLayoutManager(new LinearLayoutManager(this));
        recylerview.setAdapter(itemAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBooks();
            }
        });
    }

    public void searchBooks() {
        String queryString = textSearch.getText().toString();

        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected() && queryString.length() > 0){
            new FetchBook(this, values, itemAdapter, recylerview).execute(queryString);
        } else{
            Toast.makeText(this, "Tolong cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
        }
    }
}