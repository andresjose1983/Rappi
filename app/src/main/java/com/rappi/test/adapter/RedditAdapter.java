package com.rappi.test.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rappi.test.R;
import com.rappi.test.model.Children;
import com.rappi.test.model.ChildrenResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mendez Fernandez on 30/12/2016.
 */

public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.RedditViewHolder>
        implements Filterable{

    private List<ChildrenResponse> mChildrens = new ArrayList<>();
    private List<ChildrenResponse> mChildrensFilter = new ArrayList<>();
    private int mWidthImage;

    public RedditAdapter(List<ChildrenResponse> mChildrens, int mWidthImage) {
        this.mChildrens = mChildrens;
        this.mChildrensFilter = new ArrayList<>(this.mChildrens);
        this.mWidthImage = mWidthImage;
    }

    @Override
    public RedditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reddit,
                parent, false);

        return new RedditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RedditViewHolder holder, int position) {
        Children children = mChildrens.get(position).getData();
        holder.mTvTitle.setText(children.getHeaderTitle());

        Glide.with(holder.mIvReddirHeader.getContext()).load(children.getHeaderImg())
                .override(mWidthImage, mWidthImage - 50 ).crossFade(500).into(holder.mIvReddirHeader);
    }

    @Override
    public int getItemCount() {
        return mChildrens.size();
    }

    class RedditViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view)
        CardView mCardView;
        @BindView(R.id.iv_reddir_header)
        ImageView mIvReddirHeader;
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        public RedditViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public Filter getFilter() {
        return new RedditFilter(this, mChildrensFilter);
    }

    private static class RedditFilter extends Filter{

        private List<ChildrenResponse> filtersReddit = new ArrayList<>();
        final private List<ChildrenResponse> reddits;
        final private RedditAdapter redditAdapter;

        public RedditFilter(RedditAdapter redditAdapter, List<ChildrenResponse> reddits) {
            super();
            this.reddits = reddits;
            this.redditAdapter = redditAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filtersReddit.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {
                filtersReddit.addAll(reddits);
            } else {
                final String filterPattern = constraint.toString().toUpperCase().trim();

                for (final ChildrenResponse childrenResponse : reddits) {
                    try {
                        if (childrenResponse.getData().getHeaderTitle().toUpperCase().contains(filterPattern)
                                || childrenResponse.getData().getDisplayName().toUpperCase().contains(filterPattern)
                                || childrenResponse.getData().getTitle().toUpperCase().contains(filterPattern)) {
                            filtersReddit.add(childrenResponse);
                        }
                    }catch(NullPointerException e){
                        Log.e(RedditFilter.class.getCanonicalName(), e.getMessage());
                    }
                }
            }
            results.values = filtersReddit;
            results.count = filtersReddit.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            redditAdapter.mChildrens.clear();
            redditAdapter.mChildrens.addAll((ArrayList<ChildrenResponse>) filterResults.values);
            redditAdapter.notifyDataSetChanged();
        }
    }
}
