package io.fiskur.form;

import java.io.Serializable;

public class Choice implements Serializable{
  public String id;
  public String text;
  public String parentId;
  public String subgroupId;
  public Response response;
}
