package com.android1.pintu;

import com.android1.pintu.base.BaseActivity;
import com.android1.pintu.util.LogUtil;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LogoActivity extends BaseActivity {
	/**
	 * 开始的时候播放3动画 从看见到看不见
	 * 1。新建一个文件夹 用来放动画新建xml文件 点finish完成命名空间要粘过来<alph></alph>
	 * 2.
	 */
	private Animation animation;//新建一个动画先拿到它
	private ImageView imageView_mm,imageView_and1,imageView_logo;
	private int index=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		//先找到3张图片
		imageView_mm=(ImageView) findViewById(R.id.imageView_mm);
		imageView_and1=(ImageView) findViewById(R.id.imageView_and1);
		imageView_logo=(ImageView) findViewById(R.id.imageView_logo);

		animation=AnimationUtils.loadAnimation(this, R.anim.logo_anim2);//加载对象 找到动画的对象

		//animation=new AlphaAnimation(0.5f, 1.0f);
		//animation.setDuration(2000);
		animation.setAnimationListener(listener);
		
		imageView_mm.startAnimation(animation);	
		
	}
	
	private Animation.AnimationListener listener=new Animation.AnimationListener() {
		
		@Override
		public void onAnimationStart(Animation animation) {  }
		
		@Override
		public void onAnimationRepeat(Animation animation) {  }
		
		@Override
		public void onAnimationEnd(Animation animation) {
			index++;
			switch(index){
			case 1:
				imageView_mm.clearAnimation();
				imageView_mm.setVisibility(View.INVISIBLE);
				imageView_and1.startAnimation(animation);
				break;
			case 2:
				imageView_and1.clearAnimation();
				imageView_and1.setVisibility(View.INVISIBLE);
				imageView_logo.startAnimation(animation);
				break;
			case 3:
				imageView_logo.clearAnimation();
				imageView_logo.setVisibility(View.INVISIBLE);
				//跳转
				openActivity(MenuActivity.class);
				finish();
				break;
			}
		}
	};
	
}
