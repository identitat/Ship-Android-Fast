package com.example.data.storage.shared;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * rang
 * <p/>
 * Created by Lluis Ruscalleda Abad on 15/07/15.
 * Copyright (c) 2015 Identitat SL. All rights reserved.
 */
@Singleton
public class ProjectSharedPreferences {

    /** The m preferences. */
    protected SharedPreferences mPreferences;

    /** The m editor. */
    protected SharedPreferences.Editor mEditor;

    /** The Constant SHARED_PREFERENCES_FILE. */
    private static final String SHARED_PREFERENCES_FILE = "rang_project_data";

    /** The Constant PROJECT_DATA. */
    private static final String PROJECT_DATA = "project_data";

    /** The Constant INFO_PROJECT_DATA. */
    private static final String INFO_PROJECT_DATA = "info_project_data";


    /** The Constant KEY_RANG_CONTEXT_BEACON_REGIONS define the rang beacon regions list. */
    private static final String KEY_RANG_CONTEXT_BEACON_REGIONS = "rang_context_beacon_regions";

    /** The Constant KEY_RANG_CONTEXT_GEOFENCE define the rang geofences regions. */
    private static final String KEY_RANG_CONTEXT_GEOFENCE = "rang_context_geofence_regions";

    /** The Constant KEY_RANG_CONTEXT_SSID  define the rang SSID region. */
    private static final String KEY_RANG_CONTEXT_SSID = "rang_context_ssid";

    /**
     * Instantiates a new session data.
     *
     * @param context the context
     */
    @Inject
    public ProjectSharedPreferences(Context context) {
        mPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE, 0);
    }

    /**
     * Apply.
     */
    public void apply() {
        if (mEditor != null) {
            mEditor.apply();
            mEditor = null;
        }
    }

    /**
     * Gets the editor.
     *
     * @return the editor
     */
    protected SharedPreferences.Editor getEditor() {
        if (mEditor == null) {
            mEditor = mPreferences.edit();
        }

        return mEditor;
    }

    /**
     * Clears the editor.
     */
    public void clearEditor(){
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

    /**
     * Gets the project data json string.
     *
     * @return the info project data
     */
    public String getInfoProjectData() {
        return mPreferences.getString(INFO_PROJECT_DATA, null);
    }

    /**
     * Sets the project data.
     *
     * @param infoProjectData the new info project data
     */
    public void setInfoProjectData(String infoProjectData) {
        getEditor().putString(INFO_PROJECT_DATA, infoProjectData);
    }

    /**
     * Gets a rang beacons list.
     *
     * @return the flag of pending out call
     */
    public String getRangContextBeacons() {
        return mPreferences.getString(KEY_RANG_CONTEXT_BEACON_REGIONS, null);
    }

    /**
     * Sets a list of rang beacons context.
     *
     * @param rangContextBeacons the new beacon list
     */
    public void setRangContextBeacons(String rangContextBeacons) {
        getEditor().putString(KEY_RANG_CONTEXT_BEACON_REGIONS, rangContextBeacons);
    }

    /**
     * Gets a rang geofence list.
     *
     * @return the flag of pending out call
     */
    public String getRangContextGeofences() {
        return mPreferences.getString(KEY_RANG_CONTEXT_GEOFENCE, null);
    }

    /**
     * Sets a list of rang geofences context.
     *
     * @param rangContextGeofences the new state of pending out call falg
     */
    public void setRangContextGeofences(String rangContextGeofences) {
        getEditor().putString(KEY_RANG_CONTEXT_GEOFENCE, rangContextGeofences);
    }

    /**
     * Gets a rang ssid mask.
     *
     * @return the ssid rang mask
     */
    public String getRangContextSsid() {
        return mPreferences.getString(KEY_RANG_CONTEXT_SSID, null);
    }

    /**
     * Sets a rang ssid mask
     *
     * @param rangContextSsid the new state of pending out call falg
     */
    public void setRangContextSsid(String rangContextSsid) {
        getEditor().putString(KEY_RANG_CONTEXT_SSID, rangContextSsid);
    }

}
