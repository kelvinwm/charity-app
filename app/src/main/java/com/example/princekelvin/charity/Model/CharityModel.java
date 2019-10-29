package com.example.princekelvin.charity.Model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "charity_table")
public class CharityModel {
    @PrimaryKey(autoGenerate = true)
    int id;
    String title;
    String start_date;
    String end_date;
    String target_amount;
    String description;

    public CharityModel(String title, String start_date, String end_date, String target_amount, String description) {
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.target_amount = target_amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTarget_amount() {
        return target_amount;
    }

    public void setTarget_amount(String target_amount) {
        this.target_amount = target_amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
