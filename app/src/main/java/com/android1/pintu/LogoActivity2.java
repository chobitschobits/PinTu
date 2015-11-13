package com.android1.pintu;

import android.os.Bundle;

import com.android1.pintu.base.BaseActivity;
/*
提交
 */
public class LogoActivity2 extends BaseActivity{
	public static LogoActivity2 logoActivity2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo2);
		logoActivity2=this;
	}
	

	public void openMenu(){
		openActivity(MenuActivity.class);
		this.finish();
	}

}
