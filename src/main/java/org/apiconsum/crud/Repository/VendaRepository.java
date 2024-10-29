package org.apiconsum.crud.Repository;



import jakarta.persistence.Entity;
import org.apiconsum.crud.Entidade.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
