package com.android1.pintu.model.biz;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android1.pintu.model.db.DBHelper;
import com.android1.pintu.model.entity.Score;

public class ScoreManager {
	private DBHelper helper;
	public ScoreManager(Context context){
		helper=new DBHelper(context);
	}
	
	public boolean saveScore(Score score){
		SQLiteDatabase db=null;
		try {
			db=helper.getWritableDatabase();
			db.execSQL("insert into score (name,time,level_id) values (?,?,?)", new Object[]{score.name,score.time,score.level_id});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(db!=null){
				db.close();
			}
		}
		return false;
	}


	public List<String> getScoreLevels(){
		List<String> levels=new ArrayList<String>();
		SQLiteDatabase db=null;
		try {
			db=helper.getReadableDatabase();
			Cursor cursor=db.rawQuery("select _id,name from level", null);
			while(cursor.moveToNext()){
				levels.add(cursor.getString(cursor.getColumnIndex("name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(db!=null){
				db.close();
			}
		}
		return levels;
	}


	public List<Score>  getScoresByLevel(int level){
		int level_id=1;
		switch(level){
		case 3:
			level_id=1;
			break;
		case 4:
			level_id=2;
			break;
		case 5:
			level_id=3;
			break;
		}
		List<Score> scores=new ArrayList<Score>();
		SQLiteDatabase db=null;
		try {
			db=helper.getReadableDatabase();
			Cursor cursor=db.rawQuery("select _id,name,time from score where level_id=? order by time limit 10", new String[]{level_id+""});
			int id=1;
			while(cursor.moveToNext()){
				String name=cursor.getString(cursor.getColumnIndex("name"));
				long time=cursor.getLong(cursor.getColumnIndex("time"));
				Score score=new Score(id, name, time, level);
				scores.add(score);
				id++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(db!=null){
				db.close();
			}
		}
		return scores;
	}
	
	public Cursor getScoreCursor(Cursor groupCursor){
		int level_id=groupCursor.getInt(groupCursor.getColumnIndex("_id"));
		SQLiteDatabase db=helper.getReadableDatabase();
		return db.rawQuery("select _id,time,name from score where level_id=?", new String[]{level_id+""});
	}

	public Cursor getScoreLevelCursor() {
		SQLiteDatabase db=helper.getReadableDatabase();
		return db.rawQuery("select * from level ", null);
	}
	
	
}
