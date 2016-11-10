package com.suxw.androidlessions.layoutandview;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.suxw.androidlessions.R;

public class CreateViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_createview);

		LinearLayout layout = (LinearLayout)getLayoutInflater()
				.inflate(R.layout.activity_createview, null);
		Button btnCancel = new Button(this);
		btnCancel.setText("Cancel");
		layout.addView(btnCancel, 
				new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 
						LayoutParams.WRAP_CONTENT));
		
		setContentView(layout);
	}

}
