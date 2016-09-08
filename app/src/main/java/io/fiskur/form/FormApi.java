package io.fiskur.form;

import android.content.Context;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    }
    //recursively add subfields
    //todo - create mechanism to toggle subfield visibility based on responses
    if(field.hasSubfields()){
      for(Field subfield : field.subfields){
        addField(context, field, root);
      }
    }
  }
}
