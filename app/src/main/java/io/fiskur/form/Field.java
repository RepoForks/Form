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
  public static final String TYPE_SPACER = "SPACER";
  public static final String TYPE_SINGLE_CHOICE = "SINGLE_CHOICE";
  public static final String TYPE_MULTI_CHOICE = "MULTI_CHOICE";
  public static final String TYPE_BINARY_CHOICE = "BINARY_CHOICE";
  public static final String TYPE_FREE_TEXT = "FREE_TEXT";
  public static final String TYPE_IMAGE = "IMAGE";
  public static final String TYPE_POPUP_SUBGROUP = "POPUP_SUBGROUP";
  public static final String TYPE_SUBMIT = "SUBMIT";

  public String id;
  public String title;
  public String subtitle;
  public String config;
  public String url;
  public String text;
  public String type;
  public String parent = null;
  public String subgroupId;
  public List<Choice> choices;
  public boolean required;
  public Response response;
  public List<Group> groups;

  public boolean hasSubfields(){
    if(choices != null){
      for(Choice choice : choices){
        if(choice.subgroupId != null){
          return true;
        }
      }
      return false;
    }else{
      return false;
    }
    /*
    if(subfields != null && subfields.size() > 0){
      return true;
    }else{
      return false;
    }
    */
  }

  public String getPreviewLabel(){
    StringBuilder sb = new StringBuilder();
    sb.append(id);
    sb.append(": ");
    switch(type){
      case TYPE_STATIC_TEXT:
        if(title != null && !title.isEmpty()){
          sb.append(title);
          if((subtitle != null && !subtitle.isEmpty()) || (text != null && !text.isEmpty())){
            sb.append(" > ");
          }
        }
        if(subtitle != null && !subtitle.isEmpty()){
          sb.append(subtitle);
          if(text != null && !text.isEmpty()){
            sb.append(" > ");
          }
        }
        if(text != null && !text.isEmpty()){
          sb.append(text);
        }
        break;
      case TYPE_STATIC_IMAGE:
      case TYPE_CURRENT_DATE:
      case TYPE_DATE:
      case TYPE_TIME:
      case TYPE_FREE_TEXT:
        sb.append(text);
        break;
      case TYPE_DIVIDER:
        sb.append("--------------------------------------------------------");
        break;
      case TYPE_YES_NO:
        sb.append("YES_NO to do");
        break;
      case TYPE_SINGLE_CHOICE:
        sb.append("TYPE_SINGLE_CHOICE to do");
        break;
      case TYPE_MULTI_CHOICE:
        sb.append("TYPE_MULTI_CHOICE to do");
        break;
      default:
        sb.append("Unknown field type");

    }

    return  sb.toString();
  }

  public String getSubgroupId(String choiceId){
    for(Choice choice: choices){
      if(choice.id.equals(choiceId)){
        return choice.subgroupId;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return id + ": " + type;
  }
}
