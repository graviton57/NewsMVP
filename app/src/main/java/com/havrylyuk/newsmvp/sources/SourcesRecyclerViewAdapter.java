package com.havrylyuk.newsmvp.sources;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.havrylyuk.newsmvp.R;
import com.havrylyuk.newsmvp.data.model.Source;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class SourcesRecyclerViewAdapter extends
        RecyclerView.Adapter<SourcesRecyclerViewAdapter.ItemHolder> {

    public interface ItemClickListener {
        void onItemClick(Source source);
    }

    private ItemClickListener listener;
    private List<Source> sourceList;

    public SourcesRecyclerViewAdapter(ItemClickListener listener, List<Source> sourceList) {
        this.listener = listener;
        this.sourceList = sourceList;
        notifyDataSetChanged();
    }

    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.source_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(sourceList.get(holder.getAdapterPosition()));
                }
            }
        });
        String name = sourceList.get(position).getName();
        String description = sourceList.get(position).getDescription();
        holder.name.setText(name);
        holder.description.setText(description);
        String imageUrl = sourceList.get(position).getUrlsToLogos().getMedium();
        if (!TextUtils.isEmpty(imageUrl)){
            if (!imageUrl.toLowerCase().startsWith("http")){
                imageUrl = "http:" + imageUrl;
            }
            holder.newsImage.setImageURI(Uri.parse(imageUrl));
        }
    }

    @Override
    public int getItemCount() {
        return sourceList != null ? sourceList.size() : 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private  View view;
        private  SimpleDraweeView newsImage;
        private  TextView description;
        private  TextView name;

        public ItemHolder(View view) {
            super(view);
            this.view = view;
            this.newsImage = (SimpleDraweeView) view.findViewById(R.id.list_item_icon);
            this.description = (TextView) view.findViewById(R.id.list_item_sub_name);
            this.name = (TextView) view.findViewById(R.id.list_item_name);
        }
    }
}
