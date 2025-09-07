package br.ufrn.imd.giife.obspot.security.auth;

import br.ufrn.imd.giife.obspot.user.UserEntity;
import br.ufrn.imd.giife.obspot.user.controller.UserMapper;
import br.ufrn.imd.giife.obspot.user.service.UserService;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.RegistrationRequestDTO;
import br.ufrn.imd.giife.obspot.security.jwt.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserMapper userMapper;

    AuthService(
            JwtService jwtService,
            UserService userService,
            UserMapper userMapper
    ) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public UserEntity register(RegistrationRequestDTO newUser) {
        checkPasswords(newUser.password(), newUser.confirmPassword());
        return userService.save(
                userMapper.toEntity(newUser)
        );
    }

    private void checkPasswords(final String password, final String confirmPassword) {
        if (password == null || !password.equals(confirmPassword)) {
            throw new BadCredentialsException("Passwords do not match");
        }
    }


}
