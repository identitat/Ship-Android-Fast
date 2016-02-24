package com.example.data.storage.shared;

import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProjectSharedPreferences implements Preferences {

    /** The m preferences. */
    protected SharedPreferences mPreferences;

    /** The m editor. */
    protected SharedPreferences.Editor mEditor;

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
        mPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
    }

    public void apply() {
        if (mEditor != null) {
            mEditor.apply();
            mEditor = null;
        }
    }

    public SharedPreferences.Editor getEditor() {
        if (mEditor == null) {
            mEditor = mPreferences.edit();
        }

        return mEditor;
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
        return mPreferences.getString(PROJECT_DATA, null);
    }

    /**
     * Sets the project data.
     *
     * @param projectData the new project data
     */
    public void setProjectData(String projectData) {
        getEditor().putString(PROJECT_DATA, projectData);
    }
}
