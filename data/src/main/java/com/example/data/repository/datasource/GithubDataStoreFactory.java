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

package com.example.data.repository.datasource;

import android.content.Context;
import com.example.data.net.githubapi.GithubApiImpl;
import com.example.data.net.githubapi.GithubApiService;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link GithubDataStore}.
 */
@Singleton
public class GithubDataStoreFactory {

  private final Context context;
  private final GithubApiService githubApiService;

  @Inject
  public GithubDataStoreFactory(Context context, GithubApiService githubApiService) {
    this.context = context;
    this.githubApiService = githubApiService;
  }

  public GithubDataStore create() {
    GithubDataStore githubDataStore;

    githubDataStore = createCloudDataStore();

    return githubDataStore;
  }

  private GithubDataStore createCloudDataStore() {
    return new CloudGithubDataStore(new GithubApiImpl(context, githubApiService));
  }
}
