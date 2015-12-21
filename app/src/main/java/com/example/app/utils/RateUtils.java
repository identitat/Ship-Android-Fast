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

package com.example.app.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.example.app.AndroidApp;
import timber.log.Timber;

import static com.example.commons.LogUtils.makeLogTag;

/**
 * Utils class to handle App Rating operations
 */
public class RateUtils {

  private static final String TAG = makeLogTag("RateUtils");

  private RateUtils() {
  }

  public static void goToMarketPlaceForRating(Activity activity) {
    if (activity == null) {
      throw new RuntimeException("Activity may not be null when calling goToMarketPlaceForRating");
    }
    try {
      activity.startActivity(
          new Intent(
              "android.intent.action.VIEW", Uri.parse(
              "market://details?id=" + AndroidApp.get(activity).getPackageName()
          )
          )
      );
    } catch (Exception e) {
      Timber.e(TAG, "Failed to start view intent to open up the market place for rating");
    }
  }
}
