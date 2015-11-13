package com.android1.pintu.adapter;

import com.android1.pintu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HelpAdapter extends BaseAdapter{
	private String[] titles;
	private String[] contents;
	private LayoutInflater inflater;
	
	public HelpAdapter(Context context){
		titles=context.getResources().getStringArray(R.array.help_title);
		contents=context.getResources().getStringArray(R.array.help_content);
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return titles.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView=inflater.inflate(R.layout.list_item_help, null);
		TextView tv_title=(TextView) convertView.findViewById(R.id.textView_title);
		final TextView tv_content=(TextView) convertView.findViewById(R.id.textView_content);
		tv_title.setText(titles[position]);
		tv_content.setText(contents[position]);	
		tv_title.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(tv_content.getVisibility()==View.VISIBLE){
					tv_content.setVisibility(View.GONE);
				}else{
					tv_content.setVisibility(View.VISIBLE);
				}
			}
		});
		return convertView;
	}
}
