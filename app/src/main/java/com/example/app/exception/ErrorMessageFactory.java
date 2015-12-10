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

package com.example.app.exception;

import android.content.Context;
import com.example.app.BuildConfig;
import com.example.app.R;
import com.example.data.exception.NetworkConnectionException;
import com.example.data.exception.ServerIssueException;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

  private ErrorMessageFactory() {
    //empty
  }

  /**
   * Creates a String representing an error message.
   *
   * @param context Context needed to retrieve string resources.
   * @param exception An exception used as a condition to retrieve the correct error message.
   * @return {@link String} an error message.
   */
  public static String create(Context context, Exception exception) {

    if (BuildConfig.DEBUG) {
      return exception.getMessage();
    } else if (exception instanceof NetworkConnectionException) {
      return context.getString(R.string.exception_message_no_connection);
    } else if (exception instanceof ServerIssueException) {
      return context.getString(R.string.exception_message_server_issue);
    }

    return context.getString(R.string.exception_message_generic);
  }
}
