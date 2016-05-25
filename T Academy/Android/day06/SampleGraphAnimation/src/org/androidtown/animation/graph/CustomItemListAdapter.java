package org.androidtown.animation.graph;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

public class CustomItemListAdapter extends BaseAdapter {

	private Context mContext;

	private List<CustomItem> gItems = new ArrayList<CustomItem>();

	public static Animation growAnim;


	public CustomItemListAdapter(Context context) {
		mContext = context;
		growAnim = AnimationUtils.loadAnimation(mContext, R.anim.grow);
	}

	public void addItem(CustomItem it) {
		gItems.add(it);
	}

	public void setListItems(List<CustomItem> lit) {
		gItems = lit;
	}

	public int getCount() {
		return gItems.size();
	}

	public Object getItem(int position) {
		return gItems.get(position);
	}

	public boolean areAllItemsSelectable() {
		return false;
	}

	public boolean isSelectable(int position) {
		try {
			return gItems.get(position).isSelectable();
		} catch (IndexOutOfBoundsException ex) {
			return false;
		}
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		CustomItemView itemView;
		if (convertView == null) {
			itemView = new CustomItemView(mContext, gItems.get(position));
		} else {
			itemView = (CustomItemView) convertView;

			itemView.setIcon(gItems.get(position).getIcon());
			itemView.setText(gItems.get(position).getName());
			itemView.setExpired(gItems.get(position).getExpiredDate());
			itemView.setInital(gItems.get(position).getInitialDate());
		}

		return itemView;
	}


}

