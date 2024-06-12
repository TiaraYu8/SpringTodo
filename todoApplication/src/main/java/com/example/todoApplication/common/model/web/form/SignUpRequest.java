package com.example.todoApplication.common.model.web.form;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String password;

}
