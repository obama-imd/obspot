package br.ufrn.imd.giife.obspot.recursoeducacional;

import br.ufrn.imd.giife.obspot.common.service.exception.EntityNotFoundException;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.RecursoEducacionalMapper;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.RecursoEducacionalRequestDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.RecursoEducacionalUpdateRequestDTO;
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

    public RecursoEducacionalEntity create(RecursoEducacionalRequestDTO requestDTO) {
        RecursoEducacionalEntity recurso = recursoEducacionalMapper.toEntity(requestDTO);
        findAndSetEntities(requestDTO, recurso);

        return recursoEducacionalRepository.save(recurso);
    }

    private void findAndSetEntities(RecursoEducacionalRequestDTO requestDTO, RecursoEducacionalEntity recurso) {
        recurso.setAuthors(
                requestDTO.authorsIds().stream().map(userService::findById).toList()
        );

         recurso.setCoAuthors(
                 requestDTO.coAuthorsIds().stream().map(userService::findById).toList()
         );

         recurso.setTeachers(
                 requestDTO.teachersIds().stream().map(userService::findById).toList()
         );
    }

    public RecursoEducacionalEntity findById(@NonNull Long id) {
        return recursoEducacionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(RECURSO_NOT_FOUND_MESSAGE.formatted(id)));
    }

    public RecursoEducacionalEntity update(Long id, RecursoEducacionalUpdateRequestDTO updateDTO) {
        RecursoEducacionalEntity recurso = findById(id);
        return recursoEducacionalMapper.update(updateDTO, recurso);
    }

    public void delete(@NonNull Long id) {
        recursoEducacionalRepository.delete(findById(id));
    }
 }
