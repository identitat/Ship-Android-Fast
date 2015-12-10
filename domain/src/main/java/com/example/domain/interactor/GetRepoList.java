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

package com.example.domain.interactor;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.executor.ThreadExecutor;
import com.example.domain.repository.GithubRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection.
 */
public class GetRepoList extends UseCase {

  private final GithubRepository githubRepository;

  @Inject
  protected GetRepoList(
      ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, GithubRepository githubRepository
  ) {
    super(threadExecutor, postExecutionThread);
    this.githubRepository = githubRepository;
  }

  @Override
  protected Observable buildUseCaseObservable() {
    return this.githubRepository.repos();
  }
}
