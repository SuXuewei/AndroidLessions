package com.suxw.androidlessions.layoutandview;

import com.suxw.androidlessions.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewBackgroundActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ListView list = new ListView(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				R.layout.custom_row,
				R.id.item_content,
				new String[] {"–°Ã’", "±¶±¶", "–°±¶±¶"});
		list.setAdapter(adapter);
		setContentView(list);
	}

}
