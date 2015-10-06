package com.fia.mia.slidelistviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.fia.mia.slidelistviewdemo.data.MyDemoData;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.demo_listview);


        //Test data
        ArrayList<MyDemoData> array = new ArrayList<MyDemoData>();
        MyDemoData testData;

        for(int i = 0;i<20;i++) {
            testData = new MyDemoData();
            testData.setIndexId("key "+i);
            testData.setTitle("[ "+ +i + " ] Title test Title test Title test Title test Title test Title test Title test ");
            testData.setContent("[ "+ +i + " ] content test \n content test \ncontent test  ");
            array.add(testData);
        }


        MySlideAdapter adapter = new MySlideAdapter(MainActivity.this, array);
        listView.setAdapter(adapter);

    }


}
