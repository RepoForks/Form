package io.fiskur.form;

import android.content.SharedPreferences;

public class FieldPrefs {
  public static final String PREFS_KEY = "io.fiskur.form.PREFS";
  private static final FieldPrefs instance = new FieldPrefs();


  public static FieldPrefs getInstance() {
    return instance;
  }

  private SharedPreferences prefs;

  public void init(SharedPreferences prefs){
    this.prefs = prefs;
  }

  public String getText(String fieldId){
      return prefs.getString(fieldId, "");
  }

  public void saveText(String fieldId, String context){
    prefs.edit().putString(fieldId, context).apply();
  }
}
