package com.havrylyuk.newsmvp.news;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.havrylyuk.newsmvp.R;
import com.havrylyuk.newsmvp.data.model.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ItemHolder> {

    public interface ItemClickListener {
        void onItemClick(Article article);
    }
    private static final  SimpleDateFormat formatTo =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
    private static final  SimpleDateFormat formatFrom =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private ItemClickListener listener;
    private List<Article> articleList;

    public NewsRecyclerViewAdapter(ItemClickListener listener, List<Article> articleList) {
        this.listener = listener;
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    public void addArticles(List<Article> articleList) {
        this.articleList.addAll(articleList);
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(articleList.get(holder.getAdapterPosition()));
                }
            }
        });
        String title = articleList.get(position).getTitle();
        String author = articleList.get(position).getAuthor();
        String date = articleList.get(position).getPublishedAt();
        holder.author.setText(author);
        holder.title.setText(title);
        try {
            holder.date.setText(formatFrom.format(formatTo.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String imageUrl = articleList.get(position).getImageUrl();
        holder.newsImage.setImageURI(Uri.parse(imageUrl));
    }

    @Override
    public int getItemCount() {
        return articleList != null ? articleList.size() : 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private  View view;
        private  SimpleDraweeView newsImage;
        private  TextView author;
        private  TextView title;
        private  TextView date;

        public ItemHolder(View view) {
            super(view);
            this.view = view;
            this.newsImage = (SimpleDraweeView) view.findViewById(R.id.list_item_icon);
            this.title = (TextView) view.findViewById(R.id.list_item_name);
            this.author = (TextView) view.findViewById(R.id.list_item_sub_name);
            this.date = (TextView) view.findViewById(R.id.list_item_date);
        }
    }
}
