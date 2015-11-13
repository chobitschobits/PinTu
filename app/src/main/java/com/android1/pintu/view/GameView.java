package com.android1.pintu.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.android1.pintu.GameActivity;
import com.android1.pintu.R;
import com.android1.pintu.SelectActivity;
import com.android1.pintu.base.BaseSurfaceView;
import com.android1.pintu.media.Music;
import com.android1.pintu.sprite.BlockGroup;

public class GameView extends BaseSurfaceView{
	public static final int GAMESTATE_READY=0;
	public static final int GAMESTATE_RUN=1;
	public static final int GAMESTATE_SHOW=2;
	public static final int GAMESTATE_REPLAY=3;
	public static final int GAMESTATE_WIN=4;
	
	public long score;
	private boolean isDebuge=true;
	private Context context;
	
	/** 游戏状态*/
	public int gameState;
	/** 游戏大图*/
	private Bitmap bitmap_game;
	/** 游戏小图*/
	private Bitmap bitmap_small;
	/** 时钟图片*/
	private Bitmap bitmap_clock;
	/** 回放图片*/
	private Bitmap bitmap_replay;
	
	private RectF rect_small;
	private RectF rect_replay;

	/** 游戏地图（格子组）*/
	private BlockGroup bg;
	/** 打乱的总帧数*/
	private int flushTotal;
	/** 打乱的当前帧数*/
	private int flushCount;
	/** 每帧打乱次数*/
	private int flushPerCount;
	/** 打乱字符串数组*/
	private String[] flush_strings;
	/** 打乱时提示字符串*/
	private String flushText;
	/** 记录是否发生的点击事件*/
	private boolean isTouch;
	/** 触屏点的坐标*/
	private float touchX,touchY;
	
	private int winText_X;
	
	private long startTime;
	private String gameTime;
	
	private int stepIndex;
	
	private int replayCount=0;

	public GameView(Context context) {
		super(context);
		this.context=context;
		 init();
	}
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		 init();
	}

	/** 数据的初始化*/
	private void init(){
		gameState=GAMESTATE_READY;
		bitmap_game=BitmapFactory.decodeResource(getResources(), SelectActivity.selectPic);
		bitmap_game=Bitmap.createScaledBitmap(bitmap_game, GameActivity.screenW-20, GameActivity.screenH-130, true);
		bitmap_small=Bitmap.createScaledBitmap(bitmap_game, 100, 100, true);
		bitmap_clock=BitmapFactory.decodeResource(getResources(), R.drawable.k);
		bitmap_replay=BitmapFactory.decodeResource(getResources(), R.drawable.up);
		rect_small=new RectF(10, 10, 110, 110);
		rect_replay=new RectF(220, 60, 220+bitmap_replay.getWidth(), 60+bitmap_replay.getHeight());
		
		bg=new BlockGroup(bitmap_game, SelectActivity.level, 10, 120);
		if(isDebuge){
			flushTotal=1;
			flushPerCount=1;
		}else{
			flushTotal=SelectActivity.level*10;
			flushPerCount=5;
		}
		flushCount=0;
		flush_strings=getResources().getStringArray(R.array.flush_strings);
		flushText=flush_strings[0];
		winText_X=400;
		gameTime="00:00:00";
	}
	
	@Override
	public void onTouch(MotionEvent event) {
		switch(gameState){
		case GAMESTATE_READY:
			break;
		case GAMESTATE_RUN:
			isTouch=true;
			touchX=event.getX();
			touchY=event.getY();
		   break;
		case GAMESTATE_SHOW:
			isTouch=true;
			break;
		case GAMESTATE_REPLAY:
			break;
		case GAMESTATE_WIN:
			break;
		}
	}
	
	@Override
	public void onUpdate() {
		switch(gameState){
		case GAMESTATE_READY:
			//打乱图片。。
			if(flushCount<flushTotal){
				bg.flushBlocks(flushPerCount);
				flushText=flush_strings[flushCount%flush_strings.length];
				flushCount++;
			}else{
				gameState=GAMESTATE_RUN;
				//计时
				startTime=System.currentTimeMillis();//毫秒
				//记录下打乱后的初始状态
				bg.markState();
			}
			break;
		case GAMESTATE_RUN:
			gameTime=getTime();
			if(isTouch){
				if(rect_small.contains(touchX, touchY)){//判断 小图
					gameState=GAMESTATE_SHOW;
				}else if(rect_replay.contains(touchX, touchY)){//判断 回放	
					if(bg.steps.size()>0){
						//还原初始状态
						bg.rebackState();
						stepIndex=0;
						gameState=GAMESTATE_REPLAY;					
					}
				}else if(bg.clickResponse(touchX, touchY)){//判断  地图
					Music.play(context,R.raw.win, false);
					gameState=GAMESTATE_WIN;
				}
				isTouch=false;
			}
		   break;
		case GAMESTATE_SHOW:
			if(isTouch){
				gameState=GAMESTATE_RUN;
				isTouch=false;
			}
			break;
		case GAMESTATE_REPLAY:			
			replayCount++;
			if(replayCount<5){
				return;
			}
			if(replayCount%2==0 && stepIndex<bg.steps.size()){
				int[] step=bg.steps.get(stepIndex);					
				bg.move(step);
				stepIndex++;
			}else if(stepIndex==bg.steps.size()){
				gameState=GAMESTATE_RUN;
			}
			break;
		case GAMESTATE_WIN:			
			if(winText_X<50){
				isRun=false;
				GameActivity.gameActivity.gotoInput();				
			}else{
				winText_X=winText_X-10;
			}
			break;
		}
	}
	
	private String getTime() {
		long currentTime=System.currentTimeMillis();
		long time=(currentTime-startTime)/1000;		
		score=time;
		long h=time/3600;
		long m=time%3600/60;
		long s=time%60;
		return ((h<10)?"0"+h:h)+":"+((m<10)?"0"+m:m)+":"+((s<10)?"0"+s:s);
	}
	@Override
	public void onPaint(Canvas canvas, Paint paint) {
		switch(gameState){
		case GAMESTATE_READY:
			//画文本
			paint.setColor(Color.YELLOW);
			paint.setTextSize(30);
			canvas.drawText(flushText, 50, 90, paint);
			//画地图
			bg.paint(canvas, paint);
			break;
		case GAMESTATE_RUN:
			paintRun(canvas,paint);
		   break;
		case GAMESTATE_SHOW:
			canvas.drawBitmap(bitmap_game, 10, 10, paint);
			break;
		case GAMESTATE_REPLAY:
			bg.paint(canvas, paint);
			break;
		case GAMESTATE_WIN:
			canvas.drawBitmap(bitmap_game, 10, 10, paint);
			paint.setColor(Color.YELLOW);
			paint.setTextSize(30);
			canvas.drawText(getResources().getString(R.string.game_win), winText_X, 750, paint);
			break;
		}
	}
	
	/** 运行状态时的绘制*/
	private void paintRun(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bitmap_small, 10, 10,paint);
		canvas.drawBitmap(bitmap_clock, 220, 10, paint);
		canvas.drawBitmap(bitmap_replay, 220, 60, paint);
	//	canvas.drawBitmap(bitmap_game, 10, 120, paint);
		bg.paint(canvas, paint);
		paint.setTextSize(40);
		paint.setColor(Color.YELLOW);
		canvas.drawText(gameTime, 275, 50, paint);
	}
}
