package io.fiskur.form;

import android.view.View;
import android.widget.LinearLayout;

public class FormUIGraph implements FieldListener {

  private static final String TAG = "FormUIGraph";

  private static final FormUIGraph instance = new FormUIGraph();

  private Form form = null;
  private LinearLayout root = null;

  protected FormUIGraph() {
  }

  public static FormUIGraph getInstance() {
    return instance;
  }

  public void setForm(Form form){
    this.form = form;
  }

  public void setRootLayout(LinearLayout root){
    this.root = root;
  }

  @Override
  public void choiceSelected(String fieldId, String choiceId) {
    Field field = findField(fieldId);
    if(field == null){
      return;
    }

    //todo - this whole block is massivly inefficient:
    if(field.hasSubfields()){
      int fieldCount = root.getChildCount();
      for(Field subfield : field.subfields){
        if(subfield.parent != null && subfield.parent.equals(choiceId)){
          //show subfield
          for(int i = 0 ; i < fieldCount ; i++){
            View view = root.getChildAt(i);
            if(subfield.id.equals(view.getTag())){
              view.setVisibility(View.VISIBLE);
            }
          }
        }else{
          //hide subfield
          for(int i = 0 ; i < fieldCount ; i++){
            View view = root.getChildAt(i);
            if(subfield.id.equals((String)view.getTag())){
              view.setVisibility(View.GONE);
            }
          }
        }

        //todo - could be nested choices, not nested fields
      }
    }
  }

  @Override
  public void choiceDeselected(String fieldId, String choiceId) {

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
