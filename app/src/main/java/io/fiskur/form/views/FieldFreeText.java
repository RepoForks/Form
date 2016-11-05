package io.fiskur.form.views;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.fiskur.form.Field;
import io.fiskur.form.FieldPrefs;
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
    if(field.text != null && !field.text.isEmpty()){
      freeTextTitle.setText(field.text);
      freeTextTitle.setVisibility(View.VISIBLE);
    }else{
      removeView(freeTextTitle);
    }
    config(field);
  }

  private void config(Field field){
    if(field.config != null && !field.config.isEmpty()){
      String config = field.config;
      String[] configs = config.split("\\|");
      for(String configOption : configs){
        if(configOption.toLowerCase().equals("numeric")){
          freeTextEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        }else if(configOption.toLowerCase().equals("singleline")){
          freeTextEdit.setMaxLines(1);
        }else if(configOption.toLowerCase().equals("persist")){
          persist(field);
        }
      }
    }
  }

  private void persist(final Field field){
    freeTextEdit.setText(FieldPrefs.getInstance().getText(field.id));
    freeTextEdit.setSelection(freeTextEdit.getText().length());
    freeTextEdit.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //unused
      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        FieldPrefs.getInstance().saveText(field.id, charSequence.toString());
      }

      @Override
      public void afterTextChanged(Editable editable) {
        //unused
      }
    });
  }

  public String getFieldText(){
    return freeTextEdit.getText().toString();
  }
}
