package io.fiskur.form.views;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.fiskur.form.Field;
import io.fiskur.form.R;

public class FieldCurrentDate extends LinearLayout {

  private TextView datelabel;

  public FieldCurrentDate(Context context) {
    super(context);
    init();
  }

  public FieldCurrentDate(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldCurrentDate(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.field_current_date, this);
    datelabel = (TextView) findViewById(R.id.current_date_label);
  }

  public void setField(Field field){
    long now = System.currentTimeMillis();
    String label = "<b>" + field.text + ":</b> " + SimpleDateFormat.getDateInstance().format(new Date(now));
    datelabel.setText(Html.fromHtml(label));
  }
}
