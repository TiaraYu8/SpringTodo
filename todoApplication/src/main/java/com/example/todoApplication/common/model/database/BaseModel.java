package com.example.todoApplication.common.model.database;

import java.io.Serializable;

public class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseModel{");
        sb.append('}');
        return sb.toString();
    }
}
