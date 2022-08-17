package com.ander.apps.client;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ander.apps.client.models.SampleModel;
import com.ander.apps.client.models.SampleModelDao;
import com.ander.apps.client.models.Tweet;
import com.ander.apps.client.models.TweetDao;
import com.ander.apps.client.models.User;

@Database(entities={SampleModel.class, Tweet.class, User.class}, version=4)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SampleModelDao sampleModelDao();
    public abstract TweetDao tweetDao();

    // Database name to be used
    public static final String NAME = "MyDataBase";
}
