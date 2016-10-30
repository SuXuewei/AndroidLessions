package com.suxw.androidlessions;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class FoodListActivity extends Activity {

	private static final String TAG="FoodListActivity";
	ArrayList<String> mFoods;
	ArrayList<String> mFoodList;
	ArrayAdapter<String> mViewAdapter;
	EditText etFoodCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foodlist);

		mFoods = initData();

		Button btnStart	= (Button)findViewById(R.id.btn_start);
		ListView lvFoods = (ListView)findViewById(R.id.lv_foodList);
		TextView tvEmpty	= (TextView)findViewById(R.id.tv_foodlistempty);
		EditText etMainFoodCount = (EditText)findViewById(R.id.et_mainFoodCount);
		etFoodCount = (EditText)findViewById(R.id.et_foodCount);
		lvFoods.setEmptyView(tvEmpty);
		getData(mFoods, mFoods.size());

		mViewAdapter = new ArrayAdapter<String>(this, 
					R.layout.testmenu_list_item, 	//listview需要一个布局文件来完成对item的布局
					R.id.tv_testmenu_item,			//确认在item布局中用于显示内容的控件id
					mFoodList);
		lvFoods.setAdapter(mViewAdapter);
/*		lvFoods.setAdapter(new ArrayAdapter<String>(this, 
				R.layout.testmenu_list_item, 	//listview需要一个布局文件来完成对item的布局
				R.id.tv_testmenu_item,			//确认在item布局中用于显示内容的控件id
				getData(foods, Integer.valueOf(etFoodCount.getText().toString()))));*/

		btnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Log.i(TAG, "onClick");
				changeFoodList();
			}
		});
	}

	private void changeFoodList(){
		getData(mFoods, Integer.valueOf(etFoodCount.getText().toString()));
		mViewAdapter.notifyDataSetChanged();
	}

	private ArrayList<String> initData(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("西红柿炒蛋");
		list.add("西葫芦炒肉");
		list.add("小鸡炖蘑菇");
		list.add("可乐鸡翅");
		return list;
	}

	private ArrayList<String> getData(ArrayList<String> foodList, int nFoodCount){
		ArrayList<Integer> arySelectedFoodIds = new ArrayList<Integer>();
		ArrayList<String> list;
		
		Log.i(TAG, "getData");

		if(null == mFoodList) {
			mFoodList = new ArrayList<String>();
		}
		if(!mFoodList.isEmpty()) {
			mFoodList.clear();
		}

		if(nFoodCount < foodList.size())
			list = foodList;

		for(int i = 0; i < nFoodCount; i++){
			int nSelectedId = 0;
			nSelectedId = (int)(Math.random() * 100) % foodList.size();
			while(arySelectedFoodIds.contains(nSelectedId))
				nSelectedId = (nSelectedId + 1) % foodList.size();

			arySelectedFoodIds.add(nSelectedId);
		}

		for(int i = 0; i < arySelectedFoodIds.size(); i++){
			mFoodList.add(foodList.get(arySelectedFoodIds.get(i)));
		}
		
		Log.i(TAG, "mFoodList length " + mFoodList.size());

		return mFoodList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
