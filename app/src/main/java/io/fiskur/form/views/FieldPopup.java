package io.fiskur.form.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.fiskur.form.Field;
import io.fiskur.form.FieldListener;
import io.fiskur.form.R;

public class FieldPopup extends LinearLayout {

  private static final String TAG = "FieldPopup";
  private Field field;
  private TextView title;
  private RecyclerView recycler;
  private Button addNewButton;

  private SubgroupAdapter adapter;

  private String subgroupId;
  private FieldListener fieldListener;

  public FieldPopup(Context context) {
    super(context);
    init();
  }

  public FieldPopup(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldPopup(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.field_popup, this);
    title = (TextView)findViewById(R.id.popup_title);
    adapter = new SubgroupAdapter();
    recycler = (RecyclerView) findViewById(R.id.popup_recycler);
    recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    recycler.setAdapter(adapter);
    addNewButton = (Button) findViewById(R.id.popup_add_new_button);
  }

  public void updateRows(Field field){
    Log.d(TAG, "Field has " + field.groups.size() + " rows to display");
    adapter.update(field.groups);
  }

  public void setFieldListener(FieldListener fieldListener){
    this.fieldListener = fieldListener;
  }

  public void setField(final Field field){
    this.field = field;
    if(field.title != null && !field.title.isEmpty()){
      title.setText(field.title);
      title.setVisibility(View.VISIBLE);
    }else{
      removeView(title);
    }
    addNewButton.setText(field.text);

    if(field.subgroupId != null && !field.subgroupId.isEmpty()){
      this.subgroupId = field.subgroupId;
      addNewButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          fieldListener.subgroupPopup(getContext(), field, field.subgroupId);
        }
      });
    }
  }
}
