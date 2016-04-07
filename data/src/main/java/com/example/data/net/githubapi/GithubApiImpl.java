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

package com.example.data.net.githubapi;

import android.content.Context;
import com.example.commons.DeviceNetworkUtils;
import com.example.data.entity.RepoEntity;
import com.example.data.exception.NetworkConnectionException;
import com.example.data.exception.ServerIssueException;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import java.util.List;
import retrofit2.Call;
import rx.Observable;
import timber.log.Timber;

/**
 * Api client implementation
 */
public class GithubApiImpl implements GithubApi {

  private final Context context;
  private final GithubApiService githubApiService;

  public GithubApiImpl(Context context, GithubApiService githubApiService) {
    if (context == null) {
      throw new IllegalArgumentException("Context cannot be null");
    }
    this.context = context;
    this.githubApiService = githubApiService;
  }

  @RxLogObservable
  @Override
  public Observable<List<RepoEntity>> repoEntityList() {
    return Observable.create(
        subscriber -> {
          if (DeviceNetworkUtils.isConnected(context)) {
            try {

              Call<List<RepoEntity>> call = githubApiService.listRepos("facebook");
              subscriber.onNext(call.execute().body());
              subscriber.onCompleted();
            } catch (Exception e) {

              Timber.e(e.getCause(), e.getMessage());
              subscriber.onError(new ServerIssueException(e.getCause()));
            }
          } else {

            subscriber.onError(new NetworkConnectionException());
          }
        }
    );
  }
}
