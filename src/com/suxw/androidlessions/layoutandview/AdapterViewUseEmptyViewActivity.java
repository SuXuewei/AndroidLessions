package com.suxw.androidlessions.layoutandview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.suxw.androidlessions.R;

public class AdapterViewUseEmptyViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adapterview_emptyview);
		
		LinearLayout llEmpty = (LinearLayout)findViewById(R.id.ll_empty);
		ListView lvItems = (ListView)findViewById(R.id.lv_items);
		lvItems.setEmptyView(llEmpty);
	}

}
