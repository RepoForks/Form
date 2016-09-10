package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.fiskur.form.Field;
import io.fiskur.form.R;

public class FieldDate extends LinearLayout {

  private TextView dateTitle;
  private DatePicker datePicker;

  public FieldDate(Context context) {
    super(context);
    init();
  }

  public FieldDate(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldDate(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.field_date, this);
    dateTitle = (TextView) findViewById(R.id.date_title);
    datePicker = (DatePicker) findViewById(R.id.field_date_picker);
  }

  public void setField(Field field){
    if(field.text != null && !field.text.isEmpty()){
      dateTitle.setText(field.text);
      dateTitle.setVisibility(View.VISIBLE);
    }else{
      removeView(dateTitle);
    }
  }
}

