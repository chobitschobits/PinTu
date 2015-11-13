package com.android1.pintu;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.android1.pintu.adapter.MyExpandableListAdapter;
import com.android1.pintu.adapter.RankAdapter2;
import com.android1.pintu.base.BaseActivity;
import com.android1.pintu.model.biz.ScoreBiz;
import com.android1.pintu.model.entity.Score;

public class RankActivity2 extends BaseActivity{
	private ExpandableListView expandableListView;
	private ScoreBiz biz;
	private List<String> groups;
	private List<List<Score>> childs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank);
		biz=new ScoreBiz(this);
//		initData();
//		expandableListView=(ExpandableListView) findViewById(R.id.expandableListView1);
//		RankAdapter2 adapter=new RankAdapter2(this, groups, childs);
		MyExpandableListAdapter adapter=new MyExpandableListAdapter(this, biz.getLevelCursor());		
		expandableListView.setAdapter(adapter);
	}
	private void initData() {
		groups=biz.getScoreLevels();
		
		childs=new ArrayList<List<Score>>();
				
		List<Score> score1=biz.getScoreByLevel(3);
		List<Score> score2=biz.getScoreByLevel(4);
		List<Score> score3=biz.getScoreByLevel(5);
		childs.add(score1);
		childs.add(score2);
		childs.add(score3);
	}
}
