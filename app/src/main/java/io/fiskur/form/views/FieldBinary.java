package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import io.fiskur.form.Choice;
import io.fiskur.form.Field;
import io.fiskur.form.FieldListener;
import io.fiskur.form.R;

//To support Yes/No fields with buttons instead of a radio group
public class FieldBinary extends LinearLayout implements CompoundButton.OnCheckedChangeListener {
  private static final String TAG = "FieldBinary";

  private Field field;
  private TextView label;
  private ToggleButton buttonA;
  private ToggleButton buttonB;

  private FieldListener fieldListener = null;

  public FieldBinary(Context context) {
    super(context);
    init();
  }

  public FieldBinary(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldBinary(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public void init(){
    inflate(getContext(), R.layout.field_binary, this);
    label = (TextView) findViewById(R.id.label);
    buttonA = (ToggleButton)findViewById(R.id.binary_button_a);
    buttonB = (ToggleButton)findViewById(R.id.binary_button_b);
  }

  public void setField(Field field){
    this.field = field;
    if(field.text != null && !field.text.isEmpty()){
      label.setVisibility(View.VISIBLE);
      label.setText(field.text);
    }else{
      removeView(label);
    }
    if(field.choices != null && field.choices.size() > 0){
      buttonA.setTag(field.choices.get(0).id);
      buttonA.setText(field.choices.get(0).text);

      buttonB.setTag(field.choices.get(1).id);
      buttonB.setText(field.choices.get(1).text);

      buttonA.setOnCheckedChangeListener(this);
      buttonB.setOnCheckedChangeListener(this);
    }
  }

  public void setFieldListener(FieldListener fieldListener){
    this.fieldListener = fieldListener;
  }

  @Override
  public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
    if(fieldListener != null){
      String choiceTag = (String)compoundButton.getTag();
      Choice selectedChoice = null;
      for(Choice choice : field.choices){
        if(choice.id == choiceTag){
          selectedChoice = choice;
          break;
        }
      }
      if(selectedChoice != null){
        fieldListener.choiceSelected(getContext(), (String)FieldBinary.this.getTag(), choiceTag, selectedChoice.subgroupId);
      }else{
        Log.e(TAG, "Could not find subgroupID");
      }
     }
  }
}
