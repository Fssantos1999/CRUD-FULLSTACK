package org.apiconsum.crud.Repository;


import org.apiconsum.crud.Entidade.Fornecedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository  extends CrudRepository<Fornecedor, Long> {
}
