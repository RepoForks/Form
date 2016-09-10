package io.fiskur.form;

public class ValidationResponse {
  public static final int FORM_OK = 0;
  public static final int FORM_DUPLICATE_ID = 1;
  public static final int FORM_NULL = 2;
  public static final int FORM_NO_FIELDS = 3;

  public int status;

  public String[] duplicates = null;
}
