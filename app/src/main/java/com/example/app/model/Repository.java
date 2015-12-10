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

package com.example.app.model;

import android.os.Parcelable;
import auto.parcel.AutoParcel;

@AutoParcel
public abstract class Repository implements Parcelable {

  public abstract int id();

  public abstract String name();

  public static Repository create(int id, String name) {
    return builder().id(id).name(name).build();
  }

  public static Builder builder() {
    return new AutoParcel_Repository.Builder();
  }

  @AutoParcel.Builder
  public interface Builder {
    Builder id(int id);

    Builder name(String name);

    Repository build();
  }
}
