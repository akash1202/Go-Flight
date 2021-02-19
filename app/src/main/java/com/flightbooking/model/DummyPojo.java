package com.flightbooking.model;

import com.google.gson.annotations.SerializedName;

public class DummyPojo {
    @SerializedName("name")
    public String name;

    @SerializedName("id")
    public String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DummyPojo(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
