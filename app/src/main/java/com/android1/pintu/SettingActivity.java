package com.android1.pintu;

import com.android1.pintu.base.BaseActivity;
import com.android1.pintu.media.Music;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingActivity extends BaseActivity {
	private ImageView imageView_state,imageView_on,imageView_off;
	private ImageView imageView_up,imageView_down;
	private SeekBar seekBar_volume;
	private TextView textView_clear,textView_back;
	private AudioManager am;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		am=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
		initWidget();
	}
	//1.拿到对象   设置监听
	private void initWidget() {
		imageView_state=(ImageView) findViewById(R.id.imageView_state);
		imageView_on=(ImageView) findViewById(R.id.imageView_on);
		imageView_off=(ImageView) findViewById(R.id.imageView_off);
		imageView_up=(ImageView) findViewById(R.id.imageView_up);
		imageView_down=(ImageView) findViewById(R.id.imageView_down);
		seekBar_volume=(SeekBar) findViewById(R.id.seekBar_volume);
		textView_clear=(TextView) findViewById(R.id.textView_clear);
		textView_back=(TextView) findViewById(R.id.textView_back);
		imageView_on.setOnClickListener(l);
		imageView_off.setOnClickListener(l);
		imageView_up.setOnClickListener(l);
		imageView_down.setOnClickListener(l);
		textView_clear.setOnClickListener(l);
		textView_back.setOnClickListener(l);
		seekBar_volume.setMax(15);
		//界面的初始化。。。。。。。音乐的开关状态    拖动条的进度值。。。
		seekBar_volume.setProgress(Music.volume);
		if(Music.isMusicOn){
			imageView_state.setImageResource(R.drawable.audio_on);
		}else{
			imageView_state.setImageResource(R.drawable.audio_off);
		}		
		seekBar_volume.setOnSeekBarChangeListener(seekBarListener);
	}
	private View.OnClickListener l=new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {			
			switch(v.getId()){
			case R.id.imageView_on:
				//#1 改 变Music  记录音乐开关的变量 
				Music.isMusicOn=true;
				//音乐开
				Music.play(SettingActivity.this, R.raw.heros, true);
				//改变状态图标
				imageView_state.setImageResource(R.drawable.audio_on);
				break;
			case R.id.imageView_off:
				Music.isMusicOn=false;
				Music.stop();
				imageView_state.setImageResource(R.drawable.audio_off);
				break;
			case R.id.imageView_down:
				//#1设置 设备的音量
				am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0);
				//#3 音量值 保存				
				Music.volume=am.getStreamVolume(AudioManager.STREAM_MUSIC);
				//#2 改变进度条的进度值
//				seekBar_volume.setProgress(seekBar_volume.getProgress()-1);
				seekBar_volume.setProgress(Music.volume);
				break;
			case R.id.imageView_up:
				am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0);
				//#3 音量值 保存				
				Music.volume=am.getStreamVolume(AudioManager.STREAM_MUSIC);
				//#2 改变进度条的进度值
				seekBar_volume.setProgress(Music.volume);
				break;
			case R.id.textView_clear:
				break;
			case R.id.textView_back:
				Music.save2();//保存偏好设置
				finish();
				break;
			}
		}
	};
	private SeekBar.OnSeekBarChangeListener seekBarListener=new SeekBar.OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) { }
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) { }
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			//改变音量的大小  －－AudioManager    音量的值 保存 
			am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
			Music.volume=progress;
		}
	};
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Music.save();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
}
