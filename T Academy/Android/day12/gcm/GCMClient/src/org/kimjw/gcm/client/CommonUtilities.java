/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kimjw.gcm.client;

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities {

    /**
     * 응용서버의 기본 주소 설정
     */
    static final String SERVER_URL = "http://1.234.7.150:8080";

    /**
     * Google API project id registered to use GCM. 
     * Sender_id 값 변경하기
     */
    static final String SENDER_ID = "908544295302";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "GCMDemo";

    /**
     * 앞에문자열 본인 패키지명으로 변경함.
     */
    static final String DISPLAY_MESSAGE_ACTION =
            "kr.co.tacademy.tgcmtest.DISPLAY_MESSAGE";

    /**
     * UI 쉽게 변경할수 있도록 공용으로 방송 보내기
     */
    static final String EXTRA_MESSAGE = "message";
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
