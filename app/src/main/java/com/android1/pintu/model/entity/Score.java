package com.android1.pintu.model.entity;

public class Score {
	public int id;
	public String name;
	public long time;
	public int level_id;
	/**
	 * 构造方法
	 * @param name 玩家大名
	 * @param time  玩家的分数(时间，单位：秒)
	 * @param level_id  游戏等级(3、4、5)
	 */
	public Score(String name, long time, int level_id) {
		this.name = name;
		this.time = time;
//		switch(level_id){
//		case 3:
//			this.level_id=1;
//			break;
//		case 4:
//			this.level_id=2;
//			break;
//		case 5:
//			this.level_id=3;
//			break;
//		}
		this.level_id=level_id;
	}
	/**
	 * 构造方法
	 * @param name 玩家大名
	 * @param time  玩家的分数(时间，单位：秒)
	 * @param level_id  游戏等级(3、4、5)
	 */
	public Score(int id, String name, long time, int level_id) {
		this.id = id;
		this.name = name;
		this.time = time;
//		switch(level_id){
//		case 3:
//			this.level_id=1;
//			break;
//		case 4:
//			this.level_id=2;
//			break;
//		case 5:
//			this.level_id=3;
//			break;
//		}
		this.level_id=level_id;
	}
}
