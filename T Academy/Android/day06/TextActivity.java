package org.kimjw.text;

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
	//�乮�ڿ�
	String str = "�뱸 �������� ���Ǽ��� �ڸ��� ������ ��� �����ڸ� ���� �������� �ǻ�� ��ȣ�� �������� �Ű����� ġ���ϰ� �������� �ִ�.���������� ������ �� ���� ���Ǽ����� ������ �� �� �� ������ ���� �ڸ��� ��� ������, �ֱ� �ǽ��� ������ ���Ǽ��� ���� 8���� �ĺ��� ��û�ߴ�. �̵� 8���� ��� �������� �ǻ��� 5��, ��ȣ�� 1��, ������ ������ 2�� ������ ������.Ư�� ���� 4�� ����� ���Ǽ��� �ڸ��� �ΰ� ���ǰ迭�� �����ϴ� �ǻ� ������ ��ȣ�� ������ ���� �ڽ��� �������� ������ ����ž� �Ѵٰ� �����ϰ� �־� ����� �ָ�ȴ�. ���� ���Ǽҿ� �����ϸ鼭 ��ȣ������ ��û�� ȫ�ƹ���(55) �����, �ǻ������� ��û�� Ȳ�ƹ���(41) �����ǻ簡 ������ �ĺ��� �ŷеǸ鼭 �� ������ ������ �������� ������ �����̴�. �뱸���ǻ�ȸ�� �ֱ� �������� ������ ���� �� �̾� 26�� 40������ ������ ��� ��û �տ��� ��ȸ�� ���� \"���Ǽ� ��ġ ���ù��� ���Ǽ��忡 �ǻ縦 �켱������ �����ؾ� �Ѵ�\"�� ���ظ� ������. ������ �뱸��ȣ��ȸ ���� \"�ǻ�ȸ�� �����ϴ� ���ù��� ������ �����α�����ȸ���� '���� ����'�� �Ǵ��ϰ� �� ������ �ǰ��� ����̱� ������ ū �ǹ̰� ����\"�� �ݹ��ߴ�.���� ������ �� ���Ǽ����� �Ӹ��Ѵٴ� ��ħ�� ���� ���� 2��, �ùδ�ü 1��, ����� 1��, ��ȣ�� 1��, ���ǿ� 1��, ������ ���� 1�� �� 7������ �̹� �ɻ�����ȸ�� �ٷ� ����. ���� �̸� �ð� �ȿ� �ɻ����� ���� ���Ǽ��忡 ������ �ĺ��� 8���� ���� �ɻ縦 ���� �ӱ� 2���� ������ �����ϱ�� �ߴ�.�豤�� ������ �α�û���� \"���Ǽ��� �ӿ��� �ѷ��ΰ� ������ ���� �������� �ɻ�����ȸ�� ������ �ð��\"��\"�� ����ȸ���� �����ϰ� ������ ������ ���� ���� ���Ǽ����� ������ ���� �״�� �����ڴ�\"�� ���ߴ�.";
	
	int strLength = str.length();
	

	int loc[] = new int[20]; // ������ ���۹��ڿ� ��ġ ��
	int page = 1;//������ ��ġ
	int cnt = 0 ;// ���ڿ��� ��ġ��
	Paint paint; // paint ������� 
	Canvas mCanvas ; //��� Canvas
	
	
	int lcdW ; // MyView ����
	int lcdH ; // MyView ����
	
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
			paint.setAntiAlias(true); // �۾� ���� ó��
			
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			if(mCanvas == null){
				mCanvas = canvas;
				lcdH = getHeight();	//MyView ���� ���ϱ�
				lcdW = this.getWidth();//MyView ���� ���ϱ�
			}
			canvas.drawColor(Color.WHITE);	//�ܻ� ����
			drawTitle("��� ����");	//��� Ÿ��Ʋ ó��
			drawText(5, 10, 100);	//���ڿ� ���
			drawMenu();//�Ʒ� �޴� ���
		}
		//Ÿ��Ʋ ���� �׸��� �޼ҵ�
		void drawTitle(String title){
			paint.setColor(Color.BLUE);
			paint.setTextSize(30);
			paint.setTextAlign(Paint.Align.CENTER);	//Ÿ��Ʋ �߾����� 
			mCanvas.drawText(title, lcdW/2, 40, paint);	//���ڿ� �߾� ���
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(Color.RED);
			mCanvas.drawRoundRect(new RectF(lcdW/2-100,9,lcdW/2+100,44), 3, 3, paint);
		}
		void drawText(int lineSize, int left, int top){
			paint.setTextAlign(Paint.Align.LEFT);//���ڿ� ���� ���� ���
			
			paint.setColor(Color.RED);
			paint.setTextSize(20);
			mCanvas.drawText("���������� : " + page, 80, 60, paint);
			float chW;							// ���ڿ��� ����
			float chH = paint.getTextSize() + 2;// ���ڿ��� ����
			float chX;	//���� ��� X
			float chY;	//���� ��� Y
			chX = left;	//�ʱⰪ ����
			chY = top;	//�ʱⰪ ����
			int idx = loc[page-1];//���� ��ġ ������
			aaa:for(int i = 0 ; i < lineSize; i++){//��� ���� ��ŭ �ݺ�
				while(chX <= lcdW-20){	//lcd ���� ��ŭ �ݺ�
					if(idx >= strLength){	//������ ���� ����� �ݺ��� �����
						break aaa;
					}
					mCanvas.drawText(str, idx, idx+1, chX,chY, paint);					
					chW = paint.measureText(str, idx, idx+1); //���� ���� ���ϱ�
					chX += chW;	//���� ���� �����ϱ�
					idx++;	//���� ���� ��ġ ���
				}
				chX = left;	//���� ��ǥ�� �ʱ�ȭ
				chY += chH;	//���� ���� ����
			}
			loc[page] = idx;//���� ������ ������ġ			
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
			mCanvas.drawText("����", 0, lcdH, paint);
			paint.setTextAlign(Paint.Align.CENTER);
			mCanvas.drawText("ó��", lcdW/2, lcdH, paint);
			paint.setTextAlign(Paint.Align.RIGHT);
			mCanvas.drawText("����", lcdW, lcdH, paint);
			paint.setTextAlign(Paint.Align.LEFT);
		}
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			float x = event.getX();
			float y = event.getY();
			if(y >= lcdH-30){
				if(x < 60){
					// ���� ��ư ó��
					Log.i(TAG, "previous");
					if(page != 1){
						page--;
						invalidate();
					}
				}else if((x >=(lcdW/2-30)) && (x <=(lcdW/2+30))){
					//ó�� ��ư ó��
					Log.i(TAG, "first");
					page = 1;
					invalidate();
				}else if(x > lcdW-60){
					//���� ��ư ó��
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