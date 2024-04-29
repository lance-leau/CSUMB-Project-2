package com.example.mapwithmarker.Database;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {
    public static final Migration MIGRATION_1_3 = new Migration(1, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Create the new 'cities' column in the UserTable
            database.execSQL("ALTER TABLE UserTable ADD COLUMN cities TEXT DEFAULT '0'");

            // Note: If there are other changes between version 1 and version 3,
            //       you would add additional migration steps here.
        }
    };
}