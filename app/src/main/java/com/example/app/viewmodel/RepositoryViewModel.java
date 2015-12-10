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

package com.example.app.viewmodel;

import android.databinding.ObservableInt;
import android.view.View;
import com.example.app.model.Repository;
import com.example.app.model.mapper.RepoDomainPresMapper;
import com.example.domain.exception.DefaultErrorBundle;
import com.example.domain.exception.ErrorBundle;
import com.example.domain.interactor.DefaultSubscriber;
import com.example.domain.interactor.UseCase;
import com.example.domain.model.RepoDomain;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Implementation of a Viewmodel.
 */
public class RepositoryViewModel implements ViewModel {

  private DataListener dataListener;

  public ObservableInt progressVisibility;
  public ObservableInt retryVisibility;

  private final UseCase getRepoListUseCase;
  private final RepoDomainPresMapper repoDomainPresMapper;

  @Inject
  public RepositoryViewModel(
      @Named("repoList")
      UseCase getRepoListUseCase, RepoDomainPresMapper repoDomainPresMapper
  ) {
    this.getRepoListUseCase = getRepoListUseCase;
    this.repoDomainPresMapper = repoDomainPresMapper;

    progressVisibility = new ObservableInt(View.GONE);
    retryVisibility = new ObservableInt(View.GONE);
  }

  @Override
  public void destroy() {
    if (getRepoListUseCase != null) {
      getRepoListUseCase.unsubscribe();
    }
  }

  public void initialize() {
    hideRetry();
    showLoading();
    loadRepositories();
  }

  private void loadRepositories() {
    getRepoListUseCase.execute(new RepositoryListSubscriber());
  }

  private void showLoading() {
    progressVisibility.set(View.VISIBLE);
  }

  private void hideLoading() {
    progressVisibility.set(View.GONE);
  }

  private void showRetry() {
    retryVisibility.set(View.VISIBLE);
  }

  private void hideRetry() {
    retryVisibility.set(View.GONE);
  }

  private void showError(ErrorBundle errorBundle) {
    if (dataListener != null) {
      dataListener.onError(errorBundle);
    }
  }

  private void showRepoList(List<RepoDomain> repositories) {
    if (dataListener != null) {
      dataListener.onRepositoriesChanged(repoDomainPresMapper.transform(repositories));
    }
  }

  public void setDataListener(DataListener dataListener) {
    this.dataListener = dataListener;
  }

  public interface DataListener {
    void onRepositoriesChanged(List<Repository> repositories);

    void onError(ErrorBundle errorBundle);
  }

  @RxLogSubscriber
  private final class RepositoryListSubscriber extends DefaultSubscriber<List<RepoDomain>> {

    @Override
    public void onCompleted() {
      RepositoryViewModel.this.hideLoading();
    }

    @Override
    public void onError(Throwable e) {
      RepositoryViewModel.this.hideLoading();
      RepositoryViewModel.this.showRetry();
      RepositoryViewModel.this.showError(new DefaultErrorBundle((Exception) e));
    }

    @Override
    public void onNext(List<RepoDomain> repositories) {
      RepositoryViewModel.this.showRepoList(repositories);
    }
  }
}
