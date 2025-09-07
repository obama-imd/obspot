package br.ufrn.imd.giife.obspot.security.auth.controller;

import br.ufrn.imd.giife.obspot.common.controller.dto.ResponseDTO;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.RegistrationRequestDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Autenticação",
        description = "Endpoints necessários para o processo de autenticação"
)
public interface AuthController {

    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegistrationRequestDTO registrationDTO);
}
