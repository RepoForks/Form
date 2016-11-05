package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import io.fiskur.form.R;
import io.fiskur.form.SubmitListener;

public class FieldSubmit extends LinearLayout implements View.OnClickListener {

  private Button submitButton;
  private SubmitListener submitListener;

  public FieldSubmit(Context context) {
    super(context);
    init();
  }

  public FieldSubmit(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldSubmit(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.field_submit, this);
    submitButton = (Button) findViewById(R.id.field_submit_button);
    submitButton.setOnClickListener(this);
  }

  public void setSubmitListener(SubmitListener submitListener){
    this.submitListener = submitListener;
  }

  @Override
  public void onClick(View view) {
    if(submitListener != null){
      submitListener.submitForm();
    }
  }
}
