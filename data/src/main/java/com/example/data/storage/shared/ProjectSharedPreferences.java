package com.example.data.storage.shared;

import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProjectSharedPreferences implements Preferences {

  /** The m preferences. */
  protected SharedPreferences sharedPreferences;

  /** The m editor. */
  protected SharedPreferences.Editor editor;

  /** The Constant SHARED_PREFERENCES_FILE. */
  private static final String SHARED_PREFERENCES_FILE = "project_preferences";

  /** The Constant PROJECT_DATA. */
  private static final String PROJECT_DATA = "project_data";

  /**
   * Instantiates a new shared preferences data file.
   *
   * @param context the context
   */
  @Inject
  public ProjectSharedPreferences(Context context) {
    sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
  }

  public void apply() {
    if (editor != null) {
      editor.apply();
      editor = null;
    }
  }

  public SharedPreferences.Editor getEditor() {
    if (editor == null) {
      editor = sharedPreferences.edit();
    }

    return editor;
  }

  public void clearEditor() {
    getEditor().clear();
    apply();
  }

  /**
   * Gets the project data json string.
   *
   * @return the project data
   */
  public String getProjectData() {
    return sharedPreferences.getString(PROJECT_DATA, null);
  }

  /**
   * Sets the project data.
   *
   * @param projectData the new project data
   */
  public SharedPreferences.Editor setProjectData(String projectData) {
    return getEditor().putString(PROJECT_DATA, projectData);
  }
}
