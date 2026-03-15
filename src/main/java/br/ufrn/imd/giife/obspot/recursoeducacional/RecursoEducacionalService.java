package br.ufrn.imd.giife.obspot.recursoeducacional;

import br.ufrn.imd.giife.obspot.common.service.exception.EntityNotFoundException;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.RecursoEducacionalMapper;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.request.RecursoEducacionalRequestDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.request.RecursoEducacionalUpdateRequestDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.model.RecursoEducacionalEntity;
import br.ufrn.imd.giife.obspot.user.UserService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class RecursoEducacionalService {

    private static final String RECURSO_NOT_FOUND_MESSAGE = "O recurso educacional de ID %d não foi encontrado!";

    private final RecursoEducacionalRepository recursoEducacionalRepository;
    private final RecursoEducacionalMapper recursoEducacionalMapper;
    private final UserService userService;

    public  RecursoEducacionalService(
            RecursoEducacionalRepository recursoEducacionalRepository,
            RecursoEducacionalMapper recursoEducacionalMapper,
            UserService userService
    ) {
        this.recursoEducacionalRepository = recursoEducacionalRepository;
        this.recursoEducacionalMapper = recursoEducacionalMapper;
        this.userService = userService;
    }

    public @NonNull RecursoEducacionalEntity create(@NonNull RecursoEducacionalRequestDTO requestDTO) {
        RecursoEducacionalEntity recurso = recursoEducacionalMapper.toEntity(requestDTO);
        recurso.setAuthors(userService.findAllById(requestDTO.authorsIds()));
        recurso.setCoAuthors(userService.findAllById(requestDTO.coAuthorsIds()));
        recurso.setTeachers(userService.findAllById(requestDTO.teachersIds()));

        return recursoEducacionalRepository.save(recurso);
    }

    public @NonNull RecursoEducacionalEntity findById(@NonNull Long id) {
        return recursoEducacionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(RECURSO_NOT_FOUND_MESSAGE.formatted(id)));
    }

    public @NonNull RecursoEducacionalEntity partialUpdate(@NonNull Long id, @NonNull RecursoEducacionalUpdateRequestDTO updateDTO) {
        RecursoEducacionalEntity recurso = findById(id);
        return recursoEducacionalMapper.partialUpdate(updateDTO, recurso);
    }

    public void delete(@NonNull Long id) {
        recursoEducacionalRepository.delete(findById(id));
    }
 }
