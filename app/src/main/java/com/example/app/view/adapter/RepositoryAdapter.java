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

package com.example.app.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.app.R;
import com.example.app.databinding.ViewRepoRowBinding;
import com.example.app.model.Repository;
import com.example.app.viewmodel.ItemRepoViewModel;
import java.util.Collections;
import java.util.List;

/**
 * @author victor
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepoViewHolder> {

  List<Repository> repositoryList;

  public RepositoryAdapter() {
    this.repositoryList = Collections.emptyList();
  }

  public RepositoryAdapter(List<Repository> repositoryList) {
    this.repositoryList = repositoryList;
  }

  @Override
  public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewRepoRowBinding binding = DataBindingUtil.inflate(
        LayoutInflater.from(parent.getContext()), R.layout.view_repo_row, parent, false
    );
    return new RepoViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(RepoViewHolder holder, int position) {
    holder.bindRepository(repositoryList.get(position));
  }

  @Override
  public int getItemCount() {
    return (this.repositoryList != null) ? this.repositoryList.size() : 0;
  }

  public void setRepositoryList(List<Repository> repositoryList) {
    this.repositoryList = repositoryList;
  }

  public class RepoViewHolder extends RecyclerView.ViewHolder {

    final ViewRepoRowBinding binding;

    public RepoViewHolder(ViewRepoRowBinding binding) {
      super(binding.cvRepo);
      this.binding = binding;
    }

    void bindRepository(Repository repository) {
      if (binding.getViewModel() == null) {
        binding.setViewModel(new ItemRepoViewModel(itemView.getContext(), repository));
      } else {
        binding.getViewModel().setRepository(repository);
      }
    }
  }
}
