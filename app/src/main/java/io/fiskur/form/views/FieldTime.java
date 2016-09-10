package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import io.fiskur.form.Field;
import io.fiskur.form.R;

public class FieldTime extends LinearLayout{

  private TextView timeTitle;
  private TimePicker timePicker;

  public FieldTime(Context context) {
    super(context);
    init();
  }

  public FieldTime(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldTime(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.field_time, this);
    timeTitle = (TextView) findViewById(R.id.time_title);
    timePicker = (TimePicker) findViewById(R.id.field_time_picker);
  }

  public void setField(Field field){
    if(field.text != null && !field.text.isEmpty()){
      timeTitle.setText(field.text);
      timeTitle.setVisibility(View.VISIBLE);
    }else{
      removeView(timeTitle);
    }
  }
}
