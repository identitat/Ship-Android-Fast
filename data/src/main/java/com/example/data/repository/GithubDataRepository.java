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

package com.example.data.repository;

import com.example.data.entity.mapper.RepoDataDomainMapper;
import com.example.data.repository.datasource.GithubDataStore;
import com.example.data.repository.datasource.GithubDataStoreFactory;
import com.example.domain.model.RepoDomain;
import com.example.domain.repository.GithubRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

@Singleton
public class GithubDataRepository implements GithubRepository {

  private final GithubDataStoreFactory githubDataStoreFactory;
  private final RepoDataDomainMapper repoDataDomainMapper;

  @Inject
  public GithubDataRepository(
      GithubDataStoreFactory githubDataStoreFactory, RepoDataDomainMapper repoDataDomainMapper
  ) {
    this.githubDataStoreFactory = githubDataStoreFactory;
    this.repoDataDomainMapper = repoDataDomainMapper;
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override
  public Observable<List<RepoDomain>> repos() {

    final GithubDataStore githubDataStore = this.githubDataStoreFactory.create();

    return githubDataStore.repoEntityList().map(repoEntities -> this.repoDataDomainMapper.transform(repoEntities));
  }
}
