package io.fiskur.form;

import android.content.Context;
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
import io.fiskur.form.views.FieldImage;
import io.fiskur.form.views.FieldMutipleChoice;
import io.fiskur.form.views.FieldPopup;
import io.fiskur.form.views.FieldSingleChoice;
import io.fiskur.form.views.FieldSpacer;
import io.fiskur.form.views.FieldStaticText;
import io.fiskur.form.views.FieldSubmit;
import io.fiskur.form.views.FieldTime;
import io.fiskur.form.views.SubgroupPopup;

public class FormApi implements SubmitListener, FieldListener {

  private static final String TAG = "FormApi";
  private static final FormApi instance = new FormApi();

  private Form form = null;
  private LinearLayout root;

  public FormApi() {
  }

  public Form getForm(){
    return form;
  }

  public String getFormTitle(){
    return form.title;
  }

  public static FormApi getInstance() {
    return instance;
  }

  private ImageLoader imageLoader = null;

  public void createForm(String jsonForm){
    Gson gson = new GsonBuilder().create();
    form =  gson.fromJson(jsonForm, Form.class);
  }

  public void setImageLoader(ImageLoader imageLoader){
    this.imageLoader = imageLoader;
  }

  public void buildViews(Context context, LinearLayout root){
    this.root = root;
    if(root == null){
      Log.e(TAG, "Root LinearLayout is null");
      return;
    }

    //init prefs class
    FieldPrefs.getInstance().init(context.getSharedPreferences(FieldPrefs.PREFS_KEY, Context.MODE_PRIVATE));

    root.setOrientation(LinearLayout.VERTICAL);

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

    if(group == null){
      Log.e(TAG, "Group is null - a choice subgroup may not be populated");
      return;
    }

    holder.setOrientation(LinearLayout.VERTICAL);

    for(Field field : group.fields){
      addField(context, field, holder, true);
    }
  }

  private static final long FADE_TIME = 750;

  private void addField(Context context, Field field, LinearLayout root, boolean isSubfield){
    l("Adding field of type: " + field.type);
    switch(field.type.toUpperCase()){
      case Field.TYPE_DIVIDER:
        FieldDivider div = new FieldDivider(context);
        div.setTag(field.id);
        root.addView(div);
        if(isSubfield){
          div.setAlpha(0);
          ViewTools.show(div, FADE_TIME);
        }
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
        root.addView(spacer);
        if(isSubfield){
          spacer.setAlpha(0);
          ViewTools.show(spacer, FADE_TIME);
        }
        break;
      case Field.TYPE_STATIC_TEXT:
        FieldStaticText staticText = new FieldStaticText(context);
        staticText.setTag(field.id);
        staticText.setField(field);
        root.addView(staticText);
        if(isSubfield){
          staticText.setAlpha(0);
          ViewTools.show(staticText, FADE_TIME);
        }
        break;
      case Field.TYPE_FREE_TEXT:
        FieldFreeText freeText = new FieldFreeText(context);
        freeText.setTag(field.id);
        freeText.setField(field);
        root.addView(freeText);
        if(isSubfield){
          freeText.setAlpha(0);
          ViewTools.show(freeText, FADE_TIME);
        }
        break;
      case Field.TYPE_CURRENT_DATE:
        FieldCurrentDate currentDate = new FieldCurrentDate(context);
        currentDate.setTag(field.id);
        currentDate.setField(field);
        root.addView(currentDate);
        if(isSubfield){
          currentDate.setAlpha(0);
          ViewTools.show(currentDate, FADE_TIME);
        }
        break;
      case Field.TYPE_DATE:
        FieldDate date = new FieldDate(context);
        date.setTag(field.id);
        date.setField(field);
        root.addView(date);
        if(isSubfield){
          date.setAlpha(0);
          ViewTools.show(date, FADE_TIME);
        }
        break;
      case Field.TYPE_TIME:
        FieldTime time = new FieldTime(context);
        time.setTag(field.id);
        time.setField(field);
        root.addView(time);
        if(isSubfield){
          time.setAlpha(0);
          ViewTools.show(time, FADE_TIME);
        }
        break;
      case Field.TYPE_BINARY_CHOICE:
        FieldBinary binary = new FieldBinary(context);
        binary.setTag(field.id);
        binary.setField(field);
        if(field.hasSubfields()) {
          binary.setFieldListener(this);
        }
        root.addView(binary);
        if(isSubfield){
          binary.setAlpha(0);
          ViewTools.show(binary, FADE_TIME);
        }
        break;
      case Field.TYPE_SINGLE_CHOICE:
        FieldSingleChoice singleChoice = new FieldSingleChoice(context);
        singleChoice.setTag(field.id);
        singleChoice.setField(context, field);
        if(field.hasSubfields()) {
          singleChoice.setFieldListener(this);
        }
        root.addView(singleChoice);
        if(isSubfield){
          singleChoice.setAlpha(0);
          ViewTools.show(singleChoice, FADE_TIME);
        }
        break;
      case Field.TYPE_MULTI_CHOICE:
        FieldMutipleChoice multiChoice = new FieldMutipleChoice(context);
        multiChoice.setTag(field.id);
        multiChoice.setField(context, field);
        if(field.hasSubfields()) {
          multiChoice.setFieldListener(this);
        }
        root.addView(multiChoice);
        if(isSubfield){
          multiChoice.setAlpha(0);
          ViewTools.show(multiChoice, FADE_TIME);
        }
        break;
      case Field.TYPE_IMAGE:
        FieldImage imageField = new FieldImage(context);
        imageField.setTag(field.id);
        imageField.setImageLoader(imageLoader);
        imageField.setField(field);
        root.addView(imageField);
        if(isSubfield){
          imageField.setAlpha(0);
          ViewTools.show(imageField, FADE_TIME);
        }
        break;
      case Field.TYPE_POPUP_SUBGROUP:
        //todo - add button and label - set callback to graph.
        FieldPopup popupField = new FieldPopup(context);
        popupField.setTag(field.id);
        popupField.setFieldListener(this);
        popupField.setField(field);
        root.addView(popupField);
        if(isSubfield){
          popupField.setAlpha(0);
          ViewTools.show(popupField, FADE_TIME);
        }
        break;
      case Field.TYPE_SUBMIT:
        FieldSubmit submitField = new FieldSubmit(context);
        submitField.setTag(field.id);
        submitField.setSubmitListener(this);
        root.addView(submitField);
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

  @Override
  public void submitForm() {

  }

  @Override
  public void choiceSelected(Context context, String fieldId, String choiceId, String subgroupId) {
    Field field = findField(fieldId);
    if(field == null){
      return;
    }

    buildSubgroup(context, fieldId, subgroupId);
  }

  @Override
  public void subgroupPopup(Context context, final Field parentField, String subgroupId) {
    Group group = form.getGroup(subgroupId);
    SubgroupPopup popup = new SubgroupPopup(context, parentField, group, new SubgroupPopup.GroupPopupListener() {
      @Override
      public void addGroup(Group group) {
        Log.d(TAG, "addGroup: " + group.id);
        form.addGroup(parentField.id, group);
        FieldPopup parent = (FieldPopup) ViewTools.findView(root, parentField.id);
        parent.updateRows(form.findField(parentField.id));
      }
    });
    popup.show();
  }

  private void buildSubgroup(Context context, String fieldId, String subgroupId){
    String holderTag = String.format("%s_holder", fieldId);
    LinearLayout holder = (LinearLayout) root.findViewWithTag(holderTag);
    if(holder == null){
      Log.e(TAG, "Could not find subgroup holder");
      return;
    }
    holder.removeAllViews();

    Group group = form.getGroup(subgroupId);
    buildGroup(context, group, holder);
  }

  private Field findField(String fieldId){
    return form.findField(fieldId);
  }
}
