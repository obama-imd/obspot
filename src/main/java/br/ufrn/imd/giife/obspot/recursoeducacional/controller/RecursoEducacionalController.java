package br.ufrn.imd.giife.obspot.recursoeducacional.controller;

import br.ufrn.imd.giife.obspot.common.controller.dto.ResponseDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.request.RecursoEducacionalRequestDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.request.RecursoEducacionalUpdateRequestDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.response.RecursoEducacionalResponseDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Recursos educacionais",
        description = "Endpoints para gerenciamento dos recursos educacionais"
)
public interface RecursoEducacionalController {

    ResponseEntity<ResponseDTO> create(@RequestBody RecursoEducacionalRequestDTO requestDTO);

    ResponseEntity<RecursoEducacionalResponseDTO> findById(Long id);

    ResponseEntity<ResponseDTO> partialUpdate(Long id, @RequestBody RecursoEducacionalUpdateRequestDTO updateDTO);

    ResponseEntity<ResponseDTO> delete(Long id);
}
