package com.example.baiquatrinh2;

public class NoteDTO {
    private int id;
    private String title, desc;
    private String time;

    public NoteDTO(int id, String title, String desc, String time) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.time = time;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
