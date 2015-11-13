package com.android1.pintu;

import com.android1.pintu.base.BaseActivity;
import com.android1.pintu.media.Music;
import com.android1.pintu.util.LogUtil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends BaseActivity {
	private ImageView imageView_game,imageView_rank,imageView_setting;
	private ImageView imageView_help,imageView_about,imageView_exit;
	private AlertDialog aboutDialog,exitDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		initWidget();
		//加载偏好设置 
		Music.load2();
		Music.init(this);
	}
	@Override
	protected void onResume() {
		super.onResume();
		Music.play(this, R.raw.heros, true);
	}
	
	/**
	 * 初始化控件 并设置监听器
	 */
	private void initWidget() {
		imageView_game=(ImageView) findViewById(R.id.imageView_game);
		imageView_rank=(ImageView) findViewById(R.id.imageView_rank);
		imageView_setting=(ImageView) findViewById(R.id.imageView_setting);
		imageView_help=(ImageView) findViewById(R.id.imageView_help);
		imageView_about=(ImageView) findViewById(R.id.imageView_about);
		imageView_exit=(ImageView) findViewById(R.id.imageView_exit);
		imageView_game.setOnClickListener(l);
		imageView_rank.setOnClickListener(l);
		imageView_setting.setOnClickListener(l);
		imageView_help.setOnClickListener(l);
		imageView_about.setOnClickListener(l);
		imageView_exit.setOnClickListener(l);
	}	
	
	/**  单击事件监听器对象*/
	private View.OnClickListener l=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.imageView_game:
				openActivity(SelectActivity.class);
				break;
			case R.id.imageView_rank:
				openActivity(RankActivity.class);
				break;
			case R.id.imageView_setting:
				openActivity(SettingActivity.class);
				break;
			case R.id.imageView_help:
				openActivity(HelpActivity.class);
				break;
			case R.id.imageView_about:
				showAboutDialog();
				break;
			case R.id.imageView_exit:
				showExitDialog();
				break;
			}
		}
	};
	
	/** 显示“关于”对话框*/
	protected void showAboutDialog() {
		if(aboutDialog==null){
//			aboutDialog=new AlertDialog.Builder(this)
//			.setIcon(R.drawable.icon)
//			.setTitle(R.string.about_title)
//			.setMessage(R.string.about_msg)
//			.setPositiveButton(R.string.btn_ok, null)
//			.create();		
			
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.icon);
			builder.setTitle(R.string.about_title);
			builder.setMessage(R.string.about_msg);
			builder.setPositiveButton(R.string.btn_ok, null);
			
			aboutDialog=builder.create();
		}
		aboutDialog.show();
	}
	
	/** 显示“退出”对话框*/
	protected void showExitDialog() {
		if(exitDialog==null){
			exitDialog=new AlertDialog.Builder(this)
			.setIcon(R.drawable.icon)
			.setTitle(R.string.exit_title)
			.setMessage(R.string.exit_msg)
			.setPositiveButton(R.string.btn_ok, listener)
			.setNegativeButton(R.string.btn_cancel, null)
			.create();
		}
		exitDialog.show();
	}
	
	/** 对话框中 “确定“按钮的监听器对象*/
	private DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			Music.stop();
			finish();
		}
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			showExitDialog();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		if(event.getAction()==MotionEvent.ACTION_DOWN){
		//AudioManager am=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
//			int max=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//			int current=am.getStreamVolume(AudioManager.STREAM_MUSIC);
//			LogUtil.i("Music", "max:"+max);
//			LogUtil.i("Music", "current:"+current);
//		}
//		
//		return super.onTouchEvent(event);
//	}
}
