package io.fiskur.form.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.fiskur.form.R;

public class FieldSpacer extends LinearLayout {

  private int height;

  public FieldSpacer(Context context, int height) {
    super(context);
    this.height = height;
    init();
  }

  public FieldSpacer(Context context, AttributeSet attrs) {
    super(context, attrs);
    height = 30;
    init();
  }

  public FieldSpacer(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    height = 30;
    init();
  }

  public void init(){
    inflate(getContext(), R.layout.field_spacer, this);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    float f = (float)height;
    setMeasuredDimension(getMeasuredWidth(), (int) dpFromPx(f));
  }

  public static float dpFromPx(final float px) {
    return px / Resources.getSystem().getDisplayMetrics().density;
  }
}
