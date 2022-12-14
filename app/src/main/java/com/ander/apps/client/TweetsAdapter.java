package com.ander.apps.client;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ander.apps.client.models.Tweet;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;
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

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }




    // Define a viewholder
    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView ivProfile;
        TextView tvTweetBody;
        TextView tvScreenName;
        TextView tvUserName;
        TextView tvSymbole;
        TextView tvTimeStamp;
        //ImageView ivTweetImage;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvTweetBody = itemView.findViewById(R.id.tvTweetBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvSymbole = itemView.findViewById(R.id.tvSymbole);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            //ivTweetImage = itemView.findViewById(R.id.ivTweetImage);
            itemView.setOnClickListener(this);
        }

        public void bind(Tweet tweet) {
            tvTweetBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.name);
            tvUserName.setText(tweet.user.screenName);
            tvTimeStamp.setText(tweet.getFormattedTimestamp());
            Glide.with(context).load(tweet.user.profileImageUrl)
                    .transform(new CircleCrop())
                    .into(ivProfile);
        }

        @Override
        public void onClick(View view) {
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Tweet tweet = tweets.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, TweetDetailsActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                // show the activity
                context.startActivity(intent);

        }
    }
}

}


