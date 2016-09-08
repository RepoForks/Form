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
  public List<Field> fields;
}
