package com.android1.pintu.sprite;

import android.graphics.RectF;

public class Block {
	/** 块对应的(矩形)碰撞区域*/
	public RectF rectF;
	/** 块对应的行、列值*/
	public int xline,yline;
	/** 块的坐标*/
	public int x,y;
	/** 块中图片的编号*/
	public int id;
	
	public Block(int level,int id){
		xline=id/level;
		yline=id%level;
		this.id=id;
		x=BlockGroup.baseX+yline*(ImageRect.imageW+1);
		y=BlockGroup.baseY+xline*(ImageRect.imageH+1);
		rectF=new RectF(x, y, x+ImageRect.imageW, y+ImageRect.imageH);
	}
	/**
	 * 判断块是否被点中
	 * @param touchX 触屏的坐标x
	 * @param touchY 触屏的坐标y
	 * @return 点中返回true 否则返回false
	 */
	public boolean isClick(float touchX,float touchY){
		return rectF.contains(touchX, touchY);
	}
}
