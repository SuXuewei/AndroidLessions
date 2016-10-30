package com.suxw.androidlessions;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DataParserActivity extends Activity {

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
	}

	private ArrayList<String> getData(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("TLV���ݽ���");
		list.add("JSON���ݽ���");
		list.add("XML���ݽ���");
		
		return list;
	}

}
