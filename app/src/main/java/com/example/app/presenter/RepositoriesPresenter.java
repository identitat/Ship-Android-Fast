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

package com.example.app.presenter;

import com.example.app.model.mapper.RepoDomainPresMapper;
import com.example.app.view.viewPresenter.RepositoriesView;
import com.example.domain.exception.DefaultErrorBundle;
import com.example.domain.exception.ErrorBundle;
import com.example.domain.interactor.DefaultSubscriber;
import com.example.domain.interactor.UseCase;
import com.example.domain.model.RepoDomain;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

public class RepositoriesPresenter implements Presenter<RepositoriesView> {

  private RepositoriesView view;

  private final UseCase getRepoListUseCase;
  private final RepoDomainPresMapper repoDomainPresMapper;

  @Inject
  public RepositoriesPresenter(
      @Named("repoList")
      UseCase getRepoListUseCase, RepoDomainPresMapper repoDomainPresMapper
  ) {
    this.getRepoListUseCase = getRepoListUseCase;
    this.repoDomainPresMapper = repoDomainPresMapper;
  }

  @Override
  public void initialize() {
    hideRetry();
    showLoading();
    loadRepositories();
  }

  @Override
  public void attachView(RepositoriesView view) {
    this.view = view;
  }

  @Override
  public void resume() {
  }

  @Override
  public void pause() {
  }

  @Override
  public void destroy() {
    if (getRepoListUseCase != null) {
      getRepoListUseCase.unsubscribe();
    }
    this.view = null;
  }

  private void loadRepositories() {
    getRepoListUseCase.execute(new RepositoryListSubscriber());
  }

  private void showLoading() {
    view.showLoading();
  }

  private void hideLoading() {
    view.hideLoading();
  }

  private void showRetry() {
    view.showRetry();
  }

  private void hideRetry() {
    view.hideRetry();
  }

  private void showError(ErrorBundle errorBundle) {
    view.showError(errorBundle);
  }

  private void showRepositories(List<RepoDomain> repoDomainList) {
    view.showRepositories(repoDomainPresMapper.transform(repoDomainList));
  }

  @RxLogSubscriber
  private final class RepositoryListSubscriber extends DefaultSubscriber<List<RepoDomain>> {

    @Override
    public void onCompleted() {
      RepositoriesPresenter.this.hideLoading();
    }

    @Override
    public void onError(Throwable e) {
      RepositoriesPresenter.this.hideLoading();
      RepositoriesPresenter.this.showRetry();
      RepositoriesPresenter.this.showError(new DefaultErrorBundle((Exception) e));
    }

    @Override
    public void onNext(List<RepoDomain> repositories) {
      RepositoriesPresenter.this.showRepositories(repositories);
    }
  }
}
