package com.android1.pintu.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	public static final String DB_NAME="rank.db";
	public static final int DB_VERSION=1;
	
	public DBOpenHelper(Context context){
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//#1 创建两张表
		db.execSQL("create table level (_id integer primary key, name varchar(100) not null )");
		db.execSQL("create tabel score (_id integer primary key,name varchar(100) not null,time integer not null,level_id integer not null )");
		//#2 插入 等级数据
		db.execSQL("insert into level (_id,name) values (3,'简单')");
		db.execSQL("insert into level (_id,name) values (4,'普通')");
		db.execSQL("insert into level (_id,name) values (5,'困难')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	

}
