package com.android1.pintu.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ImageRect {
	/** 图片的宽高*/
	public static int imageW,imageH;
	/** 图片*/
	private Bitmap bitmap;
	/** 行、列值 */
	private int xline,yline;
	
	public ImageRect(Bitmap bigBitmap,int level,int id){
		if(id==0){
			imageW=bigBitmap.getWidth()/level;
			imageH=bigBitmap.getHeight()/level;
		}
		xline=id/level;
		yline=id%level;
		bitmap=Bitmap.createBitmap(bigBitmap, yline*imageW, xline*imageH, imageW, imageH);		
		//如果是最后一块 变成白色的
		if(id==level*level-1){
			Canvas canvas=new Canvas(bitmap);
			canvas.drawColor(Color.WHITE);
		}
	}
	/**
	 * 图片的绘制
	 * @param canvas 画布
	 * @param drawX 绘制的坐标点x
	 * @param drawY 绘制的坐标点y
	 * @param paint 画笔
	 */
	public void paint(Canvas canvas,int drawX,int drawY,Paint paint){
		canvas.drawBitmap(bitmap, drawX, drawY, paint);
	}
}
