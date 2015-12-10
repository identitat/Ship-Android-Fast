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

package com.example.app.utils.analytics;

import android.app.Application;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;

/**
 * Analytics facade to allow multiple analytics tools
 */
public final class AnalyticsFacade {

  private static AnalyticsFacade instance;

  private GoogleAnalyticsController googleAnalytics;

  private AnalyticsFacade(Application application) {
    googleAnalytics = new GoogleAnalyticsController(application);
  }

  public static void init(Application application) {
    instance = new AnalyticsFacade(application);
  }

  public static AnalyticsFacade getInstance() {
    if (instance == null) {
      throw new IllegalStateException("Call init() before getInstance()");
    }
    return instance;
  }

  public static void setScreen(String screenName) {

    if (instance == null) {
      return;
    }
    instance.googleAnalytics.handleScreenChange(screenName);
  }

  public static void track(Event event) {
    if (instance == null) {
      return;
    }
    instance.googleAnalytics.handleEvent(event);
  }

  public static void addTransaction(Product product, ProductAction productAction) {
    if (instance == null) {
      return;
    }
    instance.googleAnalytics.handleTransaction(product, productAction);
  }

  public static String getScreen() {
    if (instance == null) {
      return "";
    }
    return instance.googleAnalytics.getScreen();
  }
}
