package com.example.sohail.drawerlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    int[] Images = {
            R.drawable.manblack,
            R.drawable.womenblack,
            R.drawable.childrenblack,
            R.drawable.manafter,
            R.drawable.womenafter,
            R.drawable.boyafter
    };

    String[] Names = {
            "Men",
            "Women",
            "Kids"
    };



    Map<String,List<String>> Children;
    List<String> Men = new ArrayList<>();
    List<String> Women = new ArrayList<>();
    List<String> Kids = new ArrayList<>();
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Men.add("Shirts");
        Men.add("Trousers");
        Men.add("FootWear");
        Men.add("Summer");

        Women.add("Tops");
        Women.add("Jeans");
        Women.add("Footwear");

        Kids.add("Shirts");
        Kids.add("FootWear");
        Kids.add("Jeans");
        Kids.add("Accessories");
        Kids.add("Toys");

        Children = new HashMap<>();

        Children.put(Names[0],Men);
        Children.put(Names[1],Women);
        Children.put(Names[2],Kids);

        expandableListView = findViewById(R.id.ExpandableListView);
        expandableListAdapter = new MyAdapter(this,Images,Names,Children);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Images[0] = R.drawable.manblack;
                Images[1] = R.drawable.womenblack;
                Images[2] = R.drawable.childrenblack;
                Images[3] = R.drawable.manafter;
                Images[4] = R.drawable.womenafter;
                Images[5] = R.drawable.boyafter;
                Images[i] = Images[i+3];
                view.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.myblue));
                final BaseExpandableListAdapter adapter = (BaseExpandableListAdapter) expandableListAdapter;
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Group "+i,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(MainActivity.this,"Child "+i,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }



}
