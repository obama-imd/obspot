package br.ufrn.imd.giife.obspot.common.controller.dto;

import org.springframework.http.HttpStatus;

public record ResponseDTO(
        HttpStatus status,
        String description
) {
}
