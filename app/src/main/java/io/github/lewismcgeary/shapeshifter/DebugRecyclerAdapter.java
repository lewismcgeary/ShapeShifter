package io.github.lewismcgeary.shapeshifter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Lewis on 12/05/2016.
 */
public class DebugRecyclerAdapter extends RecyclerView.Adapter<DebugRecyclerAdapter.ViewHolder> {

    private VoiceInputResultsReceiver receiver;
    private String[] debugShapeList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView;
        }

        @Override
        public void onClick(View v) {
            if (v instanceof ImageView){
                String shape = v.getTag().toString();
                receiver.debugShapeSelected(shape);

            }
        }
    }

    public DebugRecyclerAdapter(VoiceInputResultsReceiver receiver, String[] shapeList){
        this.receiver = receiver;
        debugShapeList = shapeList;
    }

    @Override
    public DebugRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_image_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        vh.imageView.setOnClickListener(vh);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.imageView.getContext();
        String shape = debugShapeList[position];
        int resourceId = context.getResources().getIdentifier(shape, "drawable", context.getPackageName());
        holder.imageView.setTag(shape);
        holder.imageView.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return debugShapeList.length;
    }


}
