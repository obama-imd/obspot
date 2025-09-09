package br.ufrn.imd.giife.obspot.recursoeducacional.controller;

import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.RecursoEducacionalRequestDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.model.RecursoEducacionalEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecursoEducacionalMapper {

    RecursoEducacionalEntity toEntity(RecursoEducacionalRequestDTO requestDTO);

}
