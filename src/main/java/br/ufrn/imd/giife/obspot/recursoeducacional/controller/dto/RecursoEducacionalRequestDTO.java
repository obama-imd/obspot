package br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto;

import br.ufrn.imd.giife.obspot.recursoeducacional.model.TipoRecursoEducacional;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

public record RecursoEducacionalRequestDTO(
        @Schema(
                description = "Título do recurso educacional.",
                example = "Introdução à Biologia Celular"
        )
        String title,

        @Schema(
                description = "Breve descrição sobre o conteúdo do recurso.",
                example = "Um vídeo interativo sobre as organelas celulares."
        )
        String description,

        @Schema(
                description = "Caminho ou URL para o arquivo do recurso.",
                example = "/uploads/videos/biologia_celular.pdf"
        )
        String pathFile,

        @Schema(
                description = "Lista de IDs dos autores principais do recurso.",
                example = "[1, 2]"
        )
        List<UUID> authorsIds,

        @Schema(
                description = "Lista de IDs dos co-autores que contribuíram com o recurso.",
                example = "[3]"
        )
        List<UUID> coAuthorsIds,

        @Schema(
                description = "Lista de IDs dos orientadores do desenvolvimento do recurso.",
                example = "[4, 5]"
        )
        List<UUID> teachersIds,

        @Schema(
                description = "Tipo do recurso educacional.",
                example = "ARTIGO"
        )
        TipoRecursoEducacional tipoRecursoEducacional
) {
}
