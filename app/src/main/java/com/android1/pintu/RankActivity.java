package com.android1.pintu;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android1.pintu.adapter.RankAdapter;
import com.android1.pintu.adapter.RankCursorAdapter;
import com.android1.pintu.base.BaseActivity;
import com.android1.pintu.model.biz.ScoreManager;
import com.android1.pintu.model.entity.Score;

public class RankActivity extends BaseActivity {
	private ExpandableListView expandableListView;
	private ScoreManager manager;
	private List<String> groups;
	private List<List<Score>> childs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank);
		manager=new ScoreManager(this);
		expandableListView=(ExpandableListView) findViewById(R.id.expandableListView1);
		//#1
//		loadData();
//		ExpandableListAdapter adapter=new RankAdapter(this, groups, childs);
		//#2
		Cursor cursor=manager.getScoreLevelCursor();
		ExpandableListAdapter adapter=new RankCursorAdapter(this, cursor);
		
		expandableListView.setAdapter(adapter);
	}
	private void loadData() {
		groups=manager.getScoreLevels();		
		childs=new ArrayList<List<Score>>();
		for(int i=3;i<=5;i++){
			childs.add(manager.getScoresByLevel(i));
		}
	}
}
