package io.fiskur.form.creator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.fiskur.form.Form;
import io.fiskur.form.FormApi;
import io.fiskur.form.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewFragment extends Fragment {

  private LinearLayout formHolder;


  public PreviewFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_preview, container, false);

    formHolder = (LinearLayout) view.findViewById(R.id.form_holder);
    FormApi.getInstance().buildViews(getActivity(), formHolder);
    return view;
  }

  public void update(){
    FormApi.getInstance().buildViews(getActivity(), formHolder);
  }
}
