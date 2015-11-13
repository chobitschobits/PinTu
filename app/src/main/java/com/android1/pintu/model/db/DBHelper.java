package com.android1.pintu.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	private static final String DB_NAME="pintu.db";
	private static final int DB_VERSION=1;
	
	public DBHelper(Context context){
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table level (_id integer primary key,name varchar(10))");
		db.execSQL("create table score (_id integer primary key,name varchar(100) not null,time integer not null,level_id integer not null)");
		db.execSQL("insert into level (name) values ('简单')");
		db.execSQL("insert into level (name) values ('普通')");
		db.execSQL("insert into level (name) values ('困难')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
