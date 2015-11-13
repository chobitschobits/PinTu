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

public class RankAdapter extends BaseExpandableListAdapter{
	private Context context;
	private LayoutInflater inflater;
	private List<String> groups;
	private List<List<Score>> childs;
	
	public RankAdapter(Context context,List<String> groups,List<List<Score>> childs){
		this.context=context;
		inflater=LayoutInflater.from(context);
		this.groups=groups;
		this.childs=childs;
	}
	/****************group***********************************************/
	@Override
	public int getGroupCount() {
		return groups.size();
	}
	@Override
	public String getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView tv_group=new TextView(context);
		tv_group.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,50));
		tv_group.setPadding(50, 0, 0, 0);
		tv_group.setTextSize(20);
		tv_group.setTextColor(Color.WHITE);
		tv_group.setText(groups.get(groupPosition));
		return tv_group;
	}
	/****************child***********************************************/
	@Override
	public int getChildrenCount(int groupPosition) {
		return childs.get(groupPosition).size();
	}
	@Override
	public Score getChild(int groupPosition, int childPosition) {
		return childs.get(groupPosition).get(childPosition);
	}
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		convertView=inflater.inflate(R.layout.item_list_score, null);
		TextView tv_id=(TextView) convertView.findViewById(R.id.textView_id);
		TextView tv_time=(TextView) convertView.findViewById(R.id.textView_time);
		TextView tv_name=(TextView) convertView.findViewById(R.id.textView_name);
		Score score=childs.get(groupPosition).get(childPosition);
		tv_id.setText(score.id+"");
		tv_time.setText(score.time+"");
		tv_name.setText(score.name);
		return convertView;
	}
	@Override
	public boolean hasStableIds() {
		return false;
	}
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
