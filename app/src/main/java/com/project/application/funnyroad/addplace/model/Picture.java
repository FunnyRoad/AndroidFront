package com.project.application.funnyroad.addplace.model;

import com.project.application.funnyroad.detailroadtripnew.model.Post;

/**
 * Created by oa on 28/02/2017.
 */

public class Picture {

    private int id;
    private String type;
    private int place;
    private Post post;

    public Picture(String type){
        this.type = type;
    }

    public Picture(String type, int place) {
        this.type = type;
        this.place = place;
    }

    public Picture(int id, String type, int place) {
        this.id = id;
        this.type = type;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
