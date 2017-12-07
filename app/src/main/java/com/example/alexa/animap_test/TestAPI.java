package com.example.alexa.animap_test;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.alexa.animap_test.AndroidVersion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestAPI extends AppCompatActivity implements LoadJSONTask.Listener, AdapterView.OnItemClickListener {

    private ListView mListView;
    private Button retour;
    public static final String URL = "https://api.learn2crack.com/android/jsonandroid/";

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();


    private static final String KEY_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);

        retour = (Button)findViewById(R.id.btnRetourTest);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Menu.class));
                finish();
            }
        });

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        new LoadJSONTask(this).execute(URL);
    }

    @Override
    public void onLoaded(List<AndroidVersion> androidList) {

        for (AndroidVersion android : androidList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_NAME, android.getName());

            mAndroidMapList.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mAndroidMapList.get(i).get(KEY_NAME),Toast.LENGTH_LONG).show();
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(TestAPI.this, mAndroidMapList, R.layout.list_item,
                new String[] { KEY_NAME},
                new int[] {R.id.name});

        mListView.setAdapter(adapter);

    }
}