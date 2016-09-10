package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.fiskur.form.Field;
import io.fiskur.form.R;

public class FieldFreeText extends LinearLayout {

  private TextView freeTextTitle;
  private EditText freeTextEdit;

  public FieldFreeText(Context context) {
    super(context);
    init();
  }

  public FieldFreeText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldFreeText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.field_free_text, this);
    freeTextTitle = (TextView) findViewById(R.id.free_text_title);
    freeTextEdit = (EditText) findViewById(R.id.free_text_edit);
  }

  public void setField(Field field){
    if(field.title != null && !field.title.isEmpty()){
      freeTextTitle.setText(field.title);
      freeTextTitle.setVisibility(View.VISIBLE);
    }else{
      removeView(freeTextTitle);
    }
  }
}
