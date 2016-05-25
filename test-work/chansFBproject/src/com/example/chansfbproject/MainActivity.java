package com.example.chansfbproject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.OnErrorListener;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
    private static final String PENDING_PUBLISH_KEY = "pendingPublishReauthorization";
    private boolean pendingPublishReauthorization = false;
    
    Button shareButton = null;
    LoginButton authButton = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		  Add code to print out the key hash
//		try {
//	        PackageInfo info = getPackageManager().getPackageInfo(
//	                "com.example.chansfbproject", 
//	                PackageManager.GET_SIGNATURES);
//	        for (Signature signature : info.signatures) {
//	            MessageDigest md = MessageDigest.getInstance("SHA");
//	            md.update(signature.toByteArray());
//	            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//	            }
//	    } catch (NameNotFoundException e) {
//
//	    } catch (NoSuchAlgorithmException e) {
//
//	    }
//			
		authButton = (LoginButton)findViewById(R.id.authButton);
		authButton.setVisibility(View.INVISIBLE);
        authButton.setOnErrorListener(new OnErrorListener() {      
            @Override
            public void onError(FacebookException error) {
                Log.v(TAG, "로그인 에러 : " + error);
            }
        });
        authButton.setReadPermissions(Arrays.asList("basic_info","email"));
        shareButton = (Button)findViewById(R.id.button1);
        shareButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				publishStory();
				
			}
		});
      
        
        authButton.setSessionStatusCallback(new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) { 
                if (session.isOpened()) {
                	
                    shareButton.setVisibility(View.VISIBLE);
                    
                    Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
                        @Override
                        public void onCompleted(GraphUser user,Response response) {    
                            if (user != null) { 
                                // 로그인 성공 (user에 정보가 들어있음.)
                                Log.v(TAG,"User ID "+ user.getId());
                                Log.v(TAG,"Email "+ user.asMap().get("email"));
                                Log.v(TAG,"name "+ user.asMap().get("name"));
                            }
                        }
                    });
                }else{
                	shareButton.setVisibility(View.INVISIBLE);
                }
            }
        });
        
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				authButton.performClick(); 
				
			}
		});
	}

	   // facebook에 포스팅
    private void publishStory() {
        Session session = Session.getActiveSession();
        if (session != null) {
             
            // Check for publish permissions    
            List<String> permissions = session.getPermissions();
            if (!isSubsetOf(PERMISSIONS, permissions)) {
                pendingPublishReauthorization = true;
                Session.NewPermissionsRequest newPermissionsRequest = new Session.NewPermissionsRequest(this, PERMISSIONS);
                session.requestNewPublishPermissions(newPermissionsRequest);
                return;
           }
 
            Bundle postParams = new Bundle();
            postParams.putString("name", "facbTest");
            postParams.putString("caption", "다음이미지");
            postParams.putString("description", "다음이미지 업로드");
            postParams.putString("link", "http://www.daum.net");
            postParams.putString("picture", "https://fbcdn-sphotos-h-a.akamaihd.net/hphotos-ak-frc3/p480x480/1175078_10200115247238891_466853508_n.jpg");
 
            Request.Callback callback= new Request.Callback() {
                public void onCompleted(Response response) {
                    JSONObject graphResponse = response.getGraphObject().getInnerJSONObject();
                    String postId = null;
                    try {
                        postId = graphResponse.getString("id");
                        Toast.makeText(
                                MainActivity.this,
                                "등록성공",
                                Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        //Log.i(TAG, "JSON error "+ e.getMessage());
                    }
                     
                    FacebookRequestError error = response.getError();
                    if (error != null) {
                        Toast.makeText(MainActivity.this, error.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                            Toast.makeText(MainActivity.this, postId, Toast.LENGTH_LONG).show();
                    }
                }
            };
 
            Request request = new Request(session, "me/feed", postParams, HttpMethod.POST, callback);
            RequestAsyncTask task = new RequestAsyncTask(request);
            task.execute();
        }else{
        	Log.v(TAG, "session null");
        }
    }
     
     
    private boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
        for (String string : subset) {
            if (!superset.contains(string)) {
                return false;
            }
        }
        return true;
    }

	 @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
