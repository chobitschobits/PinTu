package com.android1.pintu.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android1.pintu.util.LogUtil;

public class BaseActivity extends Activity{
	public static Activity currentActivity;
	public static int screenW,screenH;
	private Toast toast;
	private Intent intent;
	//所有的Activity的共有特点 
	/****************生命周期  方法 ********************************************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentActivity=this;
		//获取 屏幕的宽、高  getDefaultDisplay()获取屏幕的显示

		screenW=getWindowManager().getDefaultDisplay().getWidth();		
		screenH=getWindowManager().getDefaultDisplay().getHeight();	
		
		LogUtil.i(this.getClass().getSimpleName()+"：onCreate()");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.i(this.getClass().getSimpleName()+"：onStart()");
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.i(this.getClass().getSimpleName()+"：onRestart()");
	}
	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.i(this.getClass().getSimpleName()+"：onResume()");
	}
	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.i(this.getClass().getSimpleName()+"：onPause()");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.i(this.getClass().getSimpleName()+"：onStop()");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogUtil.i(this.getClass().getSimpleName()+"：onDestroy()");
	}
	
	/*******界面的跳转************************************************************/
	protected void openActivity(Class<?> c){
		openActivity(c, null);
	}
	protected void openActivity(Class<?> cls,Bundle bundle){
		if(intent==null){
			intent=new Intent();
		}
		intent.setClass(this, cls);
		if(bundle!=null){
			intent.putExtras(bundle);
		}		
		startActivity(intent);
	}	
	
	/********Toast************************************************************************/
	//弹一个短的Toast
	protected void showShortToast(String msg){
		if(toast==null){
			toast=Toast.makeText(this, msg, Toast.LENGTH_SHORT);
		}
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setText(msg);
		toast.show();
	}
	protected void showShortToast(int resId){
		showShortToast(getString(resId));
	}	
	//弹一个长的Toast
	protected void showLongToast(String msg){
		if(toast==null){
			toast=Toast.makeText(this, msg, Toast.LENGTH_SHORT);
		}
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setText(msg);
		toast.show();
	}
	protected void showLongToast(int resId){
		showLongToast(getString(resId));
	}
	
}
