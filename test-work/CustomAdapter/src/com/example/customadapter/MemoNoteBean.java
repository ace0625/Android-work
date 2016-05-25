package com.example.customadapter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class MemoNoteBean extends Activity { 

	ListView listViewMemo ;
	 MemoNoteCustomAdapter customAdapter ;
	 MemoNoteBean memoNoteBean = new MemoNoteBean() ;
	 ArrayList<MemoNoteBean> arrayList = new ArrayList<MemoNoteBean>() ; 
	 
	 Button btnCancel, btnTerminate ;
	 
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.memolist);
	        
	        listViewMemo = (ListView)findViewById(R.id.listViewMemo) ;
	        btnCancel = (Button)findViewById(R.id.btnCancel) ;
	        btnTerminate = (Button)findViewById(R.id.btnTerminate) ;
	        
	        customAdapter = new MemoNoteCustomAdapter(this, R.layout.custom, arrayList) ; 
	        listViewMemo.setAdapter(customAdapter) ;
	        
	    }
	}