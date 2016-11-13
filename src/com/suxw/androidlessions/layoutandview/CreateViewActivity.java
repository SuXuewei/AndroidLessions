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

		//����xml�ļ�����һ�����ֿؼ���
		LinearLayout layout = (LinearLayout)getLayoutInflater()
				.inflate(R.layout.activity_createview, null);

		//����������activity�������
		setContentView(layout);

		//��̬�ڸ�Ŀ¼����ӿؼ�
		Button btnAddInRoot = new Button(this);
		btnAddInRoot.setText("��ͼ��Ŀ¼��̬��ӿؼ�");
		layout.addView(btnAddInRoot, new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT,  
				LayoutParams.WRAP_CONTENT));

		//��ȡ��activity����Ŀ¼���µ�ĳ���ӽڵ㣬�ڸ��ӽڵ�����ӿؼ�
		LinearLayout subLayout = (LinearLayout)findViewById(R.id.ll_subLinearLayout);
		Button btnCancel = new Button(this);
		btnCancel.setText("�ӽڵ�����ӿؼ�");
		subLayout.addView(btnCancel, 
				new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));

		//��ʱactivity���ֽṹ�Ѿ�������ϴ������ܹ����л�ͼ����
	}
}
