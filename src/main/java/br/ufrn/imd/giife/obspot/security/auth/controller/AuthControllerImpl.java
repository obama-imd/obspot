package br.ufrn.imd.giife.obspot.security.auth.controller;

import br.ufrn.imd.giife.obspot.common.controller.dto.ResponseDTO;
import br.ufrn.imd.giife.obspot.security.auth.AuthService;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.LoginRequestDTO;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.LoginResponseDTO;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.RegistrationRequestDTO;
import br.ufrn.imd.giife.obspot.user.UserEntity;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {

    public static final String USER_REGISTER_SUCCEEDED = "Usuário registrado com sucesso!";
    private final Logger log = LoggerFactory.getLogger(AuthControllerImpl.class);

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegistrationRequestDTO registrationDTO) {
        log.debug("Registrando usuário com os dados: {}", registrationDTO);
        UserEntity user = authService.register(registrationDTO);
        log.debug("Usuário registrado com sucesso: {}", user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ResponseDTO(
                                HttpStatus.CREATED.value(),
                                USER_REGISTER_SUCCEEDED
                        )
                );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginDTO) {
        LoginResponseDTO response = authService.login(loginDTO);
        log.debug("Token gerado com sucesso para o usuário {}", loginDTO.email());

        return ResponseEntity.ok(response);
    }

}
