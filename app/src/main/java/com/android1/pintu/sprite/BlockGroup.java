package com.android1.pintu.sprite;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.YuvImage;

public class BlockGroup {
	/** 格子组的起始坐标 */
	public static int baseX, baseY;
	/** 图片数组 */
	public ImageRect[] images;
	/** 格子数组 */
	public Block[][] blocks;
	/** 游戏的大图 */
	public Bitmap bitmap;
	/** 游戏的等级 */
	public int level;
	/** 图片的数量 */
	public int level2;
	/** 白块的Id */
	public int whiteB;
	private Random random;
	
	private int[] startState;
	public ArrayList<int[]> steps;

	public BlockGroup(Bitmap bitmap, int level, int baseX, int baseY) {
		this.bitmap = bitmap;
		BlockGroup.baseX = baseX;
		BlockGroup.baseY = baseY;
		this.level = level;
		this.level2 = level * level;
		this.whiteB = level2 - 1;
		random = new Random();
		initImages();
		initBlocks();
		steps=new ArrayList<int[]>();
	}

	/**
	 * 初始化图片数组
	 */
	private void initImages() {
		images = new ImageRect[level2];
		for (int i = 0; i < level2; i++) {
			images[i] = new ImageRect(bitmap, level, i);
		}
	}

	/**
	 * 初始化块的数组
	 */
	private void initBlocks() {
		blocks = new Block[level][level];
		for (int i = 0; i < level2; i++) {
			blocks[i / level][i % level] = new Block(level, i);
		}
	}

	/**
	 * 地图（格子组）的绘制
	 */
	public void paint(Canvas canvas, Paint paint) {
		for (int i = 0; i < level; i++) {
			for (int j = 0; j < level; j++) {
				Block block = blocks[i][j];
				images[block.id].paint(canvas, block.x, block.y, paint);
			}
		}
	}

	/**
	 *  打乱
	 */
	public void flushBlocks(int num) {
		// 打乱num次
		for (int i = 0; i < num; i++) {
			// 打乱一次
			// autoChange();
			autoChange2();
		}
	}

	// 打乱一次
	private void autoChange() {
		// 随机生成 0~level2-1 不重复的随机数
		// 分别赋值给格子组中每个格子的id
		int[] ids = getIds();
		for (int i = 0; i < level2; i++) {
			blocks[i / level][i % level].id = ids[i];
		}
	}

	private int[] getIds() {
		int[] ids = new int[level2];
		int num;
		for (int i = 0; i < level2; i++) {
			boolean cf;
			do {
				cf = false;
				num = random.nextInt(level2);
				for (int j = 0; j < i; j++) {
					if (ids[j] == num) {
						cf = true;
						break;
					}
				}
			} while (cf);
			ids[i] = num;
		}
		return ids;
	}
	
	private int[] getIds2(){
		int[] ids=new int[level2];
		boolean[] bool=new boolean[level2];
		int num;
		for (int i = 0; i < level2; i++) {
			do{
				num=random.nextInt(level2);
			}while(bool[num]);
			//fuzhi
			ids[i]=num;
			bool[num]=true;
		}
		return ids;
	}
	
	// 打乱一次
	private void autoChange2() {
		//#1  找到白块 
		for(int i=0;i<level;i++){
			for(int j=0;j<level;j++){
				Block block=blocks[i][j];
				if(block.id==whiteB){
					do{
						int num=random.nextInt(4);//0 1 2 3
						switch(num){
						case 0://左边的换
							if(block.yline>0){//能换
								//交换
								int tempid=block.id;
								block.id=blocks[block.xline][block.yline-1].id;
								blocks[block.xline][block.yline-1].id=tempid;
								return;
							}
							break;
						case 1://跟右边的换
							if(block.yline<level-1){//能换
								//交换
								int tempid=block.id;
								block.id=blocks[block.xline][block.yline+1].id;
								blocks[block.xline][block.yline+1].id=tempid;
								return;
							}
							break;
						case 2://跟上边的换
							if(block.xline>0){//能换
								//交换
								int tempid=block.id;
								block.id=blocks[block.xline-1][block.yline].id;
								blocks[block.xline-1][block.yline].id=tempid;
								return;
							}
							break;
						case 3://跟下边的换
							if(block.xline<level-1){//能换
								//交换
								int tempid=block.id;
								block.id=blocks[block.xline+1][block.yline].id;
								blocks[block.xline+1][block.yline].id=tempid;
								return;
							}
							break;						
						}
					}while(true);				
				}
			}
			
		}
	}

	public boolean clickResponse(float touchX,float touchY){
		//#1.判断哪个块被点中了
		for(int i=0;i<level;i++){
			for(int j=0;j<level;j++){
				Block block=blocks[i][j];
				if(block.isClick(touchX, touchY)){
					//#2 如果某块被点中  判断它能否移动 如果能     移动 
					if(checkAndMove(block)){
						//#3 如果移动成功   判断是否胜利   如果胜利 返回true 通知改为胜利状态  其余情况都 返回false
						if(isWin()){
							return true;
						}
					}				
					break;
				}
			}
		}
		return false;		
	}
	private boolean checkAndMove(Block block){
		if(block.yline>0 && blocks[block.xline][block.yline-1].id==whiteB ){//跟左移动 
			//换
			int tempId=block.id;
			block.id=blocks[block.xline][block.yline-1].id;
			blocks[block.xline][block.yline-1].id=tempId;
			//记录步骤
			addStep(block, blocks[block.xline][block.yline-1]);
			//返回 true
			return true;
		}
		if(block.yline<level-1 && blocks[block.xline][block.yline+1].id==whiteB){//跟右边的换
			int tempId=block.id;
			block.id=blocks[block.xline][block.yline+1].id;
			blocks[block.xline][block.yline+1].id=tempId;
			//记录步骤
			addStep(block, blocks[block.xline][block.yline+1]);
			
			return true;
		}
		if(block.xline>0 && blocks[block.xline-1][block.yline].id==whiteB){
			int tempId=block.id;
			block.id=blocks[block.xline-1][block.yline].id;
			blocks[block.xline-1][block.yline].id=tempId;
			//记录步骤
			addStep(block, blocks[block.xline-1][block.yline]);
			
			return true;
		}
		if(block.xline<level-1 && blocks[block.xline+1][block.yline].id==whiteB){
			int tempId=block.id;
			block.id=blocks[block.xline+1][block.yline].id;
			blocks[block.xline+1][block.yline].id=tempId;
			//记录步骤
			addStep(block, blocks[block.xline+1][block.yline]);
			return true;
		}
		return false;
	}
	private boolean isWin(){
		for(int i=0;i<level2;i++){
			if(blocks[i/level] [ i%level].id!=i){
				return false;
			}
		}		
		return true;
	}
	
	public void markState(){
		startState=new int[level2];
		for(int i=0;i<level2;i++){
			startState[i]=blocks[i/level][i%level].id;
		}
	}
	
	public void rebackState(){
		if(startState!=null){
			for(int i=0;i<level2;i++){
				blocks[i/level][i%level].id=startState[i];
			}
		}
	}
	
	public void addStep(Block block1,Block block2){
		int[] step=new int[2];
		step[0]=block1.xline *level+block1.yline;
		step[1]=block2.xline*level+block2.yline;
		steps.add(step);
	}
	
	public void move(int[] step){
		int blockId1=step[0];
		int blockId2=step[1];
		Block block1=blocks[blockId1/level][blockId1%level];
		Block block2=blocks[blockId2/level][blockId2%level];
		int tempId=block1.id;
		block1.id=block2.id;
		block2.id=tempId;
	}
	
}
