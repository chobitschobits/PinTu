package com.android1.pintu.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android1.pintu.R;
import com.android1.pintu.model.entity.Score;

public class RankAdapter2 extends BaseExpandableListAdapter{
	private Context context;
	private LayoutInflater inflater;
	private List<String> groups;
	private List<List<Score>>  childs;
	
	public RankAdapter2(Context context,List<String> groups,List<List<Score>>  childs){
		this.context=context;
		this.groups=groups;
		this.childs=childs;
		this.inflater=LayoutInflater.from(context);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childs.get(groupPosition).size();
	}

	@Override
	public String getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public Score getChild(int groupPosition, int childPosition) {
		return childs.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView tv_group=new TextView(context);
		tv_group.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 50));
		tv_group.setTextSize(20);
		tv_group.setTextColor(Color.WHITE);
		tv_group.setPadding(55, 0, 0, 0);
		tv_group.setText(groups.get(groupPosition));
		return tv_group;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		convertView=inflater.inflate(R.layout.item_list_score, null);
		TextView textView_id=(TextView) convertView.findViewById(R.id.textView_id);
		TextView textView_name=(TextView) convertView.findViewById(R.id.textView_name);
		TextView textView_time=(TextView) convertView.findViewById(R.id.textView_time);
		Score score=childs.get(groupPosition).get(childPosition);
		textView_id.setText(score.id+"");
		textView_name.setText(score.name);
		textView_time.setText(score.time+"");
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
