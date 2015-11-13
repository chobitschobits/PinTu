package com.android1.pintu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.android1.pintu.base.BaseActivity;
import com.android1.pintu.media.Music;
import com.android1.pintu.view.GameView;

public class GameActivity extends BaseActivity{
	public static GameActivity gameActivity;
	private GameView gameView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameView=new GameView(this);
		//setContentView(gameView);
		gameActivity=this;
		Music.play(this, R.raw.play, true);
	}
	@Override
	protected void onResume() {
		super.onResume();
		setContentView(gameView);
	}
	
	public void gotoInput(){
		Bundle bundle=new Bundle();
		bundle.putLong("score", gameView.score);
		openActivity(InputActivity.class,bundle);
		finish();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, R.string.game_menu_continue);
		menu.add(Menu.NONE, Menu.FIRST+1, Menu.NONE, R.string.game_menu_help);
		menu.add(Menu.NONE, Menu.FIRST+2, Menu.NONE, R.string.game_menu_setting);
		menu.add(Menu.NONE, Menu.FIRST+3, Menu.NONE, R.string.game_menu_menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case Menu.FIRST:
			break;
		case Menu.FIRST+1:
			openActivity(HelpActivity.class);
			break;
		case Menu.FIRST+2:
			openActivity(SettingActivity.class);
			break;
		case Menu.FIRST+3:
			showDialog();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showDialog() {
		new AlertDialog.Builder(this)
		.setIcon(R.drawable.icon)
		.setTitle(R.string.exit_title)
		.setMessage(R.string.game_dialog_back)
		.setPositiveButton(R.string.btn_ok,listener)
		.setNegativeButton(R.string.btn_cancel, null)
		.show();
	}
	
	private DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			openActivity(MenuActivity.class);			
		}
	};
}
