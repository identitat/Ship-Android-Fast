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

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.app.R;
import javax.inject.Inject;

/**
 * Glide implementation of Image Loader
 */
public class GlideImageLoader implements ImageLoader {

  private static final ModelCache<String, GlideUrl> URL_CACHE = new ModelCache<String, GlideUrl>(150);

  private final BitmapTypeRequest<String> glideModelRequest;
  private final CenterCrop centerCrop;

  private int placeHolderResId = -1;

  @Inject
  public GlideImageLoader(Context context) {
    this.glideModelRequest = Glide.with(context).from(String.class).asBitmap();
    this.centerCrop = new CenterCrop(Glide.get(context).getBitmapPool());
  }

  @Override
  public void loadImage(
      String url, ImageView imageView, Drawable placeholderOverride
  ) {
    loadImage(url, imageView, placeholderOverride, false);
  }

  @Override
  public void loadImage(
      String url, ImageView imageView
  ) {
    loadImage(url, imageView, null, false);
  }

  @Override
  public void loadImage(
      String url, ImageView imageView, Drawable placeholderOverride, boolean crop
  ) {
    BitmapRequestBuilder request = beginImageLoad(url, crop).animate(R.anim.fade_in_fast);
    if (placeholderOverride != null) {
      request.placeholder(placeholderOverride);
    } else if (placeHolderResId != -1) {
      request.placeholder(placeHolderResId);
    }
    request.into(imageView);
  }

  public BitmapRequestBuilder beginImageLoad(
      String url, boolean crop
  ) {
    if (crop) {
      return glideModelRequest.load(url).transform(centerCrop);
    } else {
      return glideModelRequest.load(url);
    }
  }
}
