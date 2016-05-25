package org.kimjw.navermovie;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private int layout;
	Channel channel;
	Dialog dialog;
	public MyAdapter(Context context, int layout, Channel channel){
		this.context = context;
		this.layout = layout;
		this.channel = channel;
	}
	public void setData(Channel channel){
		this.channel = channel;
	}
	public int getCount() {
		return channel.getData().size();
	}
	public Object getItem(int position) {
		return channel.getData().get(position).getTitle();
	}
	public long getItemId(int position) {
		return position;
	}

	void viewDialog(int pos, ImageView imga){
		dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog);
		
		dialog.setTitle(channel.getData().get(pos).getTitle());
		
		TextView tv = (TextView)dialog.findViewById(R.id.diatitle1);
		tv.setText(channel.getData().get(pos).getSubTitle());
		
		tv = (TextView)dialog.findViewById(R.id.diaactor);
		tv.setText(channel.getData().get(pos).getActor());
		tv = (TextView)dialog.findViewById(R.id.diaderector);
		tv.setText(channel.getData().get(pos).getDirector());
		
		tv = (TextView)dialog.findViewById(R.id.diayear);
		tv.setText(channel.getData().get(pos).getPubDate());
		
		tv = (TextView)dialog.findViewById(R.id.diaurl);
		tv.setText(channel.getData().get(pos).getLink());
		
		tv = (TextView)dialog.findViewById(R.id.diatextgrade);
		String str = channel.getData().get(pos).getUserRating();
		tv.setText(str);
		RatingBar star = (RatingBar)dialog.findViewById(R.id.ratingBar1);
		star.setRating(Float.parseFloat(str)/2);
		ImageView img = (ImageView)dialog.findViewById(R.id.diaimg);
		imga.setDrawingCacheEnabled(true);
		img.setImageBitmap( imga.getDrawingCache() );

		
		Button btn = (Button)dialog.findViewById(R.id.diaclose);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();				
			}
		});
		dialog.show();
	}
	public View getView(final int position, View cView, ViewGroup parent) {
		if(cView == null){
			cView = View.inflate(context, layout, null);			
		}
		TextView tv = (TextView)cView.findViewById(R.id.txttitle);
		tv.setText(channel.getData().get(position).getTitle());
		tv = (TextView)cView.findViewById(R.id.txtSub);
		tv.setText(channel.getData().get(position).getSubTitle());
		tv = (TextView)cView.findViewById(R.id.txtgrade);
		tv.setText(channel.getData().get(position).getUserRating());
		final ImageView img = (ImageView)cView.findViewById(R.id.img);
		
		cView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				viewDialog(position,img);
			}
		});
		
		String imgurl = channel.getData().get(position).getImage();
		if(!imgurl.equals("")){
			HttpGet get = null;
			HttpClient client = null;
			HttpResponse response = null;
			InputStream is = null;
			try{
				client = NetManager.getHttpClient();
				get = NetManager.getGet(imgurl);
				response = client.execute(get);
				int code = response.getStatusLine().getStatusCode();
				switch(code){
				case 200 :
					is = response.getEntity().getContent();
					Bitmap bitmap = BitmapFactory.decodeStream(is);
					is.close();
					img.setImageBitmap(bitmap);
				}
			}catch(Exception e){
				Log.v("MainActivity", "이미지 설정 에러");
			}
		}else{
			img.setImageResource(R.drawable.noimage);
		}
		return cView;
	}
}
