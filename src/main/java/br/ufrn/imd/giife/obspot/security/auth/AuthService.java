package br.ufrn.imd.giife.obspot.security.auth;

import br.ufrn.imd.giife.obspot.user.UserEntity;
import br.ufrn.imd.giife.obspot.user.controller.UserMapper;
import br.ufrn.imd.giife.obspot.user.service.UserService;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.RegistrationRequestDTO;
import br.ufrn.imd.giife.obspot.security.jwt.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    AuthService(
            JwtService jwtService,
            UserService userService,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity register(RegistrationRequestDTO registrationDTO) {
        checkPasswords(registrationDTO.password(), registrationDTO.confirmPassword());

        UserEntity newUser = userMapper.toEntity(registrationDTO);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userService.save(newUser);
    }

    private void checkPasswords(final String password, final String confirmPassword) {
        if (password == null || !password.equals(confirmPassword)) {
            throw new BadCredentialsException("Passwords do not match");
        }
    }


}
