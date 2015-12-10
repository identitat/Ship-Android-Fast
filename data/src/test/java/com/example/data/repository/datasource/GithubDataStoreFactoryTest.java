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

import com.example.data.ApplicationTestCase;
import com.example.data.net.githubapi.GithubApiService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GithubDataStoreFactoryTest extends ApplicationTestCase {

  private GithubDataStoreFactory githubDataStoreFactory;

  @Mock
  GithubApiService mockGithubApiService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    githubDataStoreFactory = new GithubDataStoreFactory(RuntimeEnvironment.application, mockGithubApiService);
  }

  @Test
  public void testCreate() throws Exception {

    GithubDataStore githubDataStore = githubDataStoreFactory.create();

    assertThat(githubDataStore, is(notNullValue()));
    assertThat(githubDataStore, is(instanceOf(CloudGithubDataStore.class)));
  }
}