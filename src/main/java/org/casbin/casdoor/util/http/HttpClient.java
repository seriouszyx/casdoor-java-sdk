// Copyright 2021 The casbin Authors. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.casbin.casdoor.util.http;

import com.squareup.okhttp.*;

import java.io.IOException;

public class HttpClient {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static String syncGet(String url) throws Exception{
        Request request = new Request.Builder().url(url).build();
        Response casdoorResponse = okHttpClient.newCall(request).execute();
        if (casdoorResponse.isSuccessful()) {
            return casdoorResponse.body().string();
        }
        return null;
    }

    public static String postString(String url, String objStr) throws IOException {
        MediaType MEDIA_TYPE = MediaType.parse("text/plain;charset=UTF-8");
        Request request = new Request.Builder().url(url)
                .post(RequestBody.create(MEDIA_TYPE,objStr)).build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }
}
