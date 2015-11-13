package com.android1.pintu.media;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.android1.pintu.base.BaseActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.BaseAdapter;

public class Music {
	//音乐的开  /关 boolean
	public static boolean isMusicOn;
	//音量    int 
	public static int volume;
	
	private static MediaPlayer mediaPlayer;
	private static int musicId;
	
	public static void init(Context context){
		//设置音量
		AudioManager am=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		//	am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0);//按方向  增加  或 减小 音量
		am.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);// 设置一个音量值 
	}
	/**
	 * 播放音乐 
	 * @param context 
	 * @param resId 音乐资源Id
	 * @param isLoop 是否循环播放
	 */
	public static void play(Context context,int resId,boolean isLoop){
		if(isMusicOn==false){
			return ;
		}
		//判断 当前正在播放想要播放歌曲
		if(mediaPlayer!=null && mediaPlayer.isPlaying() && musicId==resId){
			return ;
		}
		musicId=resId;
		if(mediaPlayer!=null){
			mediaPlayer.reset();
		}
		mediaPlayer=MediaPlayer.create(context, musicId);
		mediaPlayer.setLooping(isLoop);//设置是否循环
		mediaPlayer.start();
	}
	
	public static void stop(){
		if(mediaPlayer==null){
			return;
		}
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer=null;
		}
	}	
	
	public static void save(){
		//文件流的   开关   大小 
		try {
			FileOutputStream fos=BaseActivity.currentActivity.openFileOutput("music.and1", Context.MODE_PRIVATE);
			DataOutputStream dos=new DataOutputStream(fos);
			dos.writeBoolean(Music.isMusicOn);
			dos.writeInt(Music.volume);
			dos.flush();
			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 		
	}
	public static void load(){
		try {
			DataInputStream dis=new DataInputStream(BaseActivity.currentActivity.openFileInput("music.and1"));
			Music.isMusicOn=dis.readBoolean();
			Music.volume=dis.readInt();
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//设置音量
		
	}
	
	public static void save2(){
		SharedPreferences preferences=BaseActivity.currentActivity.getSharedPreferences("pintusetting", Context.MODE_PRIVATE);
		Editor editor=preferences.edit();
		editor.putBoolean("music", isMusicOn);
		editor.putInt("volume", volume);
		editor.commit();
	}
	
	public static void load2(){
		SharedPreferences preferences=BaseActivity.currentActivity.getSharedPreferences("pintusetting", Context.MODE_PRIVATE);
		isMusicOn=preferences.getBoolean("music", true);
		volume=preferences.getInt("volume", 10);
	}	
}
