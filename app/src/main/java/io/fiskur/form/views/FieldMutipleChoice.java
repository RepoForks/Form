package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.fiskur.form.Choice;
import io.fiskur.form.Field;
import io.fiskur.form.R;

public class FieldMutipleChoice extends LinearLayout {

  private TextView multiChoiceBody;
  private LinearLayout checkHolder;

  public FieldMutipleChoice(Context context) {
    super(context);
    init();
  }

  public FieldMutipleChoice(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldMutipleChoice(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.field_multi_choice, this);
    multiChoiceBody = (TextView) findViewById(R.id.multi_choice_text);
    checkHolder = (LinearLayout) findViewById(R.id.multi_choice_check_holder);
  }

  public void setField(Context context, Field field){
    if(field.text != null && !field.text.isEmpty()){
      multiChoiceBody.setVisibility(View.VISIBLE);
      multiChoiceBody.setText(field.text);
    }else{
      removeView(multiChoiceBody);
    }
    if(field.choices != null && field.choices.size() > 0){
      for(Choice choice : field.choices) {
        CheckBox check = new CheckBox(context);
        check.setTag(choice.id);
        check.setText(choice.text);
        checkHolder.addView(check);
      }
    }
  }
}
