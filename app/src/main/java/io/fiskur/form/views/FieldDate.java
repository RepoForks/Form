package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.fiskur.form.Field;
import io.fiskur.form.R;

public class FieldDate extends LinearLayout {

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
    datePicker = (DatePicker) findViewById(R.id.field_date_picker);
  }

  public void setField(Field field){

  }
}

