package org.apiconsum.crud.Repository;

import org.apiconsum.crud.Entidade.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    // Outros métodos personalizados, se necessário
}
