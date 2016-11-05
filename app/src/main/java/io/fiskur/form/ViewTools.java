package io.fiskur.form;

import android.animation.Animator;
import android.view.View;

public class ViewTools {

  public static void show(final View view, long time){
    view.setVisibility(View.VISIBLE);
    view.animate().alpha(1).setDuration(time).setListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {

      }

      @Override
      public void onAnimationEnd(Animator animation) {
        view.setVisibility(View.VISIBLE);
        view.requestFocus();
      }

      @Override
      public void onAnimationCancel(Animator animation) {
        view.setVisibility(View.VISIBLE);
        view.requestFocus();
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
