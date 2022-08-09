package com.ander.apps.client;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ander.apps.client.models.Tweet;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {

    Tweet tweet;

    ImageView ivProfile;
    TextView tvScreenName;
    TextView tvUserName;
    TextView tvTweetBody;
    TextView tvTime;
    TextView tvSymbole;

    //@Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_app);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // resolve the view objects
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        tvScreenName = (TextView) findViewById(R.id.tvScreenName);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvTweetBody =(TextView) findViewById(R.id.tvTweetBody);
        tvTime = findViewById(R.id.tvTime);
        tvSymbole =(TextView) findViewById(R.id.tvSymbole);

        //unwrap tweet
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        Log.d("TweetDetailsActivity",String.format("Showing details for '%s'", tweet.getTimeStamp()));

        //set text for tweetbody
        tvScreenName.setText(tweet.user.name);
        tvUserName.setText(tweet.user.screenName);
        tvTweetBody.setText(tweet.body);
        tvTime.setText(tweet.getTimeStamp());
        Glide.with(this)
                .load(tweet.user.profileImageUrl)
                //.apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .transform(new CircleCrop())
                .into(ivProfile);
    }
}