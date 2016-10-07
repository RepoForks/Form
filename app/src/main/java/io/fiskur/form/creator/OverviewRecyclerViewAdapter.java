package io.fiskur.form.creator;

import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.fiskur.form.Field;
import io.fiskur.form.R;
import io.fiskur.form.dragdrop.ItemTouchHelperAdapter;
import io.fiskur.form.dragdrop.ItemTouchHelperViewHolder;
import io.fiskur.form.dragdrop.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OverviewRecyclerViewAdapter extends RecyclerView.Adapter<OverviewRecyclerViewAdapter.ViewHolder> implements ItemTouchHelperAdapter {

  private List<Field> fields;
  private final OverviewFragment.OnListFragmentInteractionListener interactionListener;
  private final OnStartDragListener startDragListener;

  public OverviewRecyclerViewAdapter(OverviewFragment.OnListFragmentInteractionListener listener, OnStartDragListener dragStartListener) {
    fields = new ArrayList<>();
    interactionListener = listener;
    startDragListener = dragStartListener;
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
    holder.field = fields.get(position);
    holder.contentView.setText(fields.get(position).getPreviewLabel());

    holder.view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != interactionListener) {
          interactionListener.onListFragmentInteraction(holder.field);
        }
      }
    });

    // Start a drag whenever the handle view it touched
    holder.handleView.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
          startDragListener.onStartDrag(holder);
        }
        return false;
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
    public final View view;
    public final TextView contentView;
    public final RelativeLayout layout;
    public Field field;
    public final ImageView handleView;

    public ViewHolder(View view) {
      super(view);
      this.view = view;
      contentView = (TextView) view.findViewById(R.id.content);
      layout = (RelativeLayout)view.findViewById(R.id.the_row);
      handleView = (ImageView) itemView.findViewById(R.id.handle);
    }

    @Override
    public String toString() {
      return super.toString() + " '" + contentView.getText() + "'";
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
