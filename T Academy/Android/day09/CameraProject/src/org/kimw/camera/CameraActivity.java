package org.kimw.camera;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class CameraActivity extends Activity {

	private static final String TAG = "CameraActivity"; 
	private static final boolean debug = true;


	private static final int IN_SAMPLE_SIZE = 8;

	private Camera mCamera;

	private boolean mInProgress;


	String saveName = "";
	SurfaceHolder holder = null;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		setContentView(R.layout.camera);    

		SurfaceView surface = (SurfaceView) findViewById(R.id.camera_menu_preview);
		holder = surface.getHolder();

		holder.addCallback(mSurfaceListener);
//		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		ImageButton button = (ImageButton) findViewById(R.id.camera_btn_pic);
		button.setOnClickListener(mButtonListener);
		button = (ImageButton) findViewById(R.id.camera_btn_close);
		button.setOnClickListener(mButtonListener);
	}

	private SurfaceHolder.Callback mSurfaceListener = 
			new SurfaceHolder.Callback() {

		public void surfaceCreated(SurfaceHolder holder) {
			mCamera = Camera.open();
			try{
				mCamera.setPreviewDisplay(holder);
			} catch (Exception e) {
			}
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			mCamera.release();
			mCamera = null;
		}

		public void surfaceChanged(SurfaceHolder holder, int format,
				int width, int height) {

			Camera.Parameters parameters = mCamera.getParameters();
			parameters.setPreviewSize(width, height);

			parameters.setRotation(90);

			mCamera.setDisplayOrientation(90);


			mCamera.setParameters(parameters);
			mCamera.startPreview();
		}
	};
	private View.OnClickListener mButtonListener = new View.OnClickListener() {

		public void onClick(View v) {
			int id = v.getId();
			if(id == R.id.camera_btn_pic){
				if (mCamera != null && mInProgress == false) {
					mCamera.takePicture( mShutterListener,
							null,  mPicutureListener); 
					mInProgress = true;
				}
			}else{
				close();
			}	                
		}
	};
	void close(){
		finish();
	}

	private String getDate(){
		String str = "";
		Calendar cal = Calendar.getInstance();

		str = String.format("%4d%02d%02d", cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
		return str;
	}
	public void writeBitmap( Bitmap bitmap ) 
	{ 

		FileOutputStream out = null;
		String DirPath = "";
		File f = null;

		try{

			DirPath = Environment.getExternalStorageDirectory().getAbsolutePath();       
			DirPath = DirPath + "/" + getDate() + "/";	         
			File cameraDir = new File(DirPath);
			if( !cameraDir.exists() ){
				cameraDir.mkdirs();
			}

			File cameraDir1 = new File(cameraDir, "imgfolder");
			if( !cameraDir1.exists() ){
				cameraDir1.mkdirs();
			}

			saveName = System.currentTimeMillis()+".jpg";
			f = new File(cameraDir1, saveName);

			out = new FileOutputStream( f );

			bitmap.compress(CompressFormat.JPEG, 80, out);

			if(debug){
				Log.w(TAG, "save Success :  " + saveName);
			}
		}catch (Exception e){
			if(debug){
				Log.w(TAG, "error : " + e);
			}
		}
		finally{
			try{
				out.close();
			}catch ( Exception e){}
			sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, 
					Uri.parse("file://" + f.getAbsolutePath() ))); 
		}
	}

	private Camera.ShutterCallback mShutterListener =
			new Camera.ShutterCallback() {
		public void onShutter() {
		}
	};

	private Camera.PictureCallback mPicutureListener =
			new Camera.PictureCallback() {

		public void onPictureTaken(byte[] data, Camera camera) {
			if (data != null) {				
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = IN_SAMPLE_SIZE;
				Bitmap bitmap =
						BitmapFactory.decodeByteArray(data, 0, data.length, options);
				//이미지 회전
				bitmap = rotate(bitmap, 90);
				
				writeBitmap(bitmap);
				camera.startPreview();
				mInProgress = false;
			}
		}
	};
	public Bitmap rotate(Bitmap bitmap, int degrees) {
		if (degrees != 0 && bitmap != null) {
			Matrix m = new Matrix();
			m.setRotate(degrees);
			try {
				Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), m, true);
				if (bitmap != converted) {
					bitmap = null;
					bitmap = converted;
					converted = null;
				}
			} catch (OutOfMemoryError ex) {
				Toast.makeText(getApplicationContext(), "memoryout", 0).show();
			}
		}
		return bitmap;
	}
}






