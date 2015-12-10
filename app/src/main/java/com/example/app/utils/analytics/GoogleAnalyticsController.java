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
import com.example.app.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.HashMap;

/**
 * Google Analytics implementation of Analytics Tracker
 */
public final class GoogleAnalyticsController implements AnalyticsTracker {

  static GoogleAnalytics analytics;
  static Tracker tracker;

  private String screenName;

  public GoogleAnalyticsController(Application application) {
    analytics = GoogleAnalytics.getInstance(application);
    tracker = analytics.newTracker(R.xml.app_tracker);
  }

  @Override
  public String getName() {
    return "GoogleAnalyticsController";
  }

  @Override
  public void handleEvent(Event event) {
    if (shouldSkipEvent(event)) {
      return;
    }
    mapEvent(event);
  }

  public void handleTransaction(Product product, ProductAction productAction) {
    if (product == null || productAction == null) {
      return;
    }
    HitBuilders.ScreenViewBuilder builder = new HitBuilders.ScreenViewBuilder().addProduct(product).setProductAction(
        productAction
    );
    tracker.setScreenName("transaction");
    tracker.send(builder.build());
  }

  @Override
  public void handleScreenChange(String screenName) {
    this.screenName = screenName;
    tracker.setScreenName(screenName);
  }

  @Override
  public boolean shouldSkipEvent(Event event) {
    return event.getType().equalsIgnoreCase("internal");
  }

  @Override
  public String getScreen() {
    return this.screenName;
  }

  private void mapEvent(Event event) {
    HitBuilders.EventBuilder builder =
        new HitBuilders.EventBuilder().setCategory(event.getType()).setAction(event.getName());

    if (event.getValue() != null) {
      builder.setLabel(event.getValue());
    }

    builder = mapPayload(builder, event.getPayload());

    tracker.send(builder.build());
  }

  private HitBuilders.EventBuilder mapPayload(
      HitBuilders.EventBuilder builder, HashMap<String, String> payload
  ) {
    if (payload.containsKey("subscription_duration")) {
      builder.setCustomDimension(0, payload.get("subscription_duration"));
    }
    return builder;
  }
}
