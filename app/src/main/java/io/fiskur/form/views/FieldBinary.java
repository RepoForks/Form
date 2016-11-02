package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.fiskur.form.Field;
import io.fiskur.form.FieldListener;
import io.fiskur.form.R;

//To support Yes/No fields with buttons instead of a radio group
public class FieldBinary extends LinearLayout implements View.OnClickListener {

  private TextView label;
  private Button buttonA;
  private Button buttonB;

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
    buttonA = (Button)findViewById(R.id.binary_button_a);
    buttonB = (Button)findViewById(R.id.binary_button_b);
  }

  public void setField(Context context, Field field){
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

      buttonA.setOnClickListener(this);
      buttonB.setOnClickListener(this);
    }
  }

  public void setFieldListener(FieldListener fieldListener){
    this.fieldListener = fieldListener;
  }

  @Override
  public void onClick(View view) {
    if(fieldListener != null){
        fieldListener.choiceSelected((String)FieldBinary.this.getTag(), (String)buttonA.getTag());
    }
  }
}
