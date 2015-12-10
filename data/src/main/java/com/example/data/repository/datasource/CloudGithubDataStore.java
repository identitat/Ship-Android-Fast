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

import com.example.data.entity.RepoEntity;
import com.example.data.net.githubapi.GithubApi;
import java.util.List;
import rx.Observable;

/**
 * {@link GithubDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudGithubDataStore implements GithubDataStore {

  private final GithubApi githubApi;

  public CloudGithubDataStore(GithubApi githubApi) {
    this.githubApi = githubApi;
  }

  @Override
  public Observable<List<RepoEntity>> repoEntityList() {
    return this.githubApi.repoEntityList();
  }
}
