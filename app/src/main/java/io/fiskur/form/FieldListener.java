package io.fiskur.form;

public interface FieldListener {
  void choiceSelected(String fieldId, String choiceId);
  void choiceDeselected(String fieldId, String choiceId);
  void checkSelected(String fieldId, String choiceId);
  void checkDeselected(String fieldId, String choiceId);
}
