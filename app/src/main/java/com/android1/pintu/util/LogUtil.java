package com.android1.pintu.util;

import android.content.Context;
import android.util.Log;

public class LogUtil{
	private static final String TAG="pintu";//标签
	private static boolean isDebug=true;//调试
	private static boolean isInfo=true;//信息
	private static boolean isWarning=true;//警告
	private static boolean isError=true;//错误 	
	
	public static void d(String msg){
		if(isDebug){
			Log.d(TAG, msg);
		}
	}	
	public static void d(String tag,String msg){
		if(isDebug){
			Log.d(tag, msg);
		}
	}	
	public static void d(Context context,String msg){
		if(isDebug){
			Log.d(context.getClass().getSimpleName(), msg);
		}
	}
	
	public static void i(String msg){
		if(isInfo){
			Log.i(TAG, msg);
		}
	}
	
	public static void i(String tag,String msg){
		if(isInfo){
			Log.i(tag, msg);
		}
	}
	
	public static void i(Context context,String msg){
		if(isInfo){
			Log.i(context.getClass().getSimpleName(), msg);
		}
	}
	
	
}
