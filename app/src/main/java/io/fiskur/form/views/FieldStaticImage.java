package io.fiskur.form.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import io.fiskur.form.Field;
import io.fiskur.form.R;

public class FieldStaticImage extends LinearLayout {

  private ImageView staticImage;

  public FieldStaticImage(Context context) {
    super(context);
    init();
  }

  public FieldStaticImage(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public FieldStaticImage(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init(){
    inflate(getContext(), R.layout.field_static_text, this);
    staticImage = (ImageView) findViewById(R.id.static_image);
  }

  public void setField(Field field, ImageLoader loader){
    if(loader != null){
      loader.loadImage(staticImage, field.id);
    }
  }

  public interface ImageLoader{
    void loadImage(ImageView imageView, String url);
  }
}
