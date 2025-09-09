package br.ufrn.imd.giife.obspot.recursoeducacional.controller.dto.request;

import br.ufrn.imd.giife.obspot.recursoeducacional.model.TipoRecursoEducacional;
import io.swagger.v3.oas.annotations.media.Schema;

public record RecursoEducacionalUpdateRequestDTO(
        @Schema(
                description = "Novo título para o recurso educacional.",
                example = "Biologia Celular Avançada"
        )
        String title,

        @Schema(
                description = "Nova descrição para o recurso.",
                example = "Um artigo detalhado sobre a mitocôndria."
        )
        String description,

        @Schema(
                description = "Novo caminho ou URL para o arquivo do recurso.",
                example = "/uploads/articles/biologia_avancada.pdf"
        )
        String pathFile,

        @Schema(
                description = "Novo tipo para o recurso educacional.",
                example = "ARTIGO"
        )
        TipoRecursoEducacional tipoRecursoEducacional
) {
}
