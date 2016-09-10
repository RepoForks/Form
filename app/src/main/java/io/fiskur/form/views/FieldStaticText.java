package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.fiskur.form.Field;
import io.fiskur.form.R;

public class FieldStaticText extends LinearLayout {

  private TextView staticTextTitle;
  private TextView staticTextSubtitle;
  private TextView staticTextBody;

  public FieldStaticText(Context context) {
    super(context);
    init();
  }

  public FieldStaticText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldStaticText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.field_static_text, this);
    staticTextTitle = (TextView) findViewById(R.id.static_title);
    staticTextSubtitle = (TextView) findViewById(R.id.static_subtitle);
    staticTextBody = (TextView) findViewById(R.id.static_body);
  }

  public void setField(Field field){
    if(field.title != null && !field.title.isEmpty()){
      staticTextTitle.setVisibility(View.VISIBLE);
      staticTextTitle.setText(field.title);
    }else{
      removeView(staticTextTitle);
    }
    if(field.subtitle != null && !field.subtitle.isEmpty()){
      staticTextSubtitle.setVisibility(View.VISIBLE);
      staticTextSubtitle.setText(field.subtitle);
    }else{
      removeView(staticTextSubtitle);
    }
    if(field.body != null && !field.body.isEmpty()){
      staticTextBody.setVisibility(View.VISIBLE);
      staticTextBody.setText(field.body);
    }else{
      removeView(staticTextBody);
    }
  }
}
