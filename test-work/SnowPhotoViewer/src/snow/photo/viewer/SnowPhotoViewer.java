package snow.photo.viewer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class SnowPhotoViewer extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnDownload = (Button)findViewById(R.id.btnButton);
        btnDownload.setOnClickListener(myButtonClick);
    }
    
    Button.OnClickListener myButtonClick = new Button.OnClickListener()
    {
    	public void onClick(View v)
    	{
    		String [] strURLS = {"http://cfile25.uf.tistory.com/image/112CA2274C2220D2B47CB1", 
    							"http://cfile24.uf.tistory.com/image/110475474D666C30382331",
    							"http://cfile10.uf.tistory.com/image/160475474D666C343CD190",
    							};
    		
    		GridView gv = (GridView)findViewById(R.id.gvGridView);
    		ImageAdapter imgAdapter = new ImageAdapter(strURLS);

    		gv.setAdapter(imgAdapter);
    	}
    };
}