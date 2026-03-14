package br.ufrn.imd.giife.obspot.security.auth.controller;

import br.ufrn.imd.giife.obspot.common.controller.dto.ResponseDTO;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.LoginRequestDTO;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.LoginResponseDTO;
import br.ufrn.imd.giife.obspot.security.auth.controller.dto.RegistrationRequestDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Autenticação",
        description = "Endpoints necessários para o processo de autenticação"
)
public interface AuthController {

    @SecurityRequirements({})
    ResponseEntity<ResponseDTO> register(@RequestBody RegistrationRequestDTO registrationDTO);

    @SecurityRequirements({})
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginDTO);
}
