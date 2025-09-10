package br.ufrn.imd.giife.obspot.user.controller;

import br.ufrn.imd.giife.obspot.security.auth.controller.dto.RegistrationRequestDTO;
import br.ufrn.imd.giife.obspot.user.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(RegistrationRequestDTO registrationDTO);

}
