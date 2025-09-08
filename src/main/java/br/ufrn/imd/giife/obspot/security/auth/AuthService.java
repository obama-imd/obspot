package br.ufrn.imd.giife.obspot.security.auth;

import br.ufrn.imd.giife.obspot.security.auth.controller.dto.LoginRequestDTO;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.LoginResponseDTO;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.RegistrationRequestDTO;
import br.ufrn.imd.giife.obspot.security.jwt.JwtService;
import br.ufrn.imd.giife.obspot.security.userdetails.UserDetailsImpl;
import br.ufrn.imd.giife.obspot.user.UserEntity;
import br.ufrn.imd.giife.obspot.user.controller.UserMapper;
import br.ufrn.imd.giife.obspot.user.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserService userService,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
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

    public LoginResponseDTO login(LoginRequestDTO loginDTO) {
        Authentication auth = authenticate(loginDTO);

        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        String accessToken = jwtService.generateToken(user.getUsername());

        return new LoginResponseDTO(accessToken);
    }

    public Authentication authenticate(LoginRequestDTO loginDTO) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.password()
                )
        );
    }

    private void checkPasswords(final String password, final String confirmPassword) {
        if (password == null || !password.equals(confirmPassword)) {
            throw new BadCredentialsException("Passwords do not match");
        }
    }


}
