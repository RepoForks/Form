package io.fiskur.form;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.fiskur.form.views.FieldBinary;
import io.fiskur.form.views.FieldCurrentDate;
import io.fiskur.form.views.FieldDate;
import io.fiskur.form.views.FieldDivider;
import io.fiskur.form.views.FieldFreeText;
import io.fiskur.form.views.FieldMutipleChoice;
import io.fiskur.form.views.FieldSingleChoice;
import io.fiskur.form.views.FieldSpacer;
import io.fiskur.form.views.FieldStaticText;
import io.fiskur.form.views.FieldTime;

public class FormApi {

  private static final String TAG = "FormApi";

  private static final FormApi instance = new FormApi();

  private FormUIGraph formUIGraph;

  public FormApi() {
    formUIGraph = new FormUIGraph();
  }

  public static FormApi getInstance() {
    return instance;
  }

  public Form createForm(String jsonForm){
    Gson gson = new GsonBuilder().create();
    return gson.fromJson(jsonForm, Form.class);
  }

  public void buildViews(Context context, Form form, LinearLayout root){
    if(root == null){
      Log.e(TAG, "Root LinearLayout is null");
      return;
    }

    root.setOrientation(LinearLayout.VERTICAL);

    formUIGraph.setForm(form);
    formUIGraph.setRootLayout(root);

    String startGroupId = form.startGroupId;


    for(Field field : form.getGroupFields(startGroupId)){
      addField(context, field, root, false);
    }
  }


  public void buildGroup(Context context, Group group, LinearLayout holder){

    if(holder == null){
      Log.e(TAG, "Root LinearLayout is null");
      return;
    }

    holder.setOrientation(LinearLayout.VERTICAL);

    for(Field field : group.fields){
      addField(context, field, holder, false);
    }
  }

  private void addField(Context context, Field field, LinearLayout root, boolean isSubfield){
    l("Adding field of type: " + field.type);
    switch(field.type){
      case Field.TYPE_DIVIDER:
        FieldDivider div = new FieldDivider(context);
        div.setTag(field.id);
        if(isSubfield){
          div.setVisibility(View.GONE);
        }
        root.addView(div);
        break;
      case Field.TYPE_SPACER:
        int height = 20;
        try {
          height = Integer.parseInt(field.config);
        }catch(NumberFormatException nfe){
          l("NumberFormatException spacer config: " + nfe);
        }
        FieldSpacer spacer = new FieldSpacer(context, height);
        spacer.setTag(field.id);
        if(isSubfield){
          spacer.setVisibility(View.GONE);
        }
        root.addView(spacer);
        break;
      case Field.TYPE_STATIC_TEXT:
        FieldStaticText staticText = new FieldStaticText(context);
        staticText.setTag(field.id);
        staticText.setField(field);
        if(isSubfield){
          staticText.setVisibility(View.GONE);
        }
        root.addView(staticText);
        break;
      case Field.TYPE_FREE_TEXT:
        FieldFreeText freeText = new FieldFreeText(context);
        freeText.setTag(field.id);
        freeText.setField(field);
        if(isSubfield){
          freeText.setVisibility(View.GONE);
        }
        root.addView(freeText);
        break;
      case Field.TYPE_CURRENT_DATE:
        FieldCurrentDate currentDate = new FieldCurrentDate(context);
        currentDate.setTag(field.id);
        currentDate.setField(field);
        if(isSubfield){
          currentDate.setVisibility(View.GONE);
        }
        root.addView(currentDate);
        break;
      case Field.TYPE_DATE:
        FieldDate date = new FieldDate(context);
        date.setTag(field.id);
        date.setField(field);
        if(isSubfield){
          date.setVisibility(View.GONE);
        }
        root.addView(date);
        break;
      case Field.TYPE_TIME:
        FieldTime time = new FieldTime(context);
        time.setTag(field.id);
        time.setField(field);
        if(isSubfield){
          time.setVisibility(View.GONE);
        }
        root.addView(time);
        break;
      case Field.TYPE_BINARY_CHOICE:
        FieldBinary binary = new FieldBinary(context);
        binary.setTag(field.id);
        binary.setField(field);
        if(field.hasSubfields()) {
          binary.setFieldListener(formUIGraph);
        }
        if(isSubfield){
          binary.setVisibility(View.GONE);
        }
        root.addView(binary);
        break;
      case Field.TYPE_SINGLE_CHOICE:
        FieldSingleChoice singleChoice = new FieldSingleChoice(context);
        singleChoice.setTag(field.id);
        singleChoice.setField(context, field);
        if(field.hasSubfields()) {
          singleChoice.setFieldListener(formUIGraph);
        }
        if(isSubfield){
          singleChoice.setVisibility(View.GONE);
        }
        root.addView(singleChoice);
        break;
      case Field.TYPE_MULTI_CHOICE:
        FieldMutipleChoice multiChoice = new FieldMutipleChoice(context);
        multiChoice.setTag(field.id);
        multiChoice.setField(context, field);
        if(field.hasSubfields()) {
          multiChoice.setFieldListener(formUIGraph);
        }
        if(isSubfield){
          multiChoice.setVisibility(View.GONE);
        }
        root.addView(multiChoice);
        break;
    }

    if(field.hasSubfields()){
      LinearLayout subfieldHolder = new LinearLayout(context);
      subfieldHolder.setOrientation(LinearLayout.VERTICAL);
      subfieldHolder.setTag(String.format("%s_holder", field.id));

      LinearLayout.LayoutParams subfieldHolderLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
      subfieldHolder.setLayoutParams(subfieldHolderLayoutParams);
      root.addView(subfieldHolder);
    }
  }

  //todo... work in progress
  public void getResponses(Context context, Form form, LinearLayout root){
    final int childcount = root.getChildCount();
    for (int i = 0; i < childcount; i++) {
      View view = root.getChildAt(i);
      if(view instanceof FieldFreeText){
        FieldFreeText freeTextField = (FieldFreeText) view;
        //todo - get datafrom field view
      }else if(view instanceof  FieldCurrentDate){
        FieldCurrentDate currentDate = (FieldCurrentDate) view;
        //todo - get date from field view
      }
    }
  }

  private void l(String message){
    Log.d(TAG, message);
  }
}
