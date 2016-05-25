package com.example.drawproject;



import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;



public class TextActivity extends Activity {
	private static final String TAG = "TextActivity";
    /** Called when the activity is first created. */
	//긴문자열
	String str = "대구 수성구의 보건소장 자리가 공석인 가운데 후임자를 놓고 전문직인 의사와 간호사 직군끼리 신경전이 치열하게 벌어지고 있다.수성구에는 지난달 말 전임 보건소장이 사임한 뒤 한 달 가까이 소장 자리가 비어 있으며, 최근 실시한 개방직 보건소장 공모에 8명의 후보가 신청했다. 이들 8명은 출신 직군별로 의사직 5명, 간호직 1명, 보건직 공무원 2명 등으로 나뉜다.특히 현재 4급 상당의 보건소장 자리를 두고 보건계열에 종사하는 의사 직군과 간호사 직군이 서로 자신의 직군에서 소장이 배출돼야 한다고 주장하고 있어 결과가 주목된다. 현재 보건소에 재직하면서 간호직으로 신청한 홍아무개(55) 과장과, 의사직으로 신청한 황아무개(41) 진료의사가 유력한 후보로 거론되면서 양 직군의 자존심 경쟁으로 번지는 형국이다. 대구시의사회는 최근 수성구에 공문을 보낸 데 이어 26일 40여명이 참석한 가운데 구청 앞에서 집회를 열어 \"보건소 설치 관련법상 보건소장에 의사를 우선적으로 선발해야 한다\"는 견해를 밝혔다. 하지만 대구간호사회 쪽은 \"의사회가 주장하는 관련법은 지난해 국가인권위원회에서 '직종 차별'로 판단하고 법 개정을 권고한 사안이기 때문에 큰 의미가 없다\"고 반박했다.구는 다음달 초 보건소장을 임명한다는 방침에 따라 교수 2명, 시민단체 1명, 언론인 1명, 변호사 1명, 구의원 1명, 수성구 국장 1명 등 7명으로 이미 심사위원회를 꾸려 놨다. 구는 이른 시간 안에 심사위를 열어 보건소장에 응모한 후보자 8명을 상대로 심사를 벌여 임기 2년의 소장을 결정하기로 했다.배광식 수성구 부구청장은 \"보건소장 임용을 둘러싸고 말들이 많아 구에서는 심사위원회에 결정을 맡겼다\"며\"이 위원회에서 투명하고 공정한 절차를 거쳐 신임 보건소장을 결정해 오면 그대로 따르겠다\"고 말했다.";
	
	int strLength = str.length();
	

	int loc[] = new int[20]; // 페이지 시작문자열 위치 값
	int page = 1;//페이지 위치
	int cnt = 0 ;// 문자열의 위치값
	Paint paint; // paint 멤버변수 
	Canvas mCanvas ; //멤버 Canvas
	
	
	int lcdW ; // MyView 넓이
	int lcdH ; // MyView 높이
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView view = new MyView(this);
        setContentView(view);
    }
    class MyView extends View{

		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			paint = new Paint();
			paint.setAntiAlias(true); // 글씨 벡터 처리
			
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			if(mCanvas == null){
				mCanvas = canvas;
				lcdH = getHeight();	//MyView 높이 구하기
				lcdW = this.getWidth();//MyView 넓이 구하기
			}
			canvas.drawColor(Color.WHITE);	//잔상 제거
			drawTitle("기사 내용");	//기사 타이틀 처리
			drawText(5, 10, 100);	//문자열 출력
			drawMenu();//아래 메뉴 출력
		}
		//타이틀 영역 그리기 메소드
		void drawTitle(String title){
			paint.setColor(Color.BLUE);
			paint.setTextSize(30);
			paint.setTextAlign(Paint.Align.CENTER);	//타이틀 중앙정렬 
			mCanvas.drawText(title, lcdW/2, 40, paint);	//문자열 중앙 출력
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(Color.RED);
			mCanvas.drawRoundRect(new RectF(lcdW/2-100,9,lcdW/2+100,44), 3, 3, paint);
		}
		void drawText(int lineSize, int left, int top){
			paint.setTextAlign(Paint.Align.LEFT);//문자열 왼쪽 정력 출력
			
			paint.setColor(Color.RED);
			paint.setTextSize(20);
			mCanvas.drawText("현재페이지 : " + page, 80, 60, paint);
			float chW;							// 문자열의 넓이
			float chH = paint.getTextSize() + 2;// 문자열의 높이
			float chX;	//문자 출력 X
			float chY;	//문자 출력 Y
			chX = left;	//초기값 설정
			chY = top;	//초기값 설정
			int idx = loc[page-1];//문자 위치 얻어오기
			aaa:for(int i = 0 ; i < lineSize; i++){//출력 라인 만큼 반복
				while(chX <= lcdW-20){	//lcd 넓이 만큼 반복
					if(idx >= strLength){	//마지막 문자 출력후 반복문 벗어나기
						break aaa;
					}
					mCanvas.drawText(str, idx, idx+1, chX,chY, paint);					
					chW = paint.measureText(str, idx, idx+1); //문자 넓이 구하기
					chX += chW;	//문자 넓이 누적하기
					idx++;	//다음 문자 위치 얻기
				}
				chX = left;	//왼쪽 좌표값 초기화
				chY += chH;	//다음 라인 진행
			}
			loc[page] = idx;//다음 페이지 시작위치			
		}
		void drawMenu(){
			paint.setColor(Color.RED);
			paint.setStyle(Paint.Style.FILL);
			mCanvas.drawRect(0, lcdH-30, lcdW, lcdH, paint);
			paint.setColor(Color.CYAN);
			mCanvas.drawRect(0,lcdH-30, 60,lcdH, paint);
			mCanvas.drawRect(lcdW/2-30,lcdH-30, lcdW/2+30,lcdH, paint);
			mCanvas.drawRect(lcdW-60,lcdH-30, lcdW,lcdH, paint);
//			paint.setTextAlign(Paint.Align.LEFT);
			paint.setColor(Color.BLACK);
			paint.setTextSize(30);
			paint.setTextAlign(Paint.Align.LEFT);
			mCanvas.drawText("이전", 0, lcdH, paint);
			paint.setTextAlign(Paint.Align.CENTER);
			mCanvas.drawText("처음", lcdW/2, lcdH, paint);
			paint.setTextAlign(Paint.Align.RIGHT);
			mCanvas.drawText("다음", lcdW, lcdH, paint);
			paint.setTextAlign(Paint.Align.LEFT);
		}
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			float x = event.getX();
			float y = event.getY();
			if(y >= lcdH-30){
				if(x < 60){
					// 이전 버튼 처리
					Log.i(TAG, "previous");
					if(page != 1){
						page--;
						invalidate();
					}
				}else if((x >=(lcdW/2-30)) && (x <=(lcdW/2+30))){
					//처음 버튼 처리
					Log.i(TAG, "first");
					page = 1;
					invalidate();
				}else if(x > lcdW-60){
					//다음 버튼 처리
					Log.i(TAG, "next");
					if(loc[page] != strLength){
						page++;
						invalidate();
					}
				}				
			}
			return super.onTouchEvent(event);
		}    	
    }    
}