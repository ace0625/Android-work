package org.androidtown.animation.graph;

import android.graphics.drawable.Drawable;

public class CustomItem {

	/**
	 * Icon
	 */
	private Drawable gIcon;
	
	/**
	 * String
	 */
	private String gName;
	
	/**
	 * expiration date in milliseconds
	 */
	private long gExpired;	

	/**
	 * initial date in milliseconds
	 */
	private long gInitial;	
	
	/**
	 * True if this item is selectable
	 */
	private boolean isSelectable = true;

	/**
	 * Initialize with icon and strings
	 * 
	 * @param icon
	 * @param prodName
	 * @param expiringDate
	 * @param initial
	 */
	public CustomItem(Drawable icon, String prodName, long expiringDate, long initialDate) {
		gIcon = icon;		
		gName = prodName;
		gExpired = expiringDate;
		gInitial = initialDate;
	}
	
	/**
	 * True if this item is selectable
	 */
	public boolean isSelectable() {
		return isSelectable;
	}

	/**
	 * Set selectable flag
	 */
	public void setSelectable(boolean selectable) {
		isSelectable = selectable;
	}

	/**
	 * Set icon
	 * 
	 * @param icon
	 */
	public void setIcon(Drawable icon) {
		gIcon = icon;
	}

	/**
	 * Get icon
	 * 
	 * @return
	 */
	public Drawable getIcon() {
		return gIcon;
	}
	
	/**
	 * Get String
	 */
	public String getName() {
		return gName;
	}

	/**
	 * Get String
	 */
	public long getExpiredDate() {
		return gExpired;
	}
	
	public long getInitialDate() {
		return gInitial;
	}
	
}