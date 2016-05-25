package com.kimtacademy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    //친구정보 얻기
    
    void getFriend(){
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

    		Request.Callback callback= new Request.Callback() {
    			public void onCompleted(Response response) {

    				Log.v(TAG, "data : " + response.toString());
    				JSONObject graphResponse = response.getGraphObject().getInnerJSONObject();
    				String postId = null;
    				try {
    					postId = graphResponse.getString("id");
    					Toast.makeText(
    							MainActivity.this,
    							"친구찾기 성공",
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
    		Bundle bundle = new Bundle();
    		  bundle.putString("fields", "birthday, name");
    		  

    		Request request = new Request(session, "me/friends", bundle, HttpMethod.GET, callback);
    		RequestAsyncTask task = new RequestAsyncTask(request);
    		task.execute();
    	}
    }
    //타입라인 얻기
    
    void getFriendTimeLine(){
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
            Request.Callback callback= new Request.Callback() {
                public void onCompleted(Response response) {
                	
                	Log.v(TAG, "data : " + response.toString());
                    JSONObject graphResponse = response.getGraphObject().getInnerJSONObject();
                    String postId = null;
                    try {
                        postId = graphResponse.getString("id");
                        Toast.makeText(
                                MainActivity.this,
                                "읽기 성공",
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
 
            //me 대신 친구 ID 값 입력
            Request request = new Request(session, "100003545682219/feed", null, HttpMethod.GET, callback);
            RequestAsyncTask task = new RequestAsyncTask(request);
            task.execute();
        }
    }
    
    //내 타입라인 얻기
    
    void getTimeLine(){
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
            Request.Callback callback= new Request.Callback() {
                public void onCompleted(Response response) {
                	
                	Log.v(TAG, "data : " + response.toString());
                    JSONObject graphResponse = response.getGraphObject().getInnerJSONObject();
                    try{
                    	JSONArray arrays = graphResponse.getJSONArray("data");
                    	Log.v(TAG, "갯수 : " + arrays.length() );
                    	for(int i = 0; i < arrays.length(); i++){
                    		JSONObject obj = arrays.getJSONObject(i);
                    		String id = obj.getString("id");
                    		JSONObject fromObj = obj.getJSONObject("from");
                    		String cName = fromObj.getString("name");
                    		String picture =  "";
                    		try{
                    			picture = obj.getString("picture");
                    		}catch(Exception e){
                    			Log.v(TAG, "message error : " + e);
                    		}
                    		Log.v(TAG, String.format("%s %s %s", id, cName, picture));
                    	}
                    }catch(JSONException e){
                    	Log.v(TAG, "error :  " + e);
                    }
                    
                    
                }
            };
 
            Request request = new Request(session, "me/feed", null, HttpMethod.GET, callback);
            RequestAsyncTask task = new RequestAsyncTask(request);
            task.execute();
        }
    }
    LoginButton authButton = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_main);
		
		authButton = (LoginButton)findViewById(R.id.authButton);
		authButton.setVisibility(View.INVISIBLE);
        authButton.setOnErrorListener(new OnErrorListener() {      
            @Override
            public void onError(FacebookException error) {
                Log.v(TAG, "로그인 에럴 : " + error);
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
        
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getTimeLine();
				
			}
		});
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getFriend();
				
			}
		});
        
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//강제 이벤트 발생하기
				authButton.performClick();
				
			}
		});
        
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//친구 타임라인얻기
				getFriendTimeLine();
				
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
                            }
                        }
                    });
                }else{
                	shareButton.setVisibility(View.INVISIBLE);
                }
            }
        });

	}
	   @Override
	    public void onResume() {
	        super.onResume();
	    }
	     
	     
	     
	    // 로그인 여부 확인
	    boolean isLogined(){
	        Session session = Session.getActiveSession();
	        if(session == null)
	            return false;
	         
	        if(!session.isOpened())
	            return false;
	         
	        return true;
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
