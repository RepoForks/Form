package io.fiskur.form.creator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.fiskur.form.Field;
import io.fiskur.form.R;
import io.fiskur.form.creator.dummy.DummyContent;
import io.fiskur.form.dragdrop.OnStartDragListener;
import io.fiskur.form.dragdrop.SimpleItemTouchHelperCallback;

public class OverviewFragment extends Fragment implements OnStartDragListener {

  private OnListFragmentInteractionListener mListener;
  private ItemTouchHelper itemTouchHelper;

  private OverviewRecyclerViewAdapter adapter = null;

  public OverviewFragment() {
    //
  }

  public static OverviewFragment newInstance() {
    return new OverviewFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_overview_list, container, false);

    Context context = view.getContext();
    RecyclerView recyclerView = (RecyclerView) view;
    recyclerView.setLayoutManager(new LinearLayoutManager(context));
    adapter = new OverviewRecyclerViewAdapter(DummyContent.ITEMS, mListener);
    adapter.setItems(Preview.form.fields);
    recyclerView.setAdapter(adapter);

    ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
    itemTouchHelper = new ItemTouchHelper(callback);
    itemTouchHelper.attachToRecyclerView(recyclerView);

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

  @Override
  public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
    itemTouchHelper.startDrag(viewHolder);
  }

  public interface OnListFragmentInteractionListener {
    // TODO: Update argument type and name
    void onListFragmentInteraction(Field item);
  }

  public void update(){
    if(adapter != null) {
      adapter.setItems(Preview.form.fields);
    }
  }
}
