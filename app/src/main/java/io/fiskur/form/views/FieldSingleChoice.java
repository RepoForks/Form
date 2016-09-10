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
import io.fiskur.form.R;

public class FieldSingleChoice extends LinearLayout{

  private TextView singleChoiceTitle;
  private TextView singleChoiceSubtitle;
  private TextView singleChoiceBody;
  private RadioGroup radioGroup;

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
    inflate(getContext(), R.layout.field_free_text, this);
    singleChoiceTitle = (TextView) findViewById(R.id.single_choice_title);
    singleChoiceSubtitle = (TextView) findViewById(R.id.single_choice_subtitle);
    singleChoiceBody = (TextView) findViewById(R.id.single_choice_body);
    radioGroup = (RadioGroup) findViewById(R.id.field_single_choice_group);
  }

  public void setField(Context context, Field field){
    if(field.title != null && !field.title.isEmpty()){
      singleChoiceTitle.setVisibility(View.VISIBLE);
      singleChoiceTitle.setText(field.title);
    }else{
      removeView(singleChoiceTitle);
    }
    if(field.subtitle != null && !field.subtitle.isEmpty()){
      singleChoiceSubtitle.setVisibility(View.VISIBLE);
      singleChoiceSubtitle.setText(field.subtitle);
    }else{
      removeView(singleChoiceSubtitle);
    }
    if(field.body != null && !field.body.isEmpty()){
      singleChoiceBody.setVisibility(View.VISIBLE);
      singleChoiceBody.setText(field.body);
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
  }
}
