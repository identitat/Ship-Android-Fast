/*
 *     Copyright (c) 2015-present Victor Hidalgo Lorenzo
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.example.data.net.interceptor;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;
import java.io.IOException;

/**
 * Author: Victor Hidalgo
 * Date: 06.05.15.
 * Desc : Interceptor to rewrite cache header with necessary parameters for caching with okhttp
 */
public class CacheResponseInterceptor implements Interceptor {

  private static final String CACHE_CONTROL_PARAMS = "public, max-age=10800";

  @Override
  public Response intercept(Chain chain) throws IOException {
    Response originalResponse = chain.proceed(chain.request());
    return originalResponse.newBuilder().header("Cache-Control", CACHE_CONTROL_PARAMS).build();
  }
}
