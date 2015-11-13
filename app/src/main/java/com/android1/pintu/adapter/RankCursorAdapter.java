package com.android1.pintu.adapter;

import com.android1.pintu.R;
import com.android1.pintu.model.biz.ScoreManager;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorTreeAdapter;

public class RankCursorAdapter extends SimpleCursorTreeAdapter{
	private ScoreManager manager;

	public RankCursorAdapter(Context context, Cursor cursor) {
		super(context, cursor, 
				android.R.layout.simple_expandable_list_item_1, 
				new String[]{"name"}, 
				new int[]{android.R.id.text1}, 
				R.layout.item_list_score, 
				new String[]{"_id","time","name"},
				new int[]{R.id.textView_id,R.id.textView_time,R.id.textView_name});		
		manager=new ScoreManager(context);
	}

	@Override
	protected Cursor getChildrenCursor(Cursor groupCursor) {
		return manager.getScoreCursor(groupCursor);
	}

}
