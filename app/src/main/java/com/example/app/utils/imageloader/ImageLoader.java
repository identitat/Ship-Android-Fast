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

package com.example.app.utils.imageloader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Interface to be implemented to load images into the app
 */
public interface ImageLoader {

  /**
   * Load an image
   *
   * @param url The web URL of an image.
   * @param imageView imageView The target ImageView to load the image into.
   */
  void loadImage(
      String url, ImageView imageView
  );

  /**
   * Load an image
   *
   * @param url The web URL of an image.
   * @param imageView imageView The target ImageView to load the image into.
   * @param placeholderOverride A placeholder to use in place of the default placholder.
   * @param crop crop True to apply a center crop to the image.
   */
  void loadImage(
      String url, ImageView imageView, Drawable placeholderOverride, boolean crop
  );

  /**
   * Load an image
   *
   * @param url The web URL of an image.
   * @param imageView imageView The target ImageView to load the image into.
   * @param placeholderOverride A placeholder to use in place of the default placholder.
   */
  void loadImage(
      String url, ImageView imageView, Drawable placeholderOverride
  );
}
