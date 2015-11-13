package com.android1.pintu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;

import com.android1.pintu.adapter.PicAdapter;
import com.android1.pintu.base.BaseActivity;
import com.android1.pintu.media.Music;

public class SelectActivity extends BaseActivity {
	public static final int[] picIds={R.drawable.b0,R.drawable.b1,R.drawable.b2,R.drawable.b3,
		R.drawable.b4,R.drawable.b5,R.drawable.b6,R.drawable.b7,R.drawable.b8,R.drawable.b9};
	/** res id*/
	public static int selectPic;
	public static int level;//3  4  5 
	private Gallery gallery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		gallery=(Gallery) findViewById(R.id.gallery1);
		gallery.setAdapter(new PicAdapter(this, picIds));
		gallery.setOnItemClickListener(listener);
		Music.play(this, R.raw.heros, true);
	}
	private AdapterView.OnItemClickListener listener=new AdapterView.OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			//#1   确定了是哪张图片
			selectPic=picIds[position];
			//#2  弹个框框  选择难度
			showLevelDialog();
		}		
	};
	
	private void showLevelDialog(){
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.icon);
		builder.setTitle("请选择难度");
		builder.setPositiveButton("简单", dialogListener);
		builder.setNegativeButton("困难", dialogListener);
		builder.setNeutralButton("普通", dialogListener);
		builder.create().show();
	}
	private DialogInterface.OnClickListener dialogListener=new DialogInterface.OnClickListener(){

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch(which){
			case DialogInterface.BUTTON_POSITIVE:
				level=3;
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				level=5;
				break;
			case DialogInterface.BUTTON_NEUTRAL:
				level=4;
				break;
			}
			openActivity(GameActivity.class);
			finish();
		}		
	};
}
