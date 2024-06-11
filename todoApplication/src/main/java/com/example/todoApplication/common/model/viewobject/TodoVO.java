package com.example.todoApplication.common.model.viewobject;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
public class TodoVO  implements Serializable {

    @Serial
    private static  final long serialVersionUID = 1L;

    private  String id;
    private  String title;
    private  String description;
    private  String isFinished;
    private  String cover;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TodoVO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", isFinished='").append(isFinished).append('\'');
        sb.append(", cover='").append(cover).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
