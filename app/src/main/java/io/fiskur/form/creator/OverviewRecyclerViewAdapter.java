package io.fiskur.form.creator;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.fiskur.form.Field;
import io.fiskur.form.R;
import io.fiskur.form.dragdrop.ItemTouchHelperAdapter;
import io.fiskur.form.dragdrop.ItemTouchHelperViewHolder;

import java.util.Collections;
import java.util.List;

public class OverviewRecyclerViewAdapter extends RecyclerView.Adapter<OverviewRecyclerViewAdapter.ViewHolder> implements ItemTouchHelperAdapter {

  private List<Field> fields;
  private final OverviewFragment.OnListFragmentInteractionListener mListener;

  public OverviewRecyclerViewAdapter(List<Field> items, OverviewFragment.OnListFragmentInteractionListener listener) {
    fields = items;
    mListener = listener;
  }

  public void setItems(List<Field> fields){
    this.fields = fields;
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.creator_fragment_overview, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    holder.mItem = fields.get(position);
    holder.mIdView.setText(fields.get(position).id);
    holder.mContentView.setText(fields.get(position).toString());

    holder.mView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != mListener) {
          // Notify the active callbacks interface (the activity, if the
          // fragment is attached to one) that an item has been selected.
          mListener.onListFragmentInteraction(holder.mItem);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return fields.size();
  }

  @Override
  public boolean onItemMove(int fromPosition, int toPosition) {
    Collections.swap(fields, fromPosition, toPosition);
    notifyItemMoved(fromPosition, toPosition);
    return true;
  }

  @Override
  public void onItemDismiss(int position) {
    fields.remove(position);
    notifyItemRemoved(position);
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    public final View mView;
    public final TextView mIdView;
    public final TextView mContentView;
    public final LinearLayout layout;
    public Field mItem;

    public ViewHolder(View view) {
      super(view);
      mView = view;
      mIdView = (TextView) view.findViewById(R.id.id);
      mContentView = (TextView) view.findViewById(R.id.content);
      layout = (LinearLayout)view.findViewById(R.id.the_row);
    }

    @Override
    public String toString() {
      return super.toString() + " '" + mContentView.getText() + "'";
    }

    @Override
    public void onItemSelected() {
      layout.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
      layout.setBackgroundColor(0);
    }
  }
}
