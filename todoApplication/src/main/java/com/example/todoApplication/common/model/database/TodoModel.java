package com.example.todoApplication.common.model.database;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "todo")
public class TodoModel extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserModel userId;

    @NotBlank(message = "Title is Mandatory")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Description is Mandatory")
    @Column(name = "description")
    private String description;

    @Column(name = "is_finished")
    private String is_finished;

    @Size(max = 5 * 1024 *1024, message = "Image size must not exceed 5MB")
    @Column(name = "cover")
    private String cover;

    @Column(name = "created_by")
    private String createdBy;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @CreationTimestamp
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    


}
