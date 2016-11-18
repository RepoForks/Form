package io.fiskur.form;

import android.animation.Animator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.fiskur.form.views.FieldBinary;
import io.fiskur.form.views.FieldCurrentDate;
import io.fiskur.form.views.FieldDate;
import io.fiskur.form.views.FieldDivider;
import io.fiskur.form.views.FieldFreeText;
import io.fiskur.form.views.FieldImage;
import io.fiskur.form.views.FieldMutipleChoice;
import io.fiskur.form.views.FieldPopup;
import io.fiskur.form.views.FieldSingleChoice;
import io.fiskur.form.views.FieldSpacer;
import io.fiskur.form.views.FieldStaticText;
import io.fiskur.form.views.FieldSubmit;
import io.fiskur.form.views.FieldTime;

public class ViewTools {

  public static View findView(ViewGroup viewGroup, String tag){
    int childCount = viewGroup.getChildCount();
    for(int i = 0 ; i < childCount ; i++){
      View view = viewGroup.getChildAt(i);
      String viewTag = (String) view.getTag();
      if(viewTag != null && viewTag.equals(tag)){
        return view;
      }else if(view instanceof ViewGroup){
        View subView = findView((ViewGroup)view, tag);
        if(subView != null){
          return subView;
        }
      }
    }
    return null;
  }

  public static void show(final View view, long time){
    view.setVisibility(View.VISIBLE);
    view.animate().alpha(1).setDuration(time).setListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {

      }

      @Override
      public void onAnimationEnd(Animator animation) {
        view.setVisibility(View.VISIBLE);
        //view.requestFocus();
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        view.setVisibility(View.VISIBLE);
        //view.requestFocus();
      }

      @Override
      public void onAnimationRepeat(Animator animation) {

      }
    }).start();
  }

  public static void hide(final View view, long time){
    view.animate().alpha(0).setDuration(time).setListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
        //unused
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        view.setVisibility(View.GONE);
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        view.setVisibility(View.GONE);
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
        //unused
      }
    }).start();
  }
}
