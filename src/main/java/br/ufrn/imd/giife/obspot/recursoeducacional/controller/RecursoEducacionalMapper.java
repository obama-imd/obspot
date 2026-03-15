package br.ufrn.imd.giife.obspot.recursoeducacional.controller;

import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.request.RecursoEducacionalRequestDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.request.RecursoEducacionalUpdateRequestDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.response.RecursoEducacionalResponseDTO;
import br.ufrn.imd.giife.obspot.recursoeducacional.model.RecursoEducacionalEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RecursoEducacionalMapper {

    RecursoEducacionalEntity toEntity(RecursoEducacionalRequestDTO requestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RecursoEducacionalEntity partialUpdate(RecursoEducacionalUpdateRequestDTO updateDTO, @MappingTarget RecursoEducacionalEntity recurso);

    RecursoEducacionalResponseDTO toResponse(RecursoEducacionalEntity recurso);
}
