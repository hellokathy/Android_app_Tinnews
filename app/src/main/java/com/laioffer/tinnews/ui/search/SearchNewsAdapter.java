package com.laioffer.tinnews.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.laioffer.tinnews.R;


import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {
    interface ItemCallback {
        void onOpenDetails(Article article);
    }

    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

    // 1. Supporting data:
    private List<Article> articles = new ArrayList<>();

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();// data refresh
    }

    // 2. Adapter overrides:

    @NonNull
    @Override //7个view被创建的时候
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp);
        holder.itemTitleTextView.setText(article.title);
        Picasso.get().load(article.urlToImage).into(holder.itemImageView);
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));

    }

    @Override
    public int getItemCount() {//adapter会去告诉recylerview数据有多少个
        return articles.size();
    }



        // 3. SearchNewsViewHolder: 作用，view拿进来以后，要recycle的时候它会记住这些view。暂时记住，省略了一次binding的过程，因为binding是一次expensive的操作。
        public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

            ImageView favoriteImageView;
            ImageView itemImageView;
            TextView itemTitleTextView;

            public SearchNewsViewHolder(@NonNull View itemView) {
                super(itemView);
                SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
                favoriteImageView = binding.searchItemFavorite;
                itemImageView = binding.searchItemImage;
                itemTitleTextView = binding.searchItemTitle;
            }
        }

    }

