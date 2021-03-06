package com.example.divyanshujain.edoteng.Models;

/**
 * Created by divyanshuPC on 4/2/2017.
 */

public class StateModel {
    private String state_id;
    private String name;

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.state_id.equals(((StateModel) obj).getState_id());
    }
}
