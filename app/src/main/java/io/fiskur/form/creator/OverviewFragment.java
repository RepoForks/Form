package io.fiskur.form.creator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.fiskur.form.Field;
import io.fiskur.form.Form;
import io.fiskur.form.R;
import io.fiskur.form.creator.dummy.DummyContent;

public class OverviewFragment extends Fragment {

  private OnListFragmentInteractionListener mListener;


  public OverviewFragment() {
    //
  }

  public static OverviewFragment newInstance() {
    return new OverviewFragment();
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_overview_list, container, false);

    // Set the adapter
    if (view instanceof RecyclerView) {
      Context context = view.getContext();
      RecyclerView recyclerView = (RecyclerView) view;
      recyclerView.setLayoutManager(new LinearLayoutManager(context));
      recyclerView.setAdapter(new OverviewRecyclerViewAdapter(DummyContent.ITEMS, mListener));
    }
    return view;
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnListFragmentInteractionListener) {
      mListener = (OnListFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  public interface OnListFragmentInteractionListener {
    // TODO: Update argument type and name
    void onListFragmentInteraction(Field item);
  }

  public void update(Form form){
    if(form.fields != null && form.fields.size() > 0){
      //todo
    }else{
      //todo
    }
  }
}
