package io.fiskur.form.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import io.fiskur.form.Field;
import io.fiskur.form.FormApi;
import io.fiskur.form.Group;
import io.fiskur.form.R;

public class SubgroupPopup {
  private MaterialDialog dialog;
  private GroupPopupListener groupPopupListener;

  public SubgroupPopup(Context context, Field subgroupParentField, Group group, GroupPopupListener groupPopupListener){
    this.groupPopupListener = groupPopupListener;
    dialog = new MaterialDialog.Builder(context)
      .title("Add new")
      .customView(R.layout.dialog_popup, true)
      .positiveText("Add")
      .negativeText("Cancel")
      .onPositive(new MaterialDialog.SingleButtonCallback() {
        @Override
        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
          add();
        }
      })
      .build();

    LinearLayout root = (LinearLayout) dialog.getCustomView();

    FormApi.getInstance().buildGroup(context, group, root);
  }

  private void add(){
    LinearLayout root = (LinearLayout) dialog.getCustomView();
    //todo - call method that iterates over linearlayout and pull out values
    Group group = new Group();
    group.id = String.valueOf(System.currentTimeMillis());
    if(groupPopupListener != null){
      groupPopupListener.addGroup(group);
    }

  }

  public void show(){
    dialog.show();
  }

  public interface GroupPopupListener{
    void addGroup(Group group);
  }
}
