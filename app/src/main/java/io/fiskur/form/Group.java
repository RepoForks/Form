package io.fiskur.form;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable{
  public String id;
  public List<Field> fields;
}
