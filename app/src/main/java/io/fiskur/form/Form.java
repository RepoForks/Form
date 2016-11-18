package io.fiskur.form;

import java.io.Serializable;
import java.util.List;

public class Form implements Serializable {
  public String id;
  public String title;
  public String date;
  public String time;
  public String author;
  public String contact;
  public String startGroupId;
  public List<Group> groups;

  public List<Field> getGroupFields(String groupId){
    for(Group group : groups){
      if(group.id.equals(groupId)){
        return group.fields;
      }
    }
    return null;
  }

  public Group getGroup(String groupId){
    for(Group group : groups){
      if(group.id.equals(groupId)){
        return group;
      }
    }
    return null;
  }

  public Field findField(String fieldId){
    for(Group group : groups){
      for(Field field : group.fields){
        if(field.id.equals(fieldId)){
          return field;
        }
      }
    }
    return null;
  }

  public void addGroup(String fieldId, Group group){
    Field field = findField(fieldId);
    field.groups.add(group);
  }

  public void addField(String groupId, Field field){
    getGroupFields(groupId).add(field);
  }
}
