package com.suxw.androidlessions;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testmenu);
		ListView lvTestItems = (ListView)findViewById(R.id.lv_testmenu_list_items);
		TextView tvEmpty = (TextView)findViewById(R.id.tv_testmenu_empty);
		lvTestItems.setEmptyView(tvEmpty);
		lvTestItems.setAdapter(new ArrayAdapter<String>(this, 
				R.layout.testmenu_list_item, 	//listview��Ҫһ�������ļ�����ɶ�item�Ĳ���
				R.id.tv_testmenu_item,			//ȷ����item������������ʾ���ݵĿؼ�id
				getData()));
		
		lvTestItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView , View view , int position ,long arg3) 
		    {
		        if(0 == position) {
		        	Intent intent = new Intent();
		        	intent.setClass(MainActivity.this, FoodListActivity.class);
		        	startActivity(intent);
		        }
		    }
		});
	}

	private ArrayList<String> getData(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("��ɶ");
		list.add("UI�ؼ�ʹ��");
		list.add("���ݽ���");
		list.add("���ݴ洢");

		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
