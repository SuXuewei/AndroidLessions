package com.suxw.androidlessions.menu;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.suxw.androidlessions.R;
import com.suxw.androidlessions.layoutandview.AnimatorActivity;
import com.suxw.androidlessions.layoutandview.CreateViewActivity;
import com.suxw.androidlessions.layoutandview.DIYViewActivity;
import com.suxw.androidlessions.layoutandview.FlipperActivity;
import com.suxw.androidlessions.layoutandview.TossCoinActivity;

public class UIMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testmenu);
		ListView lvItems = (ListView)findViewById(R.id.lv_testmenu_list_items);
		TextView tvEmpty	= (TextView)findViewById(R.id.tv_testmenu_empty);
		lvItems.setEmptyView(tvEmpty);
		lvItems.setAdapter(new ArrayAdapter<String>(
				this, 
				R.layout.testmenu_list_item, 
				R.id.tv_testmenu_item,
				getData()));
		lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				Intent  intent;
				if(0 == position) {
					intent = new Intent();
					intent.setClass(UIMenuActivity.this, CreateViewActivity.class);
				} else if(1 == position) {
					intent = new Intent();
					intent.setClass(UIMenuActivity.this, DIYViewActivity.class);
				} else if(2 == position) {
					intent = new Intent();
					intent.setClass(UIMenuActivity.this, AnimatorActivity.class);
				} else if(3 == position) {
					intent = new Intent();
					intent.setClass(UIMenuActivity.this, FlipperActivity.class);
				} else if(4 == position) {
					intent = new Intent();
					intent.setClass(UIMenuActivity.this, TossCoinActivity.class);
				} else {
					return;
				}
				startActivity(intent);
			}
		});
	}

	private ArrayList<String> getData(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(getResources().getString(R.string.unit_ui_set_system_ui));
		list.add(getResources().getString(R.string.unit_ui_creat_view));
		list.add(getResources().getString(R.string.unit_ui_animate_view));
		list.add(getResources().getString(R.string.unit_ui_animate_rotation_view));
		list.add(getResources().getString(R.string.unit_ui_animate_toss_coin));
		return list;
	}
}
