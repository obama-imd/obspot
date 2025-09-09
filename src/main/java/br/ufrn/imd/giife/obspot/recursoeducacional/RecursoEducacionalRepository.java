package br.ufrn.imd.giife.obspot.recursoeducacional;

import br.ufrn.imd.giife.obspot.recursoeducacional.model.RecursoEducacionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecursoEducacionalRepository extends JpaRepository<RecursoEducacionalEntity, Long> {
}
