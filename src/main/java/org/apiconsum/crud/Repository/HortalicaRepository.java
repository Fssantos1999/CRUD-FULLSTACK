package org.apiconsum.crud.Repository;

import org.apiconsum.crud.Entidade.Hortalica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HortalicaRepository extends JpaRepository<Hortalica, Long> {

}
