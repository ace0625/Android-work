package com.skplanetx.tmapopenapi.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.skp.Tmap.BizCategory;
import com.skp.Tmap.TMapCircle;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapData.BizCategoryListenerCallback;
import com.skp.Tmap.TMapData.ConvertGPSToAddressListenerCallback;
import com.skp.Tmap.TMapData.FindAddressPOIListenerCallback;
import com.skp.Tmap.TMapData.FindAllPOIListenerCallback;
import com.skp.Tmap.TMapData.FindAroundNamePOIListenerCallback;
import com.skp.Tmap.TMapData.FindPathDataAllListenerCallback;
import com.skp.Tmap.TMapData.FindPathDataListenerCallback;
import com.skp.Tmap.TMapData.TMapPathType;
import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapGpsManager.onLocationChangedCallback;
import com.skp.Tmap.TMapInfo;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapMarkerItem2;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapPolygon;
import com.skp.Tmap.TMapTapi;
import com.skp.Tmap.TMapView;
import com.skp.Tmap.TMapView.MapCaptureImageListenerCallback;
import com.skplanetx.tmapopenapi.LogManager;
import com.skplanetx.tmapopenapi.R;



public class MainActivity extends BaseActivity implements onLocationChangedCallback
{

	@Override
	public void onLocationChange(Location location) {
	
		LogManager.printLog("onLocationChange " + location.getLatitude() +  " " + location.getLongitude());
		
	}

	private TMapView		mMapView = null;
	
	private RelativeLayout 	mMainRelativeLayout = null;
	private Context 		mCtx;
	
	
	static final int[] mArrayMapButton = {
	
		R.id.btnZoomIn,
		R.id.btnZoomOut,
		R.id.btnGetZoomLevel,
		R.id.btnSetZoomLevel,
		R.id.btnSetMapType,
		R.id.btnGetLocationPoint,
		R.id.btnSetLocationPoint,
		R.id.btnSetIcon,
		R.id.btnSetCompassMode,
		R.id.btnGetIsCompass,
		R.id.btnSetTrafficInfo,
		R.id.btnGetIsTrafficeInfo,
		R.id.btnSetSightVisible,
		R.id.btnSetTrackIngMode,
		R.id.btnGetIsTracking,
		R.id.btnAddTMapCircle,
		R.id.btnRemoveTMapCircle, 
		R.id.btnMarkerPoint,
		R.id.btnRemoveMarker,
		R.id.btnMoveFrontMarker,
		R.id.btnMoveBackMarker,
		R.id.btnDrawPolyLine,
		R.id.btnErasePolyLine,		
		R.id.btnDrawPolygon,
		R.id.btnErasePolygon,
		R.id.btnBicycle,
		R.id.btnBicycleFacility,
		R.id.btnMapPath, 
		R.id.btnRemoveMapPath,
		R.id.btnDisplayMapInfo,
		R.id.btnNaviGuide,
		R.id.btnCarPath,
		R.id.btnPedestrian_Path,
		R.id.btnBicycle_Path,
		R.id.btnGetCenterPoint,
		R.id.btnFindAllPoi,
		R.id.btnConvertToAddress,
		R.id.btnGetBizCategory,
		R.id.btnGetAroundBizPoi,
		R.id.btnTileSD,
		R.id.btnTileEx,
		R.id.btnTileHD,
		R.id.btnCapture,
		R.id.btnDisalbeZoom,
		
		R.id.btnInvokeRoute,
		R.id.btnInvokeSetLocation,
		R.id.btnInvokeSearchPortal, 
		
		R.id.btnTimeMachine, 
		R.id.btnTMapInstall,
		
		R.id.btnMarkerPoint2
		
	};

	
	private 	int 		m_nCurrentZoomLevel = 0;
	private 	double 		m_Latitude  = 0;
	private     double  	m_Longitude = 0;
	private 	Boolean 	m_bShowMapIcon;

	private 	Boolean 	m_bTrafficeMode;
	private 	Boolean 	m_bSightVisible;
	private 	Boolean 	m_bTrackingMode;
	
	
	ArrayList<String>		mArrayID;
	
	
	ArrayList<String>		mArrayCircleID;
	private static 	int 	mCircleID;
	
	ArrayList<String>		mArrayLineID;
	private static 	int 	mLineID;
	
	ArrayList<String>		mArrayPolygonID;
	private static  int 	mPolygonID;

	ArrayList<String>       mArrayMarkerID;
	private static int 		mMarkerID;
	
	private TranslateAnimation mShowAnimation;  
	
	TMapGpsManager gps = null;
	
	
	
	/*
	 * onCreate() 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_activity);
		
		mCtx = this;
		
		mMainRelativeLayout = (RelativeLayout)findViewById(R.id.mainRelativeLayout);
		
		mMapView = new TMapView(this);
		mMainRelativeLayout.addView(mMapView);	
		
		configureMapView();
		
		initView();
		
		mArrayID = new ArrayList<String>();
		
		mArrayCircleID = new ArrayList<String>();
		mCircleID = 0;
		
		mArrayLineID = new ArrayList<String>();
		mLineID = 0;
		
		mArrayPolygonID = new ArrayList<String>();
		mPolygonID = 0;
		
		mArrayMarkerID	= new ArrayList<String>();
		mMarkerID = 0;
		
		
		gps = new TMapGpsManager(MainActivity.this);
		gps.setMinTime(1000);
		gps.setMinDistance(5);
		
		gps.setProvider(gps.NETWORK_PROVIDER);
		gps.OpenGps();
		//showMarkerPoint();
		
		
	}

	
	private void configureMapView() {
		
		new Thread() {
			@Override
			public void run() {
									
				mMapView.setSKPMapApiKey("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				
				
				mMapView.setLanguage(mMapView.LANGUAGE_KOREAN);	
				
			}
		}.start();
		
		
	}

	
	
	/**
	 * initView - 버튼에 대한 리스너를 등록한다. 
	 */
	private void initView()
	{	
		for(int btnMapView : mArrayMapButton)
		{
			Button ViewButton = (Button)findViewById(btnMapView);
			ViewButton.setOnClickListener(this);
		}
		
		
		mMapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
			
			@Override
			public boolean onPressUpEvent(ArrayList<TMapMarkerItem> markerlist,ArrayList<TMapPOIItem> poilist, TMapPoint point, PointF pointf) {
				
				LogManager.printLog("MainActivity onPressUpEvent " + markerlist.size());
				
				return false;
			}
			
			@Override
			public boolean onPressEvent(ArrayList<TMapMarkerItem> markerlist,ArrayList<TMapPOIItem> poilist, TMapPoint point, PointF pointf) {
			
				LogManager.printLog("MainActivity onPressEvent " + markerlist.size());
				
				for(int i = 0; i < markerlist.size(); i++)
				{
					TMapMarkerItem item = markerlist.get(i);
					
					LogManager.printLog("MainActivity onPressEvent " + item.getName() + " " + item.getTMapPoint().getLatitude() + " " + item.getTMapPoint().getLongitude());
				}
				
				return false;
			}
			
		});
		
		
		mMapView.setOnLongClickListenerCallback(new TMapView.OnLongClickListenerCallback() {
			
			@Override
			public void onLongPressEvent(ArrayList<TMapMarkerItem> markerlist,ArrayList<TMapPOIItem> poilist, TMapPoint point) 
			{
				LogManager.printLog("MainActivity onLongPressEvent " + markerlist.size());
			}
		});
		
		
		mMapView.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
			
			@Override
			public void onCalloutRightButton(TMapMarkerItem markerItem) {
				
				String strMessage = "";
				strMessage = "ID: " + markerItem.getID() + " " + "Title " + markerItem.getCalloutTitle();
				
				Common.showAlertDialog(MainActivity.this, "Callout Right Button", strMessage);
				
			}
		});
		
		m_nCurrentZoomLevel = -1;
		m_bShowMapIcon = false;
		m_bTrafficeMode = false;
		m_bSightVisible = false;
		m_bTrackingMode = false;	
				
	}
	
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		
	}


	@Override
	protected void onPause() {
		super.onPause();
		
		
	}

	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
				
		
	}

	
	
	
	/**
	 * onClick Event 
	 */
	@Override
	public void onClick(View v) {
		
		switch(v.getId())
		{
		
		case R.id.btnZoomIn			  : 	mapZoomIn(); 			break;
		case R.id.btnZoomOut		  : 	mapZoomOut(); 			break;
		case R.id.btnGetZoomLevel	  :  	getZoomLevel(); 		break;
		case R.id.btnSetZoomLevel	  :  	setZoomLevel(); 		break;
		case R.id.btnSetMapType		  :		setMapType(); 			break;
		case R.id.btnGetLocationPoint : 	getLocationPoint(); 	break;	
		case R.id.btnSetLocationPoint : 	setLocationPoint(); 	break;
		case R.id.btnSetIcon		  : 	setMapIcon(); 			break;
		case R.id.btnSetCompassMode	  : 	setCompassMode();		break;
		case R.id.btnGetIsCompass     :		getIsCompass();			break;
		case R.id.btnSetTrafficInfo	  :		setTrafficeInfo();		break;		
		case R.id.btnGetIsTrafficeInfo: 	getIsTrafficeInfo();	break;
		case R.id.btnSetSightVisible  : 	setSightVisible();		break;
		case R.id.btnSetTrackIngMode  : 	setTrackingMode();		break;
		case R.id.btnGetIsTracking	  : 	getIsTracking();		break;
		case R.id.btnAddTMapCircle	  : 	addTMapCircle();		break;
		case R.id.btnRemoveTMapCircle : 	removeTMapCircle();		break;
		case R.id.btnMarkerPoint	  :     showMarkerPoint(); 		break;
		case R.id.btnRemoveMarker     : 	removeMarker(); 		break;
		case R.id.btnMoveFrontMarker  :     moveFrontMarker(); 		break;
		case R.id.btnMoveBackMarker   :     moveBackMarker();		break;
		case R.id.btnDrawPolyLine     :     drawLine();			 	break;
		case R.id.btnErasePolyLine	  : 	erasePolyLine();		break;
		case R.id.btnDrawPolygon	  : 	drawPolygon(); 			break;
		case R.id.btnErasePolygon     :     erasePolygon(); 		break;
		case R.id.btnMapPath		  : 	drawMapPath();			break;
		case R.id.btnRemoveMapPath    :     removeMapPath(); 		break;
		case R.id.btnDisplayMapInfo   :     displayMapInfo(); 		break;		
		case R.id.btnNaviGuide		  :     naviGuide();			break;				
		case R.id.btnCarPath		  :     drawCarPath(); 			break;
		case R.id.btnPedestrian_Path  :     drawPedestrianPath();   break;
		case R.id.btnBicycle_Path     :     drawBicyclePath(); 	    break;
		
		case R.id.btnGetCenterPoint   :     getCenterPoint();		break;
		
		case R.id.btnFindAllPoi		  :     findAllPoi();			break;
		case R.id.btnConvertToAddress :     convertToAddress(); 	break;
		case R.id.btnGetBizCategory   : 	getBizCategory(); 		break;
		case R.id.btnGetAroundBizPoi  :     getAroundBizPoi(); 		break;
		case R.id.btnTileSD			  : 	tileSD();				break;
		case R.id.btnTileEx			  :     tileEx();				break;
		case R.id.btnTileHD			  :     tileHD();				break;
		
		case R.id.btnInvokeRoute	  :     invokeRoute();			break;
		case R.id.btnInvokeSetLocation: 	invokeSetLocation();    break;
				
		case R.id.btnInvokeSearchPortal: 	invokeSearchProtal(); 	break;
		
	
		case R.id.btnBicycle		  :     setBicycle();		    break;
		case R.id.btnBicycleFacility  : 	setBicycleFacility();   break;
		case R.id.btnCapture		  :     captureImage(); 		break;
		
		case R.id.btnDisalbeZoom	  : 	disableZoom();			break;
		
		case R.id.btnTimeMachine	  :   	timeMachine(); 			break;
		
		case R.id.btnTMapInstall	  :     tmapInstall(); 			break;
		

		case R.id.btnMarkerPoint2	  :     showMarkerPoint2(); 		break;
		}
		
	}
	
	
	public TMapPoint randomTMapPoint()
	{
		double latitude = ((double)Math.random() ) * (37.575113-37.483086) + 37.483086;
	    double longitude = ((double)Math.random() ) * (127.027359-126.878357) + 126.878357;    

	    latitude = Math.min(37.575113, latitude);
	    latitude = Math.max(37.483086, latitude);
	    
	    longitude = Math.min(127.027359, longitude);
	    longitude = Math.max(126.878357, longitude);

	    LogManager.printLog("randomTMapPoint" + latitude + " " + longitude);
	    
		TMapPoint point = new TMapPoint(latitude, longitude);
		
		return point;
	}
	
	
	/**
	 * mapZoomIn
	 * 지도를 한단계 확대한다. 
	 */
	public void mapZoomIn()
	{
		mMapView.MapZoomIn();
			
		
        
        
	}
	
	
	
	/**
	 * mapZoomOut
	 * 지도를 한단계 축소한다. 
	 */
	public void mapZoomOut()
	{
		mMapView.MapZoomOut();
	}
	
	
	
	/**
	 * getZoomLevel
	 * 현재 줌의 레벨을 가지고 온다. 
	 */
	public void getZoomLevel()
	{
		int nCurrentZoomLevel = mMapView.getZoomLevel();
		
		Common.showAlertDialog(this, "", "현재 Zoom Level : " + Integer.toString(nCurrentZoomLevel));
	}
	
	
	
	/**
	 * setZoomLevel
	 * Zoom Level을 설정한다. 
	 */
    public void setZoomLevel()
    {
    	final String[] arrString = getResources().getStringArray(R.array.a_zoomlevel);
		
		AlertDialog dlg = new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("Select Zoom Level")
			.setSingleChoiceItems(R.array.a_zoomlevel, m_nCurrentZoomLevel, new DialogInterface.OnClickListener() {						
				@Override
				public void onClick(DialogInterface dialog, int item) {							
					m_nCurrentZoomLevel = item;
					dialog.dismiss();
					mMapView.setZoomLevel(Integer.parseInt(arrString[item]));							
				}
				
			}).show();		
    }
	
	
    
    /**
     * seetMapType  
     * Map의 Type을 설정한다.
     */
    public void setMapType()
    {
    	AlertDialog dlg = new AlertDialog.Builder(this)
		.setIcon(R.drawable.ic_launcher)
		.setTitle("Select MAP Type")
		.setSingleChoiceItems(R.array.a_maptype, -1, new DialogInterface.OnClickListener() {						
			@Override
			public void onClick(DialogInterface dialog, int item) {							
				
				LogManager.printLog("Set Map Type " + item);
				
				dialog.dismiss();
				
				mMapView.setMapType(item);
			}
			
		}).show();		
    }

	
    
    /**
     * getLocationPoint
     * 현재위치로 표시될 좌표의 위도, 경도를 설정한다. 
     */
	public void getLocationPoint()
	{
		TMapPoint point = mMapView.getLocationPoint();
		
		double Latitude = point.getLatitude();
		double Longitude = point.getLongitude();
		
		m_Latitude  = Latitude;
		m_Longitude = Longitude;
		
		LogManager.printLog("Latitude " + Latitude + " Longitude " + Longitude);
		
		String strResult = String.format("Latitude = %f Longitude = %f", Latitude, Longitude);
		
		Common.showAlertDialog(this, "", strResult);
	}
	
	
	
	/**
	 * setLocationPoint
	 * 현재위치로 표시될 좌표의 위도,경도를 설정한다. 
	 */
	public void setLocationPoint()
	{		
		double 	Latitude  = 37.5077664;
		double  Longitude = 126.8805826;
		
		LogManager.printLog("setLocationPoint " + Latitude + " " + Longitude);
		
		mMapView.setLocationPoint(Latitude, Longitude);
	}
	
	
	/**
	 * setMapIcon
	 * 현재위치로 표시될 아이콘을 설정한다. 
	 */
	public void setMapIcon()
	{
		m_bShowMapIcon = !m_bShowMapIcon;
		
		if(m_bShowMapIcon)
		{
			Bitmap bitmap = BitmapFactory.decodeResource(mCtx.getResources(),R.drawable.ic_launcher);
			mMapView.setIcon(bitmap);
		}
		
		mMapView.setIconVisibility(m_bShowMapIcon);
	}
	
	
	/**
	 * setCompassMode
	 * 단말의 방항에 따라 움직이는 나침반모드로 설정한다. 
	 */
	public void setCompassMode()
	{		
		mMapView.setCompassMode(!mMapView.getIsCompass());
		
	}
	
	
	
	/**
	 * getIsCompass
	 * 나침반모드의 사용여부를 반환한다. 
	 */
	public void getIsCompass()
	{
		Boolean bGetIsCompass = mMapView.getIsCompass();
		
		Common.showAlertDialog(this, "", "현재 나침반 모드는 : " + bGetIsCompass.toString() );
	}
	
	
	/**
	 * setTrafficeInfo
	 * 실시간 교통정보를 표출여부를 설정한다. 
	 */
	public void setTrafficeInfo()
	{
		m_bTrafficeMode = !m_bTrafficeMode;
		mMapView.setTrafficInfo(m_bTrafficeMode);
	}
	
	
	/**
	 * getIsTrafficeInfo
	 * 실시간 교통정보 표출상태를 반환한다. 
	 */
	public void getIsTrafficeInfo()
	{
		Boolean bIsTrafficeInfo = mMapView.IsTrafficInfo();
		
		Common.showAlertDialog(this, "", "현재 실시간 교통정보 표출상태는  : " + bIsTrafficeInfo.toString() );
	}
	
	
	/**
	 * setSightVisible
	 * 시야표출여부를 설정한다. 
	 */
	public void setSightVisible()
	{
		m_bSightVisible = !m_bSightVisible;
		
		mMapView.setSightVisible(m_bSightVisible);
	}
	
	
	
	/**
	 * setTrackingMode
	 * 화면중심을 단말의 현재위치로 이동시켜주는 트래킹모드로 설정한다. 
	 */
	public void setTrackingMode()
	{
		m_bTrackingMode = !m_bTrackingMode;
		mMapView.setTrackingMode(m_bTrackingMode);
	}
	
	
	
	/**
	 * getIsTracking
	 * 트래킹모드의 사용여부를 반환한다. 
	 */
	public void getIsTracking()
	{
		Boolean bIsTracking = mMapView.getIsTracking();
		
		Common.showAlertDialog(this, "", "현재 트래킹모드 사용 여부  : " + bIsTracking.toString() );
	}
	
	
	
	/**
	 * addTMapCircle()
	 * 지도에 서클을 추가한다. 
	 */
	public void addTMapCircle()
	{		
		TMapCircle circle = new TMapCircle();
		
		circle.setRadius(300);
		circle.setLineColor(Color.BLUE);
		circle.setCircleWidth((float)10);
		circle.setRadiusVisible(true);
		
		TMapPoint point = randomTMapPoint();
		circle.setCenterPoint(point);
		
		String strID = String.format("circle%d", mCircleID++);
		mMapView.addTMapCircle(strID, circle);
		
		mArrayCircleID.add(strID);
		
	}
	
	
	/**
	 * removeTMapCircle
	 * 지도상의 해당 서클을 제거한다. 
	 */
	public void removeTMapCircle()
	{	
		
		if(mArrayCircleID.size() <= 0 )
			return;
		
		String strCircleID = mArrayCircleID.get(mArrayCircleID.size() - 1 );
		mMapView.removeTMapCircle(strCircleID);
		
		mArrayCircleID.remove(mArrayCircleID.size() - 1);
		
		
		//mMapView.showCallOutViewWithMarkerItemID("02");
		
	}

	
	
	public void showMarkerPoint2()
	{	
		/*
		MarkerOverlay marker1 = new MarkerOverlay(this, mMapView);
		marker1.setID("01");
		marker1.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.map_pin_red));
		marker1.setTMapPoint(new TMapPoint(37.566474, 126.985022) );
		
		ArrayList<Bitmap> list = new ArrayList<Bitmap>();
		list.add(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.map_pin_red));
		list.add(BitmapFactory.decodeResource(mCtx.getResources(),R.drawable.end));
		
		marker1.setAnimationIcons(list);
		marker1.setAniDuration(1000);
		marker1.startAnimation();
		
		mMapView.addMarker2Item("01", marker1);
		
		MarkerOverlay marker2 = new MarkerOverlay(this, mMapView);
		marker2.setID("02");
		marker2.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		marker2.setTMapPoint(new TMapPoint(37.551025, 126.987898) );
		
		ArrayList<Bitmap> list1 = new ArrayList<Bitmap>();
		list1.add(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.map_pin_red));
		list1.add(BitmapFactory.decodeResource(mCtx.getResources(),R.drawable.end));
		
		marker2.setAnimationIcons(list1);
		marker2.startAnimation();
		
		mMapView.addMarker2Item("02", marker2);
		*/
		
		for(int i = 0; i < 30; i++) {
			
			MarkerOverlay marker1 = new MarkerOverlay(this, mMapView);
			
			
			String strID = String.format("%02d", i);
			
			marker1.setID(strID);
			marker1.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.map_pin_red));
			
			
			marker1.setTMapPoint(randomTMapPoint());
			
			ArrayList<Bitmap> list = new ArrayList<Bitmap>();
			list.add(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.map_pin_red));
			list.add(BitmapFactory.decodeResource(mCtx.getResources(),R.drawable.end));
			
			marker1.setAnimationIcons(list);
			//marker1.setAniDuration(1000);
			
			//marker1.startAnimation();
			
			mMapView.addMarkerItem2(strID, marker1);
			
		}
				
		
		mMapView.setOnMarkerClickEvent(new TMapView.OnCalloutMarker2ClickCallback() {
			
			@Override
			public void onCalloutMarker2ClickEvent(String id, TMapMarkerItem2 markerItem2) {
				
				//LogManager.printLog("ClickEvent " + " id " + id + " " + markerItem2.latitude + " " +  markerItem2.longitude);
				
				String strMessage = "ClickEvent " + " id " + id + " " + markerItem2.latitude + " " +  markerItem2.longitude;
				
				Common.showAlertDialog(MainActivity.this, "TMapMarker2", strMessage);
				
			}
		});
		
		
		
		
		
	}
	
	
	/**
	 * showMarkerPoint
	 * 지도에 마커를 표출한다. 
	 */
	public void showMarkerPoint()
	{	

		Bitmap bitmap = null;
		
		TMapPoint point = new TMapPoint(37.566474, 126.985022);
				
		TMapMarkerItem item1 = new TMapMarkerItem();
		
		bitmap = BitmapFactory.decodeResource(mCtx.getResources(),R.drawable.i_location);
				
		item1.setTMapPoint(point);
		item1.setName("SKT타워");
		item1.setVisible(item1.VISIBLE);
	
		item1.setIcon(bitmap);
		LogManager.printLog("bitmap " + bitmap.getWidth() + " " + bitmap.getHeight());
		
		bitmap = BitmapFactory.decodeResource(mCtx.getResources(),R.drawable.i_location);		
		item1.setCalloutTitle("SKT타워");
		item1.setCalloutSubTitle("을지로입구역 500M");
		item1.setCanShowCallout(true);
		//item1.setAutoCalloutVisible(true);
		
		Bitmap bitmap_i = BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.i_go);
		
		
		
		//item1.setCalloutLeftImage(bitmap);
		item1.setCalloutRightButtonImage(bitmap_i);
		
		mMapView.addMarkerItem("0", item1);
		
		point = new TMapPoint(37.55102510077652, 126.98789834976196);
		TMapMarkerItem item2 = new TMapMarkerItem();

		item2.setTMapPoint(point);
		item2.setName("N서울타워");
		item2.setVisible(item2.VISIBLE);
		item2.setCalloutTitle("청호타워 4층");
		
		//item2.setCalloutSubTitle("을지로입구역 500M");
		
		item2.setCanShowCallout(true);
		
		
		//item2.setAutoCalloutVisible(true);
		
		//item2.setCalloutLeftImage(bitmap);
		
		bitmap_i = BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.i_go);		
		item2.setCalloutRightButtonImage(bitmap_i);
				
		bitmap = BitmapFactory.decodeResource(mCtx.getResources(),R.drawable.pin_tevent);
		item2.setIcon(bitmap);
						
		mMapView.addMarkerItem("1", item2);
		
		
		point = new TMapPoint(37.58102510077652, 126.98789834976196);
		item2 = new TMapMarkerItem();

		item2.setTMapPoint(point);
		item2.setName("N서울타워");
		item2.setVisible(item2.VISIBLE);
		item2.setCalloutTitle("창덕궁 청호타워 4층");
		
		item2.setCalloutSubTitle("을지로입구역 500M");
		item2.setCanShowCallout(true);
		
			
		bitmap_i = BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.i_go);		
		item2.setCalloutRightButtonImage(bitmap_i);
				
		bitmap = BitmapFactory.decodeResource(mCtx.getResources(),R.drawable.map_pin_red);
		item2.setIcon(bitmap);
						
		mMapView.addMarkerItem("2", item2);
				
		point = new TMapPoint(37.58102510077652, 126.99789834976196);
		item2 = new TMapMarkerItem();

		item2.setTMapPoint(point);
		item2.setName("N서울타워");
		item2.setVisible(item2.VISIBLE);
		item2.setCalloutTitle("대학로 혜화역111111");
				
		item2.setCanShowCallout(true);
				
		item2.setCalloutLeftImage(bitmap);
		
		bitmap_i = BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.i_go);		
		item2.setCalloutRightButtonImage(bitmap_i);
				
		
		bitmap = BitmapFactory.decodeResource(mCtx.getResources(),R.drawable.end);
		item2.setIcon(bitmap);
						
		mMapView.addMarkerItem("3", item2);

			
	}
	
		
	
	public void removeMarker()
	{
		if(mArrayMarkerID.size() <= 0 )
			return;
		
		String strMarkerID = mArrayMarkerID.get(mArrayMarkerID.size() - 1 );
		mMapView.removeMarkerItem(strMarkerID);
		
		mArrayMarkerID.remove(mArrayMarkerID.size() - 1);
		
	}
	
	
	/**
	 * moveFrontMarker
	 * 마커를 맨 앞으로 표시 하도록 한다. 
	 * showMarkerPoint() 함수를 먼저 클릭을 한 후, 클릭을 해야 함.
	 */
	public void moveFrontMarker()
	{
		TMapMarkerItem item = mMapView.getMarkerItemFromID("1");
		
		mMapView.bringMarkerToFront(item);
	}
	
	
	/**
	 * moveBackMarker
	 * 마커를 맨 뒤에 표시하도록 한다. 
	 * showMarkerPoint() 함수를 먼저 클릭을 한 후, 클릭을 해야 함.
	 */
	public void moveBackMarker()
	{
		TMapMarkerItem item = mMapView.getMarkerItemFromID("1");
		
		mMapView.sendMarkerToBack(item);
	}
	
	
	/**
	 * drawLine
	 * 지도에 라인을 추가한다. 
	 */
	public void drawLine()
	{	
		TMapPolyLine polyLine = new TMapPolyLine();
		polyLine.setLineColor(Color.BLUE);
		polyLine.setLineWidth(5);
		
		for(int i = 0; i < 5; i++)
		{
			TMapPoint point = randomTMapPoint();
			polyLine.addLinePoint(point);
		}
		
		String strID = String.format("line%d", mLineID++);
		
		mMapView.addTMapPolyLine(strID, polyLine);
		
		mArrayLineID.add(strID);
		
	}
	
	
	/**
	 * erasePolyLine
	 * 지도에 라인을 제거한다. 
	 */
	public void erasePolyLine()
	{
		if(mArrayLineID.size() <= 0)
			return;
		
		String strLineID = mArrayLineID.get(mArrayLineID.size() - 1 );
		mMapView.removeTMapPolyLine(strLineID);
		
		mArrayLineID.remove(mArrayLineID.size() - 1);
		
	}

	
	
	/**
	 * drawPolygon
	 * 지도에 폴리곤에 그린다. 
	 */
	public void drawPolygon()
	{			
		int Min = 3;
		int Max = 10;
		int rndNum = (int)(Math.random() * ( Max - Min ));
		
		LogManager.printLog("drawPolygon" + rndNum);
		
		TMapPolygon polygon = new TMapPolygon();
		polygon.setLineColor(Color.BLUE);
		polygon.setPolygonWidth((float)4);
		polygon.setAreaAlpha(2);
		    
		TMapPoint point = null;
		
		if(rndNum < 3 )
		{
			rndNum = rndNum + (3 - rndNum);
		}
		
		for(int i = 0; i < rndNum; i++)
		{
			point = randomTMapPoint(); 
			polygon.addPolygonPoint(point);
			
		}
				
		String strID = String.format("polygon%d", mPolygonID++);
		mMapView.addTMapPolygon(strID, polygon);
		
		mArrayPolygonID.add(strID);
		
	}
	
	/**
	 * erasePolygon
	 * 지도에 그려진 폴리곤을 제거한다. 
	 */
	public void erasePolygon()
	{	
		if(mArrayPolygonID.size() <= 0)
			return;
		
		String strPolygonID = mArrayPolygonID.get(mArrayPolygonID.size() - 1 );
		
		LogManager.printLog("erasePolygon " + strPolygonID);
		
		mMapView.removeTMapPolygon(strPolygonID);
		
		mArrayPolygonID.remove(mArrayPolygonID.size() - 1);
		
	}
	
	
	
	/**
	 * drawMapPath
	 * 지도에 시작-종료 점에 대해서 경로를 표시한다. 
	 */
	public void drawMapPath()
	{			
		TMapPoint point1 = mMapView.getCenterPoint();
		TMapPoint point2 = randomTMapPoint();
		
		TMapData tmapdata = new TMapData();
			
		tmapdata.findPathData(point1, point2, new FindPathDataListenerCallback() {
			
			@Override
			public void onFindPathData(TMapPolyLine polyLine) {
				
				mMapView.addTMapPath(polyLine);
			}
		});
		
	}
	
	
	private String getContentFromNode(Element item, String tagName){
		NodeList list = item.getElementsByTagName(tagName);
		if(list.getLength() > 0){
			if(list.item(0).getFirstChild() != null)
			{
				return list.item(0).getFirstChild().getNodeValue();
			}
		}
		return null;
	}
	
	
	
	/**
	 * displayMapInfo()
	 * POI들이 모두 표시될 수 있는 줌레벨 결정함수와 중심점리턴하는 함수
	 */
	public void displayMapInfo()
	{	
		/*
		TMapPoint point1 = mMapView.getCenterPoint();		
		TMapPoint point2 = randomTMapPoint();
		*/
		TMapPoint point1 = new TMapPoint(37.541642248630524, 126.99599611759186);
		
		TMapPoint point2 = new TMapPoint(37.541243493556976, 126.99659830331802);
		
		TMapPoint point3 = new TMapPoint(37.540909826755524, 126.99739581346512);
		
		TMapPoint point4 = new TMapPoint(37.541080713272095, 126.99874675273895);
					
		ArrayList<TMapPoint> point = new ArrayList<TMapPoint>();
		
		point.add(point1);
		point.add(point2);
		point.add(point3);
		point.add(point4);
		
		
		TMapInfo info = mMapView.getDisplayTMapInfo(point);
		
		String strInfo = "Center Latitude" + info.getTMapPoint().getLatitude() + "Center Longitude" + info.getTMapPoint().getLongitude() + 
						"Level " + info.getTMapZoomLevel();
		
		Common.showAlertDialog(this, "", strInfo );
		
		
	}
	
	
	/**
	 * removeMapPath
	 * 경로 표시를 삭제한다. 
	 */
	public void removeMapPath()
	{	
		mMapView.removeTMapPath();
		
	}
	
	
	
	/**
	 * naviGuide
	 * 길안내 
	 */
	public void naviGuide()
	{			
		TMapPoint point1 = mMapView.getCenterPoint();
		TMapPoint point2 = randomTMapPoint();
		
		TMapData tmapdata = new TMapData();
		
		tmapdata.findPathDataAll(point1, point2, new FindPathDataAllListenerCallback() {
			
			@Override
			public void onFindPathDataAll(Document doc) {
				
			
			}
		});
		
		
	}
	
	
	public void drawCarPath()
	{	
		
		TMapPoint point1 = mMapView.getCenterPoint();
		TMapPoint point2 = randomTMapPoint();
		
		TMapData tmapdata = new TMapData();
		
		tmapdata.findPathDataWithType(TMapPathType.CAR_PATH, point1, point2, new FindPathDataListenerCallback() {
			
			@Override
			public void onFindPathData(TMapPolyLine polyLine) {
				
				mMapView.addTMapPath(polyLine);
				
			}
		});
		
				
	}
	
	
	
	public void  drawPedestrianPath()
	{				
		TMapPoint point1 = mMapView.getCenterPoint();
		TMapPoint point2 = randomTMapPoint();
		
		TMapData tmapdata = new TMapData();
		
		tmapdata.findPathDataWithType(TMapPathType.PEDESTRIAN_PATH, point1, point2, new FindPathDataListenerCallback() {
			
			@Override
			public void onFindPathData(TMapPolyLine polyLine) {
				
				polyLine.setLineColor(Color.BLUE);
				mMapView.addTMapPath(polyLine);
				
			}
		});
		
		
	}
	
	
	
	public void drawBicyclePath()
	{		
		TMapPoint point1 = mMapView.getCenterPoint();
		TMapPoint point2 = randomTMapPoint();
		
		TMapData tmapdata = new TMapData();
		
		
		tmapdata.findPathDataWithType(TMapPathType.BICYCLE_PATH, point1, point2, new FindPathDataListenerCallback() {
			
			@Override
			public void onFindPathData(TMapPolyLine polyLine) {
				
				mMapView.addTMapPath(polyLine);
				
			}
		});
		
		
	}
	
	
	
	
	
	
	
	/**
	 * getCenterPoint
	 * 지도의 중심점을 가지고 온다. 
	 */
	public void getCenterPoint()
	{
		TMapPoint point = mMapView.getCenterPoint();
		
		Common.showAlertDialog(this, "", "지도의 중심 좌표는 " + point.getLatitude() + " " + point.getLongitude() );
		
	}
	
	
	/**
	 * findAllPoi
	 * 통합검색 POI를 요청한다. 
	 */
	public void findAllPoi()
	{		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("POI 통합 검색");

		final EditText input = new EditText(this);
		builder.setView(input);

		builder.setPositiveButton("확인", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    			    		    	
		    	final String strData = input.getText().toString();
		        
			    TMapData tmapdata = new TMapData();
			      
			    tmapdata.findAllPOI(strData, new FindAllPOIListenerCallback() {
					
					@Override
					public void onFindAllPOI(ArrayList<TMapPOIItem> poiItem) {
						
						for(int i = 0; i < poiItem.size(); i++)
			        	{
			        		TMapPOIItem  item = poiItem.get(i);
			        				        		        
			        		LogManager.printLog("POI Name: " + item.getPOIName().toString() + ", " + 
			        				            "Address: " + item.getPOIAddress().replace("null", "")  + ", " + 
			        				            "Point: " + item.getPOIPoint().toString());
			        		
			        	}
						
					}
				});
			    
			    
		    }
		    
		});
		builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		    }
		});

		builder.show();
	
		
	}
	
	
	/**
	 * convertToAddress
	 * 지도에서 선택한 지점을 주소를 변경요청한다. 
	 */
	public void convertToAddress()
	{			
		TMapPoint point = mMapView.getCenterPoint();
		
	    TMapData tmapdata = new TMapData();
	      
	    if(mMapView.isValidTMapPoint(point))
	    {
		    tmapdata.convertGpsToAddress(point.getLatitude(), point.getLongitude(), new ConvertGPSToAddressListenerCallback() {
				
				@Override
				public void onConvertToGPSToAddress(String strAddress) {
					
					LogManager.printLog("선택한 위치의 주소는 " + strAddress);
				}
			});
	    }
		  
	}    
	    
	
	/**
	 * getBizCategory
	 * 업종별 category를 요청한다. 
	 */
	public void getBizCategory()
	{		
		TMapData tmapdata = new TMapData();
		
        tmapdata.getBizCategory(new BizCategoryListenerCallback() {
			
			@Override
			public void onGetBizCategory(ArrayList<BizCategory> poiItem) {
				
				for(int i = 0; i < poiItem.size(); i++)
		        {
		        	BizCategory item = poiItem.get(i);
		        	
		        	LogManager.printLog("UpperBizCode " + item.upperBizCode + " " + "UpperBizName " + item.upperBizName );
		        	LogManager.printLog("MiddleBizcode " + item.middleBizCode + " " + "MiddleBizName " + item.middleBizName);
		        }
			}
		});
            
	}
	
	
	
	/**
	 * getAroundBizPoi
	 * 업종별 주변검색 POI 데이터를 요청한다. 
	 */
	public void getAroundBizPoi()
	{				
		TMapData tmapdata = new TMapData();
		 
		TMapPoint point = mMapView.getCenterPoint();
		
		
		/*
		tmapdata.findAroundBizPOI(point, "01", "편의점", new FindAroundBizPOIListenerCallback() {
			
			@Override
			public void onFindAroundBizPOI(ArrayList<TMapPOIItem> poiItem) {
				
				for(int i = 0; i < poiItem.size(); i++)
	            {
	            	TMapPOIItem item = poiItem.get(i);
	            	
	            	LogManager.printLog("POI Name: " + item.getPOIName() + "," + 
	            						"Address: " + item.getPOIAddress().replace("null", ""));
	            
	            }
				
			}
		});
		*/		
		
		
		
		tmapdata.findAroundNamePOI(point, "편의점",1, 99, new FindAroundNamePOIListenerCallback() {
			
			@Override
			public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItem) {
				
				for(int i = 0; i < poiItem.size(); i++)
	            {
					TMapPOIItem item = poiItem.get(i);
	            	
	            	LogManager.printLog("POI Name: " + item.getPOIName() + "," + 
	            						"Address: " + item.getPOIAddress().replace("null", ""));
	            }
				
			}
		});
		
		
		
	}
	
	
	public void tileSD()
	{
		mMapView.setTileType(mMapView.TILETYPE_NORMALTILE);
	}
	
	
	public void tileEx()
	{
		mMapView.setTileType(mMapView.TILETYPE_EXTENSIONTILE);
	}
	

	public void tileHD()
	{
		mMapView.setTileType(mMapView.TILETYPE_HDTILE);
	}
	
	
	public void setBicycle()
	{
		mMapView.setBicycleInfo(!mMapView.IsBicycleInfo());
	}
	
	
	
	public void setBicycleFacility()
	{
		mMapView.setBicycleFacilityInfo(!mMapView.isBicycleFacilityInfo());
	}
	
	
	
	public void invokeRoute()
	{	
		final TMapPoint point = mMapView.getCenterPoint();
		TMapData tmapdata = new TMapData();
				
		if(mMapView.isValidTMapPoint(point)) {
			
			tmapdata.convertGpsToAddress(point.getLatitude(), point.getLongitude(), new ConvertGPSToAddressListenerCallback() {
				
				@Override
				public void onConvertToGPSToAddress(String strAddress) {
					
					TMapTapi tmaptapi = new TMapTapi(MainActivity.this);
					
					String strBizAppID = "ROUTENTELS";
									
					float fY = (float)point.getLatitude();
					float fX = (float)point.getLongitude();
					
					tmaptapi.invokeRoute(strBizAppID, strAddress, fX, fY);
					
				}
			});
		}
	
	}
	
	
	public void invokeSetLocation()
	{				
		final TMapPoint point = mMapView.getCenterPoint();
		TMapData tmapdata = new TMapData();
		
		
		tmapdata.convertGpsToAddress(point.getLatitude(), point.getLongitude(), new ConvertGPSToAddressListenerCallback() {
			
			@Override
			public void onConvertToGPSToAddress(String strAddress) {
				
				TMapTapi tmaptapi = new TMapTapi(MainActivity.this);
				
				String strBizAppID = "abcde";
				
				float fY = (float)point.getLatitude();
				float fX = (float)point.getLongitude();
				
				tmaptapi.invokeSetLocation(strBizAppID, strAddress, fX, fY);
				
			}
		});
		
		
	}
	
	
	
	String strSearch = "";
	
	public void invokeSearchProtal()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("T MAP 통합 검색");

		final EditText input = new EditText(this);
		builder.setView(input);

		builder.setPositiveButton("확인", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        
		    	strSearch = input.getText().toString();
		        		    	
		    	new Thread()
				{
					@Override
					public void run()
					{	
						TMapTapi tmaptapi = new TMapTapi(MainActivity.this);
						
						String strBizAppID = "abcde";
												
						if(strSearch.trim().length() > 0)
							tmaptapi.invokeSearchPortal(strBizAppID, strSearch);						
					}
					
				}.start();
		        
		    }
		});
		builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		    }
		});

		builder.show();
		
	}
	
	
	public void tmapInstall()
	{
//		TMapTapi tmaptapi = new TMapTapi(MainActivity.this);
//		
//        Uri uri = Uri.parse(tmaptapi.getTMapDownUrl());
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
	}

	
	public void captureImage()
	{	
		/*
		Bitmap bitmap = mMapView.getCaptureImage();
		
		String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
		 
		File path = new File(sdcard + File.separator + "image_write");
	    if (!path.exists()) 
		   path.mkdir();
	    
	    File fileCacheItem = new File(path.toString() + File.separator + System.currentTimeMillis() + ".png");
        OutputStream out = null;
        
        
        try
        {
            fileCacheItem.createNewFile();
            out = new FileOutputStream(fileCacheItem);
 
            bitmap.compress(CompressFormat.JPEG, 90, out);
            
            out.flush();
            out.close();  
        }catch (Exception e) {
            e.printStackTrace();
        }
        */
		
		
		mMapView.getCaptureImage(20, new MapCaptureImageListenerCallback() {
			
			@Override
			public void onMapCaptureImage(Bitmap bitmap) {
				
				String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
				 
				File path = new File(sdcard + File.separator + "image_write");
			    if (!path.exists()) 
				   path.mkdir();
			    
			    File fileCacheItem = new File(path.toString() + File.separator + System.currentTimeMillis() + ".png");
		        OutputStream out = null;
		        
		        try
		        {
		            fileCacheItem.createNewFile();
		            out = new FileOutputStream(fileCacheItem);
		 
		            bitmap.compress(CompressFormat.JPEG, 90, out);
		            
		            out.flush();
		            out.close();  
		        }
		        catch (Exception e) 
		        {
		            e.printStackTrace();
		        }
				
			}
		});
        
        
	}
	
	
	private boolean bZoomEnable = false;
	
	public void disableZoom()
	{
		bZoomEnable = !bZoomEnable;
		mMapView.setUserScrollZoomEnable(bZoomEnable);
	}
	
	
	
	public void timeMachine() 
	{
		TMapData tmapdata = new TMapData();
		
		HashMap<String, String> pathInfo = new HashMap<String, String>();
		
		pathInfo.put("rStName", "T Tower");
		pathInfo.put("rStlat", Double.toString(37.566474));
		pathInfo.put("rStlon", Double.toString(126.985022));
		
		pathInfo.put("rGoName", "신도림");
		pathInfo.put("rGolat", "37.50861147");
		pathInfo.put("rGolon", "126.8911457");
		
		pathInfo.put("type", "arrival");
		
		Calendar calendar = Calendar.getInstance();
				
		Date currentTime = new Date();
		
		
		tmapdata.findTimeMachineCarPath(pathInfo,  currentTime, null);	
		
	}


		
}

