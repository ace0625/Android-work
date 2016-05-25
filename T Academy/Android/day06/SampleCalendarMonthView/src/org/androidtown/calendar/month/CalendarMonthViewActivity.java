package org.androidtown.calendar.month;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

/**
 * Calendar Month View widget
 *
 * @author Mike
 */
public class CalendarMonthViewActivity extends Activity {

	CalendarMonthView monthView;
	CalendarMonthAdapter monthViewAdapter;

	TextView monthText;

	int curYear;
	int curMonth;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        monthView = (CalendarMonthView) findViewById(R.id.monthView);
        monthViewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);

        // set listener
        monthView.setOnDataSelectionListener(new OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v, int position, long id) {
				MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
				int day = curItem.getDay();

				//Toast.makeText(getApplicationContext(), "Selected : " + day, 1000).show();
				Log.d("CalendarMonthViewActivity", "Selected : " + day);

			}
		});

        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		monthViewAdapter.setPreviousMonth();
        		monthViewAdapter.notifyDataSetChanged();

        		setMonthText();
        	}
        });

        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		monthViewAdapter.setNextMonth();
        		monthViewAdapter.notifyDataSetChanged();

        		setMonthText();
        	}
        });


    }


    private void setMonthText() {
    	curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "³â " + (curMonth+1) + "¿ù");
    }

}