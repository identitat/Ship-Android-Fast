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

import android.content.Context;
import android.databinding.BaseObservable;
import com.example.app.model.Repository;


public class ItemRepoViewModel extends BaseObservable implements ViewModel {

  private final Context context;
  private Repository repository;

  public ItemRepoViewModel(Context context, Repository repository) {
    this.context = context;
    this.repository = repository;
  }

  @Override
  public void destroy() {
    // No Async Calls
  }

  public int getId() {
    return repository.id();
  }

  public String getName() {
    return repository.name();
  }

  public void setRepository(Repository repository) {
    this.repository = repository;
  }
}
