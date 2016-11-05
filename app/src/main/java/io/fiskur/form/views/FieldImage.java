package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.fiskur.form.Field;
import io.fiskur.form.ImageLoader;
import io.fiskur.form.R;

public class FieldImage extends LinearLayout{

  private ImageLoader imageLoader;
  private ImageView imageView;
  private TextView imageTitle;
  private Field field;

  public FieldImage(Context context) {
    super(context);
    init();
  }

  public FieldImage(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldImage(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public void setImageLoader(ImageLoader imageLoader){
    this.imageLoader = imageLoader;
  }

  private void init(){
    inflate(getContext(), R.layout.field_image, this);
    imageTitle = (TextView)findViewById(R.id.image_title);
    imageView = (ImageView) findViewById(R.id.image_view);
    tryImage();
  }

  public void setField(Field field){
    this.field = field;
    if(field.text != null && !field.text.isEmpty()){
      imageTitle.setText(field.text);
      imageTitle.setVisibility(View.VISIBLE);
    }else{
      removeView(imageTitle);
    }
    tryImage();
  }

  private void tryImage(){
    if(imageLoader != null && imageView != null && field != null){
      imageLoader.loadImage(imageView, field.url);
    }
  }
}
