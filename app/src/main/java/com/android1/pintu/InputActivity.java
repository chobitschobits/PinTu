package com.android1.pintu;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android1.pintu.base.BaseActivity;
import com.android1.pintu.model.biz.ScoreBiz;
import com.android1.pintu.model.biz.ScoreManager;
import com.android1.pintu.model.entity.Score;

public class InputActivity extends BaseActivity{
	private EditText editText_name;
	private Button button_ok;
	private String name;
	private long score_time;
	private ScoreManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		manager=new ScoreManager(this);
		loadName();
		score_time=getIntent().getLongExtra("score", 0);
		editText_name=(EditText) findViewById(R.id.editText_name);
		editText_name.setText(name);
		editText_name.selectAll();
		button_ok=(Button) findViewById(R.id.button_ok);
		button_ok.setOnClickListener(l);
	}
	
	private View.OnClickListener l=new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//#1 把这个名字 保存到文件  下次进来的时候读回来显示 
			if(editText_name.getText().toString().trim().equals("")){
				name="匿名";
			}else{
				name=editText_name.getText().toString().trim();
			}
			saveName();
			//#2把名字和分数 存入数据库
			
			ScoreBiz biz=new ScoreBiz(InputActivity.this);
			if(biz.saveScore(new Score(name, score_time, SelectActivity.level))){
				//gkegjgij
				
			}else{
				//gejogjgo
			}
			
			//#3 跳到排行榜界面 
			openActivity(RankActivity.class);
			//#4 关闭当前界面 
			finish();
		}
	};
	
	private void loadName() {
			SharedPreferences preferences=getSharedPreferences("pintusetting", MODE_PRIVATE);	
			name=preferences.getString("name", "匿名");
	}
	private void saveName() {
		SharedPreferences preferences=getSharedPreferences("pintusetting", MODE_PRIVATE);	
		Editor editor=preferences.edit();
		editor.putString("name", name);
		editor.commit();
	}
}
