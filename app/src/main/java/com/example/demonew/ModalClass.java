package com.example.demonew;

import java.util.List;

public class ModalClass {
    private String _id;
    private List<String> tags;
    private String content;
    private String author;
    private String authorSlug;
    private int length;
    private String dateAdded;
    private String dateModified;

    public String get_id() {
        return _id;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getContent() {
        return content;
    }


    public String getAuthor() {
        return author;
    }

    public String getAuthorSlug() {
        return authorSlug;
    }

    public int getLength() {
        return length;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getDateModified() {
        return dateModified;
    }
}
