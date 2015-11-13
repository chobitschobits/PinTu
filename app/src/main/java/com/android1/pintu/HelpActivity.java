package com.android1.pintu;

import com.android1.pintu.adapter.HelpAdapter;
import com.android1.pintu.base.BaseActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
//classCastException
public class HelpActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		ListView lv=(ListView) findViewById(R.id.list);
		lv.setAdapter(new HelpAdapter(this));		
	}
}
