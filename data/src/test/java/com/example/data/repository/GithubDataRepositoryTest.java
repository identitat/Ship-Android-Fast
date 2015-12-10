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

import com.example.data.ApplicationTestCase;
import com.example.data.entity.RepoEntity;
import com.example.data.entity.mapper.RepoDataDomainMapper;
import com.example.data.repository.datasource.GithubDataStore;
import com.example.data.repository.datasource.GithubDataStoreFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class GithubDataRepositoryTest extends ApplicationTestCase {

  private GithubDataRepository githubDataRepository;

  @Mock
  private GithubDataStoreFactory mockGithubDataStoreFactory;
  @Mock
  private RepoDataDomainMapper mockRepoDataDomainMapper;
  @Mock
  private GithubDataStore mockGithubDataStore;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    githubDataRepository = new GithubDataRepository(mockGithubDataStoreFactory, mockRepoDataDomainMapper);

    given(mockGithubDataStoreFactory.create()).willReturn(mockGithubDataStore);
  }

  @Test
  public void testRepos() {
    List<RepoEntity> repoList = new ArrayList<>();
    given(mockGithubDataStore.repoEntityList()).willReturn(Observable.just(repoList));

    githubDataRepository.repos();

    verify(mockGithubDataStoreFactory).create();
    verify(mockGithubDataStore).repoEntityList();
  }
}