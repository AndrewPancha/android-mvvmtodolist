package com.example.roomwords.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "task")
    private String word;

    @ColumnInfo(name = "isDone")
    public Boolean isDone;

    public String getWord() {
        return word;
    }

    public Task(@NonNull String word, Boolean isDone) {
        this.word = word;
        this.isDone = isDone;
    }
}
