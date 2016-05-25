import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
public class GCMServer {
	
	public GCMServer(){
		String key = "AIzaSyBiXhqzKqqgUxV3mE95jtVKHdGow7NENSI";
		String regId = "APA91bGIGF2vF8v3Cm8VhRg_B5AEGF-AWWST31kmgqZtzmyDzJfFrS34TEb_gXfVrG8DdmDJP5g3Cp4z9y2xtqsRlCSip1qUikv0UYsGLV1mcrFy9TewJsLVlG3ncI7iGaOtLKMS-TQMjmIQBZxB5JF-Rff5Qk-wSycpJd-CNUlINKowNfTMWVA";
		
		System.out.println("key : " + key);
		Sender sender = null;
		
		//여러장비에 한꺼번에 보내기
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("tempregid1");
//		list.add("tempregid2");
//		list.add("tempregid3");
//		
//		MulticastResult multi = null;
//		List<Result> results = null;
		
		try{
			sender = new Sender(key);
		}catch(Exception e){
			System.out.println("생성: " + e);
		}

		Message message = new Message.Builder().addData("code", "1").addData("msg", "android").build();
//		Message message = new Message.Builder().addData("code", "2").addData("msg", "http://m.naver.com").build();
//		Message message = new Message.Builder().addData("code", "3").addData("msg", "37.56468648536046,126.98217734415019").build();

		
		try{
			//여러장비에 한꺼번에 보내기
//			multi = sender.send(message, list, 5);
//			results = multi.getResults();
			
			Result result = sender.send(message, regId, 5); //메세지 전달
			if (result.getMessageId() != null) {

				System.out.println("send success");
				String canonicalRegId = result
						.getCanonicalRegistrationId();
				if (canonicalRegId != null) {
					
					System.out.println("Also updated registration id!");
				}
			} else {
				String error = result.getErrorCodeName();
				if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
					System.out.println("Unregistered device #");
					
				} else {
					System.out.println("Error sending message to device #");
				}
			}
		}catch(Exception e){
			System.out.println("오류 확인 : " + e);
		}
	}

	public static void main(String[] args) {
		new GCMServer();
	}
}
