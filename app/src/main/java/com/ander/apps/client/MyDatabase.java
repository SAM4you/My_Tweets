package com.ander.apps.client;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ander.apps.client.models.SampleModel;
import com.ander.apps.client.models.SampleModelDao;

@Database(entities={SampleModel.class}, version=1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SampleModelDao sampleModelDao();

    // Database name to be used
    public static final String NAME = "MyDataBase";
}
