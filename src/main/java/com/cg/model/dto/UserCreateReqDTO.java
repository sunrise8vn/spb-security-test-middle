package com.cg.model.dto;

import com.cg.exception.DataInputException;
import com.cg.model.Role;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateReqDTO {

    @NotBlank(message = "Vui lòng nhập email.")
    @Email(message = "Email không đúng định dạng.")
    private String username;

    @NotBlank(message = "Vui lòng nhập mật khẩu.")
    private String password;

    @NotBlank(message = "Vui lòng nhập mật khẩu xác nhận.")
    private String confirmPassword;

    private String roleCode;

    public User toUser(Role role) {
        return new User()
                .setUsername(username)
                .setPassword(password)
                .setRole(role)
                ;
    }
}
