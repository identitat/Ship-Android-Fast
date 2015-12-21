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
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.example.app.AndroidApp;

/**
 * Utils class to handle device operations
 */
public class DeviceUtils {

  private DeviceUtils() {
  }

  public static void closeSoftKeyboardForActivity(Activity activity) {
    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
  }

  public static void openSoftKeyboardForActivity(Activity activity) {
    activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
  }

  public static void closeSoftKeyboardForFragment(View view) {
    ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
        view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY
    );
  }

  public static void showKeyboard(Context context, EditText focus) {
    if (focus != null) {
      InputMethodManager inputMethodManager = (InputMethodManager) AndroidApp.get(context).getSystemService(
          Context.INPUT_METHOD_SERVICE
      );
      focus.requestFocus();
      inputMethodManager.showSoftInput(focus, InputMethodManager.SHOW_FORCED);
    }
  }
}
