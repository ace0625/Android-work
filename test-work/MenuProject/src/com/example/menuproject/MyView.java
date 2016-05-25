package com.example.menuproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.widget.ImageView;

public class MyView extends ImageView {

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreateContextMenu(ContextMenu menu) {
		menu.setHeaderTitle("TITLE");
		menu.add(0, 101, 0, "이미지 복사");
		menu.add(0, 102, 0, "이미지 수정");
		menu.add(0, 103, 0, "이미지 삭제");
		super.onCreateContextMenu(menu);
	}

}
