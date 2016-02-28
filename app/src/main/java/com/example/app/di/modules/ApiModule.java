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

package com.example.app.di.modules;

import android.content.Context;
import com.example.data.net.githubapi.GithubApiService;
import com.example.data.net.interceptor.CacheResponseInterceptor;
import com.example.data.net.interceptor.UserAgentInterceptor;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A dagger module that provides retrofit services
 */
@Module
public class ApiModule {

  private static final int CACHE_SIZE_10MB = 10485760;

  @Provides
  @Singleton
  GithubApiService provideGithubApiService(
      @Named("cache")
      OkHttpClient client,
      @Named("apiUrl")
      String endPoint, Gson gson
  ) {
    return new Retrofit.Builder().baseUrl(endPoint)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
        .create(GithubApiService.class);
  }

  @Provides
  @Singleton
  @Named("noCache")
  OkHttpClient provideNoCacheOkHttpClient(
      HttpLoggingInterceptor loggingInterceptor,
      @Named("userAgent")
      String userAgentValue
  ) {
    return new OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(new UserAgentInterceptor(userAgentValue))
        .addNetworkInterceptor(new StethoInterceptor())
        .build();
  }

  @Provides
  @Singleton
  @Named("cache")
  OkHttpClient provideOkHttpClient(
      Cache cache,
      HttpLoggingInterceptor loggingInterceptor,
      @Named("userAgent")
      String userAgentValue
  ) {
    return new OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(new UserAgentInterceptor(userAgentValue))
        .addNetworkInterceptor(new StethoInterceptor())
        .addNetworkInterceptor(new CacheResponseInterceptor())
        .build();
  }

  @Provides
  @Singleton
  Cache provideCache(Context context) {
    File baseDir = context.getCacheDir();
    File cacheDir = new File(baseDir, "HttpResponseCache");
    return new Cache(cacheDir, CACHE_SIZE_10MB);
  }
}
