package br.ufrn.imd.giife.obspot.recursoeducacional.controller;

import br.ufrn.imd.giife.obspot.common.controller.dto.ResponseDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.RecursoEducacionalService;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.request.RecursoEducacionalRequestDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.request.RecursoEducacionalUpdateRequestDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.response.RecursoEducacionalResponseDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.model.RecursoEducacionalEntity;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recursos-educacionais")
public class RecursoEducacionalControllerImpl implements  RecursoEducacionalController {

    private final Logger log = LoggerFactory.getLogger(RecursoEducacionalControllerImpl.class);
    private final RecursoEducacionalService recursoEducacionalService;
    private final RecursoEducacionalMapper recursoEducacionalMapper;

    public RecursoEducacionalControllerImpl(RecursoEducacionalService recursoEducacionalService, RecursoEducacionalMapper recursoEducacionalMapper) {
        this.recursoEducacionalService = recursoEducacionalService;
        this.recursoEducacionalMapper = recursoEducacionalMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody RecursoEducacionalRequestDTO requestDTO) {
        log.info("Criando recurso educacional com os dados: {}", requestDTO);
        RecursoEducacionalEntity recurso = recursoEducacionalService.create(requestDTO);
        log.info("Recurso educacional com ID {} e título \"{}\" criado com sucesso", recurso.getId(), recurso.getTitle());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED.value(), "Recurso educacional criado com sucesso"));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RecursoEducacionalResponseDTO> findById(@PathVariable Long id) {
        log.info("Buscando recurso educacional com ID {}", id);
        RecursoEducacionalEntity recurso = recursoEducacionalService.findById(id);
        log.info("Recurso educacional com ID {} encontrado com sucesso", id);

        return ResponseEntity.ok(recursoEducacionalMapper.toResponse(recurso));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO> partialUpdate(@PathVariable Long id, @Valid @RequestBody RecursoEducacionalUpdateRequestDTO updateDTO) {
        log.info("Atualizando recurso educacional com ID {} com os dados: {}", id, updateDTO);
        RecursoEducacionalEntity recurso = recursoEducacionalService.partialUpdate(id, updateDTO);
        log.info("Recurso educacional com ID {} atualizado com sucesso", recurso.getId());

        return ResponseEntity
                .ok(new ResponseDTO(HttpStatus.OK.value(), "Recurso educacional atualizado com sucesso"));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        log.info("Deletando recurso educacional com ID {}", id);
        recursoEducacionalService.delete(id);
        log.info("Recurso educacional com ID {} deletado com sucesso", id);

        return ResponseEntity
                .ok(new ResponseDTO(HttpStatus.OK.value(), "Recurso educacional deletado com sucesso"));
    }
}
