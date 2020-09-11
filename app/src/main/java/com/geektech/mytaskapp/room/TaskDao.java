package com.geektech.mytaskapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.geektech.mytaskapp.models.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllLive();

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM task ORDER BY updateAt DESC")
    List<Task> getAllSort();

    @Query("SELECT * FROM task ORDER BY title DESC")
    List<Task> getAllTitleSort();

    @Query("SELECT * FROM task ORDER BY title ASC")
    List<Task> getAllTitlesSort();

    @Query("DELETE FROM task")
    void deleteAll();
}