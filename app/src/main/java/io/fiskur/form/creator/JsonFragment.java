package io.fiskur.form.creator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.fiskur.form.R;

public class JsonFragment extends Fragment {
  private static final String TAG = "JsonFragment";
  private TextView jsonTextView;

  private Gson gson = null;

  public JsonFragment() {
    //
  }

  public void update(){
    Log.d(TAG, "update()");
    if(jsonTextView != null){
      if(gson == null) {
        gson = new GsonBuilder().setPrettyPrinting().create();
      }
      jsonTextView.setText(gson.toJson(Preview.form));
    }
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_json, container, false);
    jsonTextView = (TextView)view.findViewById(R.id.json_text_view);
    update();
    return view;
  }

}
