package com.app.listviewdrop;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


public class ListViewDropActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_drop);


        ListView lvItems = (ListView) findViewById(R.id.lv_items);
        
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = getIntent();
			    finish();
			    startActivity(intent);
				
			}
		});
        
        ExpandableAdapter adapter = getAdapter();

        lvItems.setAdapter(adapter);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExpandableAdapter adapter = (ExpandableAdapter) parent.getAdapter();

                Item item = (Item) adapter.getItem(position);
                
                if(item != null){
                    if(item.isExpanded){
                        item.isExpanded = false;
                    }else{
                        item.isExpanded = true;
                    }
                }

                adapter.notifyDataSetChanged();
            }
        });
    }


    private ExpandableAdapter getAdapter(){

        List<Item> items = new ArrayList<Item>();

        for(int i = 0; i < 50; i++){
            Item item = new Item();
            item.title = "Title Item " + i;
            item.description = "Description for Title Item "+ i;
            item.isExpanded = false;

            items.add(item);
        }

        return new ExpandableAdapter(this, items);
    }
}