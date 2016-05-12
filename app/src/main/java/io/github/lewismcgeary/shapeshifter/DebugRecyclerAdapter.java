package io.github.lewismcgeary.shapeshifter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Lewis on 12/05/2016.
 */
public class DebugRecyclerAdapter extends RecyclerView.Adapter<DebugRecyclerAdapter.ViewHolder> {

    private int[] debugShapeList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView;
        }
    }

    public DebugRecyclerAdapter(int[] shapeList){
        debugShapeList = shapeList;
    }

    @Override
    public DebugRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_image_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.imageView.setImageResource(debugShapeList[position]);
    }

    @Override
    public int getItemCount() {
        return debugShapeList.length;
    }


}
