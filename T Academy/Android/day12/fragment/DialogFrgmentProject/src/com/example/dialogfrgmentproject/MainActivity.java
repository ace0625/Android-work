package com.example.dialogfrgmentproject;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	int mStackLevel = 0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View tv = findViewById(R.id.text);
		((TextView)tv).setText("다이얼로그 프레그먼트를 보여주는 예제입니다.  "
				+ "첫번째 다이얼로그를 보려면 아래 '보여주기'버튼을 눌러주세요."
				+ "'보여주기'버튼을 계속 누를 경우에 다른 다이얼로그 스타일들이"
				+ "계속 보여주고 스택에 쌓여지고 '뒤로'버튼을 누르면 이전 다이얼로그가 보여주게됩니다."
				);

		Button button = (Button)findViewById(R.id.show);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog();
			}
		});

		if (savedInstanceState != null) {
			mStackLevel = savedInstanceState.getInt("level");
		}
	}
	@Override

	public void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		outState.putInt("level", mStackLevel);

	}

	void showDialog() {
		mStackLevel++;

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
		if (prev != null) {
			ft.remove(prev);
		}

		ft.addToBackStack(null);


		DialogFragment newFragment = MyDialogFragment.newInstance(mStackLevel);
		newFragment.show(ft, "dialog");
	}

	public static class MyDialogFragment extends DialogFragment {

		int mNum;


		static MyDialogFragment newInstance(int num) {

			MyDialogFragment f = new MyDialogFragment();


			// Supply num input as an argument.

			Bundle args = new Bundle();

			args.putInt("num", num);

			f.setArguments(args);


			return f;

		}


		@Override

		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);

			mNum = getArguments().getInt("num");


			// Pick a style based on the num.

			int style = DialogFragment.STYLE_NORMAL, theme = 0;

			switch ((mNum-1)%6) {

			case 1: style = DialogFragment.STYLE_NO_TITLE; break;

			case 2: style = DialogFragment.STYLE_NO_FRAME; break;

			case 3: style = DialogFragment.STYLE_NO_INPUT; break;

			case 4: style = DialogFragment.STYLE_NORMAL; break;

			case 5: style = DialogFragment.STYLE_NORMAL; break;

			case 6: style = DialogFragment.STYLE_NO_TITLE; break;

			case 7: style = DialogFragment.STYLE_NO_FRAME; break;

			case 8: style = DialogFragment.STYLE_NORMAL; break;

			}

			switch ((mNum-1)%6) {

			case 4: theme = android.R.style.Theme_Holo; break;

			case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;

			case 6: theme = android.R.style.Theme_Holo_Light; break;

			case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;

			case 8: theme = android.R.style.Theme_Holo_Light; break;

			}

//			setStyle(style, theme);

		}


		@Override

		public View onCreateView(LayoutInflater inflater, ViewGroup container,

				Bundle savedInstanceState) {

			View v = inflater.inflate(R.layout.fragment_dialog, container, false);

			View tv = v.findViewById(R.id.text);

			((TextView)tv).setText("Dialog #" + mNum + ": using style "

	                + mNum);


			// Watch for button clicks.

			Button button = (Button)v.findViewById(R.id.show);

			button.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {

					// When button is clicked, call up to owning activity.

//					((MainActivity)getActivity()).showDialog();
					dismiss();

				}

			});


			return v;

		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
