package com.example.demonew;

public class Modal2 {
    String id,name,dateadded,datemod,v,quotecount;

    public Modal2(String id, String name, String dateadded, String datemod, String v, String quotecount) {
        this.id = id;
        this.name = name;
        this.dateadded = dateadded;
        this.datemod = datemod;
        this.v = v;
        this.quotecount = quotecount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateadded() {
        return dateadded;
    }

    public void setDateadded(String dateadded) {
        this.dateadded = dateadded;
    }

    public String getDatemod() {
        return datemod;
    }

    public void setDatemod(String datemod) {
        this.datemod = datemod;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getQuotecount() {
        return quotecount;
    }

    public void setQuotecount(String quotecount) {
        this.quotecount = quotecount;
    }
}
