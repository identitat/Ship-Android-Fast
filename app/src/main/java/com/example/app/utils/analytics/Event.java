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

import java.util.Date;
import java.util.HashMap;

/**
 * Event sent through Analytics
 */
public class Event {

  private final String type;
  private final String name;
  private final String value;
  private final HashMap<String, String> payload;
  private final Long timestamp;
  private final String screen;

  public Event(String type, String name) {
    this(type, name, null, new HashMap<String, String>(0));
  }

  public Event(String type, String name, String value) {
    this(type, name, value, new HashMap<String, String>(0));
  }

  public Event(String type, String name, HashMap<String, String> payload) {
    this(type, name, null, payload);
  }

  public Event(String type, String name, String value, HashMap<String, String> payload) {
    this(type, name, value, payload, new Date().getTime());
  }

  public Event(
      String type, String name, String value, HashMap<String, String> payload, Long timestamp
  ) {
    this(type, name, value, payload, timestamp, AnalyticsFacade.getScreen());
  }

  Event(
      String type, String name, String value, HashMap<String, String> payload, Long timestamp, String screen
  ) {
    this.type = type;
    this.name = name;
    this.value = value;
    this.payload = payload;
    this.timestamp = timestamp;
    this.screen = screen;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

  public HashMap<String, String> getPayload() {
    return payload;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public String getScreen() {
    return screen;
  }

  @Override
  public String toString() {
    return "Event{" +
        "type='" + type + '\'' +
        ", name='" + name + '\'' +
        ", value='" + value + '\'' +
        ", payload=" + payload +
        ", timestamp=" + timestamp +
        ", screen=" + screen +
        '}';
  }
}
