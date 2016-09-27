package io.fiskur.form;

import android.view.View;
import android.widget.LinearLayout;

public class FormUIGraph implements FieldListener {

  private static final String TAG = "FormUIGraph";

  private Form form = null;
  private LinearLayout root = null;

  public FormUIGraph() {
  }

  public void setForm(Form form){
    this.form = form;
  }

  public void setRootLayout(LinearLayout root){
    this.root = root;
  }

  //Single choice/RadioButton Group toggled:
  @Override
  public void choiceSelected(String fieldId, String choiceId) {
    Field field = findField(fieldId);
    if(field == null){
      return;
    }

    toggleVisibilities(field, choiceId, true);
  }

  //Checkboxes:
  @Override
  public void choiceDeselected(String fieldId, String choiceId) {
    //not needed - as we have a mandatory single choice subfields visibility will be handled when alternative is selected
  }

  @Override
  public void checkSelected(String fieldId, String choiceId) {
    Field field = findField(fieldId);
    if(field == null){
      return;
    }

    toggleVisibilities(field, choiceId, true);
  }

  @Override
  public void checkDeselected(String fieldId, String choiceId) {
    Field field = findField(fieldId);
    if(field == null){
      return;
    }

    toggleVisibilities(field, choiceId, false);
  }

  //todo - could be nested choices (which isn't supported here yet), this is nested fields:
  private void toggleVisibilities(Field field, String choiceId, boolean choiceSelected){
    if(field.hasSubfields()) {
      int fieldCount = root.getChildCount();
      for (Field subfield : field.subfields) {
        if (subfield.parent != null && subfield.parent.equals(choiceId)) {
          //show subfield
          for (int i = 0; i < fieldCount; i++) {
            View view = root.getChildAt(i);
            if (subfield.id.equals(view.getTag())) {
              view.setVisibility(View.VISIBLE);
            }
          }
        } else {
          //hide subfield
          for (int i = 0; i < fieldCount; i++) {
            View view = root.getChildAt(i);
            if (subfield.id.equals((String) view.getTag())) {
              view.setVisibility(View.GONE);
            }
          }
        }
      }
    }
  }

  private Field findField(String fieldId){
    Field theField = null;
    for(Field field : form.fields){
      if(field.id.equals(fieldId)){
        theField = field;
      }
    }
    return theField;
  }
}
