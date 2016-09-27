package io.fiskur.form;

import java.io.Serializable;
import java.util.List;

public class Field implements Serializable{

  public static final String TYPE_STATIC_TEXT = "STATIC_TEXT";
  public static final String TYPE_STATIC_IMAGE = "STATIC_IMAGE";
  public static final String TYPE_CURRENT_DATE = "CURRENT_DATE";
  public static final String TYPE_DATE = "DATE";
  public static final String TYPE_TIME = "TIME";
  public static final String TYPE_YES_NO = "YES_NO";
  public static final String TYPE_DIVIDER = "DIVIDER";
  public static final String TYPE_SINGLE_CHOICE = "SINGLE_CHOICE";
  public static final String TYPE_MULTI_CHOICE = "MULTI_CHOICE";
  public static final String TYPE_FREE_TEXT = "FREE_TEXT";

  public String id;
  public String title;
  public String subtitle;
  public String text;
  public String type;
  public String parent = null;
  public List<Choice> choices;
  public boolean required;
  public List<Field> subfields;

  public boolean hasSubfields(){
    if(subfields != null && subfields.size() > 0){
      return true;
    }else{
      return false;
    }
  }

  @Override
  public String toString() {
    return id + ": " + type;
  }
}
