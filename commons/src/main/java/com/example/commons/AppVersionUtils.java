package com.example.commons;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class AppVersionUtils {
  /**
   * get the application version
   *
   * @return version String
   */

  public static String getAppVersion(Context context) {
    String version = "";
    try {
      PackageManager manager = context.getPackageManager();
      PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
      version = info.versionName;
    } catch (Exception e) {
      Log.e("YourActivity", "Error getting version");
    }

    return version;
  }
}
