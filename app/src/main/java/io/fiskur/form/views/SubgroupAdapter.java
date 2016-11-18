package io.fiskur.form.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.fiskur.form.Group;
import io.fiskur.form.R;

public class SubgroupAdapter extends RecyclerView.Adapter<SubgroupAdapter.ViewHolder> {

  List<Group> groups = new ArrayList<>();

  public SubgroupAdapter(){

  }

  public void update(List<Group> groups){
    this.groups = groups;
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_group, parent, false);
    ViewHolder vh = new ViewHolder(view);
    return vh;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.textView.setText(String.format("Item %d", position));
  }

  @Override
  public int getItemCount() {
    return groups.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public ViewHolder(View view) {
      super(view);
      textView = (TextView) view.findViewById(R.id.group_label);
    }
  }
}
