package com.android1.pintu.adapter;

import com.android1.pintu.SelectActivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class PicAdapter extends BaseAdapter{
	private Context context;
	private int[] picIds;
	
	public PicAdapter(Context context,int[] picIds){
		this.context=context;
		this.picIds=picIds;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return picIds.length;
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
		ImageView iv=new ImageView(context);
		iv.setImageResource(picIds[position]);
		iv.setLayoutParams(new Gallery.LayoutParams(SelectActivity.screenW*80/100, SelectActivity.screenH*80/100-100));
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		return iv;
	}

}
