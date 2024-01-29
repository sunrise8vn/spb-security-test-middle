package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.dto.UserCreateReqDTO;
import com.cg.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User, String>, UserDetailsService {

    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    User create(UserCreateReqDTO userCreateReqDTO);
}
