package br.ufrn.imd.giife.obspot.recursoeducacional;

import br.ufrn.imd.giife.obspot.common.service.exception.EntityNotFoundException;
import br.ufrn.imd.giife.obspot.recursoeducacional.model.RecursoEducacionalEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class RecursoEducacionalService {

    private static final String RECURSO_NOT_FOUND_MESSAGE = "O recurso educacional de ID %d não foi encontrado!";

    private final RecursoEducacionalRepository recursoEducacionalRepository;

    public  RecursoEducacionalService(
            RecursoEducacionalRepository recursoEducacionalRepository
    ) {
        this.recursoEducacionalRepository = recursoEducacionalRepository;
    }

    public RecursoEducacionalEntity findById(@NonNull Long id) {
        return recursoEducacionalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(RECURSO_NOT_FOUND_MESSAGE.formatted(id)));
    }
 }
