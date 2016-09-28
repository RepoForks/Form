package io.fiskur.form.creator;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import io.fiskur.form.Form;

public class Preview {
  private static final String PREFS = "io.fiskur.form.creator.PREFS";
  private static final String PREFS_FORM = "io.fiskur.form.creator.PREFS_FORM";
  public static Form form = null;

  public static void saveForm(Context context){
    context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putString(PREFS_FORM, objectToString(form)).apply();
  }

  public static void loadForm(Context context){
    form = (Form) stringToObject(context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(PREFS_FORM, null));
  }

  private static String objectToString(Serializable object) {
    String encoded = null;
    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
      objectOutputStream.writeObject(object);
      objectOutputStream.close();
      encoded = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(),0));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return encoded;
  }

  @SuppressWarnings("unchecked")
  private static Serializable stringToObject(String string){
    if(string == null){
      return null;
    }
    byte[] bytes = Base64.decode(string,0);
    Serializable object = null;
    try {
      ObjectInputStream objectInputStream = new ObjectInputStream( new ByteArrayInputStream(bytes) );
      object = (Serializable)objectInputStream.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    return object;
  }
}
