package com.android1.pintu.model.biz;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android1.pintu.model.db.DBOpenHelper;
import com.android1.pintu.model.entity.Score;

public class ScoreBiz {
	private DBOpenHelper helper=null;
	public ScoreBiz(Context context){
		helper=new DBOpenHelper(context);
	}
	
	//add
	public boolean saveScore(Score score){
		SQLiteDatabase db=null;
		try {
			db=helper.getWritableDatabase();
			db.execSQL("insert into score (name,time,level_id) values (?,?,?)",new Object[]{score.name,score.time,score.level_id});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(db!=null)
				db.close();
		}
		return false;
	}
	
	//查询 等级
	public List<String> getScoreLevels(){
		List<String> levels=new ArrayList<String>();
		SQLiteDatabase db=null;
		try {
			db=helper.getReadableDatabase();
			Cursor cursor=db.rawQuery("select * from level", null);
			while(cursor.moveToNext()){
				levels.add(cursor.getString(cursor.getColumnIndex("name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(db!=null)
				db.close();
		}
		return levels;
	}
	
	
	//查询某个等级的成绩 
	public List<Score> getScoreByLevel(int level_id){
		List<Score> scores=new ArrayList<Score>();
		SQLiteDatabase db=null;
		try {
			db=helper.getReadableDatabase();
			Cursor cursor=db.rawQuery("select _id,name,time from score where level_id=? order by time asc limit 10",new String[]{level_id+""});
			int id=1;
			while(cursor.moveToNext()){
				String name=cursor.getString(cursor.getColumnIndex("name"));
				int time=cursor.getInt(cursor.getColumnIndex("time"));
				Score score=new Score(id, name, time, level_id);
				scores.add(score);
				id++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(db!=null)
				db.close();
		}
		return scores;
	}
	
	//查询等级   返回Cursor
	public Cursor getLevelCursor(){
		SQLiteDatabase db=helper.getReadableDatabase();
		return db.rawQuery("select * from level", null);
	}
	
	//查询某个等级的成绩的Cursor
	public Cursor getScoreCursor(Cursor groupCursor){
		int level_id=groupCursor.getInt(groupCursor.getColumnIndex("_id"));
		SQLiteDatabase db=helper.getReadableDatabase();
		return db.rawQuery("select _id,name,time from score where level_id=?", new String[]{level_id+""});
	}
	
	
	
	
}






