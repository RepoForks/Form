package io.fiskur.form;

import android.content.Context;
import android.util.Log;
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
  public void choiceSelected(Context context, String fieldId, String choiceId, String subgroupId) {
    Field field = findField(fieldId);
    if(field == null){
      return;
    }

    //toggleVisibilities(field, choiceId, true);
    buildSubgroup(context, fieldId, subgroupId);
  }

  private void buildSubgroup(Context context, String fieldId, String subgroupId){
    String holderTag = String.format("%s_holder", fieldId);
    LinearLayout holder = (LinearLayout) root.findViewWithTag(holderTag);
    if(holder == null){
      Log.e(TAG, "Could not find subgroup holder");
      return;
    }
    holder.removeAllViews();

    Group group = form.getGroup(subgroupId);
    FormApi.getInstance().buildGroup(context, group, holder);

  }

  private void toggleVisibilities(Field field, String choiceId, boolean choiceSelected){
    /*
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
    */
  }

  private Field findField(String fieldId){
    return form.findField(fieldId);
  }
}
