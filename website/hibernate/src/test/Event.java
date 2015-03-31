package test;

import java.util.Date;

public class Event {
    private int id;

    private String title;
    private Date date;

    Event() {}

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}