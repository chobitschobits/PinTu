package com.android1.pintu.base;

import com.android1.pintu.util.LogUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class BaseSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
	private SurfaceHolder holder;
	protected boolean isRun;
	private Paint paint;
	private Thread thread;
	
	public BaseSurfaceView(Context context) {
		super(context);
		init();
	}
	public BaseSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	private void init() {
		holder=getHolder();
		holder.addCallback(this);
		paint=new Paint();
		//消除锯齿
		paint.setAntiAlias(true);
		paint.setFlags(Paint.ANTI_ALIAS_FLAG);
	}
	@Override
	public void run() {
		while(isRun){
			update();
			paint();
			try {
				Thread.sleep(1000/24);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			onTouch(event);
		}
		return true;
	}
	
	public void update(){
		onUpdate();
	}
	public void paint(){
		Canvas canvas=null;
		try {
			canvas=holder.lockCanvas();
			if(canvas!=null){
				canvas.drawColor(Color.BLACK);
				//画。。。。
				onPaint(canvas,paint);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(canvas!=null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		LogUtil.i("surfaceCreated()");
		thread=new Thread(this);
		isRun=true;
		thread.start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		LogUtil.i("surfaceChanged()");
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		LogUtil.i("surfaceDestroyed()");
			isRun=false;
	}
	
	public abstract void  onTouch(MotionEvent event);
	
	public abstract void onUpdate();
	
	public abstract void onPaint(Canvas canvas,Paint paint);
	

}
