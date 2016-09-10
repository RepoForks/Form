package io.fiskur.form;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.fiskur.form.views.FieldCurrentDate;
import io.fiskur.form.views.FieldFreeText;
import io.fiskur.form.views.FieldStaticText;

public class FormApi {

  private static final FormApi instance = new FormApi();

  protected FormApi() {
  }

  public static FormApi getInstance() {
    return instance;
  }

  public Form createForm(String jsonForm){
    Gson gson = new GsonBuilder().create();
    Form form = gson.fromJson(jsonForm, Form.class);
    return form;
  }

  public void buildViews(Context context, Form form, LinearLayout root){

    root.setOrientation(LinearLayout.VERTICAL);

    for(Field field : form.fields){
      addField(context, field, root);
    }
  }

  private void addField(Context context, Field field, LinearLayout root){
    switch(field.type){
      case Field.TYPE_STATIC_TEXT:
        FieldStaticText staticText = new FieldStaticText(context);
        staticText.setTag(field.id);
        staticText.setField(field);
        root.addView(staticText);
        break;
      case Field.TYPE_FREE_TEXT:
        FieldFreeText freeText = new FieldFreeText(context);
        freeText.setTag(field.id);
        freeText.setField(field);
        root.addView(freeText);
        break;
      case Field.TYPE_CURRENT_DATE:
        FieldCurrentDate currentDate = new FieldCurrentDate(context);
        currentDate.setTag(field.id);
        currentDate.setField(field);
        root.addView(currentDate);
        break;
    }
    //recursively add subfields
    //todo - create mechanism to toggle subfield visibility based on responses
    if(field.hasSubfields()){
      for(Field subfield : field.subfields){
        addField(context, field, root);
      }
    }
  }

  public void getResponses(Context context, Form form, LinearLayout root){
    final int childcount = root.getChildCount();
    for (int i = 0; i < childcount; i++) {
      View view = root.getChildAt(i);
      if(view instanceof FieldStaticText){
        //ignore - no user input
      }else if(view instanceof FieldFreeText){
        FieldFreeText freeTextField = (FieldFreeText) view;
        //todo - get datafrom field view
      }else if(view instanceof  FieldCurrentDate){
        FieldCurrentDate currentDate = (FieldCurrentDate) view;
        //todo - get date from field view
      }

    }
  }
}
