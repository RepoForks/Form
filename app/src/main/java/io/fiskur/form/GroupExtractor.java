package io.fiskur.form;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.fiskur.form.views.FieldBinary;

public class GroupExtractor {

  public void extractResponses(LinearLayout root, String groupId, Form form){
    Group group = form.getGroup(groupId);

    for(Field field : group.fields){
      String tag = field.id;
      View view = getView(root, tag);
      switch(field.type){
        case Field.TYPE_BINARY_CHOICE:
          FieldBinary binaryField = (FieldBinary) view;
          field.response = binaryField.getReponse();
          break;

      }
    }
  }

  private View getView(ViewGroup viewGroup, String tag){
    int childCount = viewGroup.getChildCount();
    for(int i = 0 ; i < childCount ; i++){
      View view = viewGroup.getChildAt(i);
      if(view.getTag().equals(tag)){
        return view;
      }
    }
    return null;
  }
}
