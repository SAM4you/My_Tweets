package com.ander.apps.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ander.apps.client.models.Tweet;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.Viewholder>{

    Context context;
    List<Tweet> tweets;
    // Pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // For each row, inflate the layout
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweets, parent, false);
        return new Viewholder(view);
    }

    // Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Get the data at position
        Tweet tweet = tweets.get(position);
        // Bind the tweets in the ViewHolder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }




    // Define a viewholder
    public class Viewholder extends RecyclerView.ViewHolder{

        ImageView ivProfile;
        TextView tvTweetBody;
        TextView tvScreenName;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvTweetBody = itemView.findViewById(R.id.tvTweetBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
        }

        public void bind(Tweet tweet) {
            tvTweetBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            Glide.with(context).load(tweet.user.profileImageUrl)
                    .into(ivProfile);
        }
    }
}