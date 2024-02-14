package com.example.notes;

import java.io.Serializable;

public class NoteModel implements Serializable {
    String title,message,id,time;

    public NoteModel(String title, String message, String id, String time) {
        this.title = title;
        this.message = message;
        this.id = id;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
