package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import io.fiskur.form.Choice;
import io.fiskur.form.Field;
import io.fiskur.form.FieldListener;
import io.fiskur.form.R;

public class FieldSingleChoice extends LinearLayout{

  private TextView singleChoiceBody;
  private RadioGroup radioGroup;

  private FieldListener fieldListener = null;

  public FieldSingleChoice(Context context) {
    super(context);
    init();
  }

  public FieldSingleChoice(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldSingleChoice(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.field_single_choice, this);
    singleChoiceBody = (TextView) findViewById(R.id.single_choice_text);
    radioGroup = (RadioGroup) findViewById(R.id.field_single_choice_group);
  }

  public void setFieldListener(FieldListener fieldListener){
    this.fieldListener = fieldListener;
  }

  public void setField(Context context, Field field){
    if(field.text != null && !field.text.isEmpty()){
      singleChoiceBody.setVisibility(View.VISIBLE);
      singleChoiceBody.setText(field.text);
    }else{
      removeView(singleChoiceBody);
    }
    if(field.choices != null && field.choices.size() > 0){
      for(Choice choice : field.choices){
        RadioButton choiceButton = new RadioButton(context);
        choiceButton.setText(choice.text);
        choiceButton.setTag(choice.id);
        radioGroup.addView(choiceButton);
      }
    }

    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radio = (RadioButton)group.findViewById(checkedId);
         if(fieldListener != null){
          if(radio.isChecked()){
            fieldListener.choiceSelected((String)FieldSingleChoice.this.getTag(), (String)radio.getTag());
          }else{
            fieldListener.choiceDeselected((String)FieldSingleChoice.this.getTag(), (String)radio.getTag());
          }
        }
      }
    });
  }

  public String getFieldSelectedChoice(){
    int radioButtonID = radioGroup.getCheckedRadioButtonId();
    View radioButton = radioGroup.findViewById(radioButtonID);
    if(radioButton != null){
      return (String)radioButton.getTag();
    }else{
      return null;
    }
  }
}
