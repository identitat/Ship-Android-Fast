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
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Analytics facade to allow multiple analytics tools
 */
@Singleton
public final class AnalyticsManager {

  private GoogleAnalyticsController googleAnalytics;

  @Inject
  public AnalyticsManager(Application application) {

    googleAnalytics = new GoogleAnalyticsController(application);
  }

  public void setScreen(String screenName) {

    googleAnalytics.handleScreenChange(screenName);
  }

  public void track(Event event) {

    googleAnalytics.handleEvent(event);
  }

  public void addTransaction(Product product, ProductAction productAction) {

    googleAnalytics.handleTransaction(product, productAction);
  }

}
