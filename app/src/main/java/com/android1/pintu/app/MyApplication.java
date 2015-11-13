package com.android1.pintu.app;

import java.util.HashMap;

import android.app.Application;

import com.android1.pintu.util.LogUtil;

public class MyApplication extends Application{
	
	private static MyApplication application;
	
	public static MyApplication getApplication(){
		return application;
	}
	
	private HashMap<String, Object> globalData=new HashMap<String,Object>();

	@Override
	public void onCreate() {
		super.onCreate();
		LogUtil.i("MyApplication--->onCreate()");
		application=this;
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		LogUtil.i("MyApplication--->onTerminate()");
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		LogUtil.i("MyApplication--->onLowMemory()");
	}
	
	//存
	public void putGlobalData(String key,Object value){
		globalData.put(key, value);
	}
	
	//取
	public Object getGlobalData(String key){
		return globalData.get(key);
	}
	
	
	//清空
	public void clearGlobalData(){
		globalData.clear();
	}
	
	//数量
	public int getSize(){
		return globalData.size();
	}
	
	
}
