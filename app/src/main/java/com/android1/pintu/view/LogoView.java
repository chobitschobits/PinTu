package com.android1.pintu.view;

import com.android1.pintu.LogoActivity2;
import com.android1.pintu.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LogoView extends View implements Runnable{
	private Paint paint=new Paint();
	private static final int[] resIds={R.drawable.mmlogo,R.drawable.and1,R.drawable.logo};
	private Bitmap[] bitmaps;
	private int index;

	public LogoView(Context context) {
		super(context);
		loadBitmap();
	}

	public LogoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		loadBitmap();
	}
	
	private void loadBitmap(){
		bitmaps=new Bitmap[resIds.length];
		for(int i=0;i<resIds.length;i++){
			bitmaps[i]=BitmapFactory.decodeResource(getResources(), resIds[i]);
			bitmaps[i]=Bitmap.createScaledBitmap(bitmaps[i], LogoActivity2.screenW, LogoActivity2.screenH, true);
		}
		index=0;
		new Thread(this).start();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(bitmaps[index], 0, 0, paint);		
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			index++;
			if(index==3){
				break;
			}
			postInvalidate();//让当前控件界面失效，通知它重绘			
		}
		//跳转
		LogoActivity2.logoActivity2.openMenu();
	}
}
