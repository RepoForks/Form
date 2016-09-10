package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.fiskur.form.R;

public class FieldDivider extends LinearLayout {
  public FieldDivider(Context context) {
    super(context);
    init();
  }

  public FieldDivider(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldDivider(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public void init(){
    inflate(getContext(), R.layout.field_divider, this);
  }
}
