package com.cadastro.morador;

import org.springframework.data.repository.CrudRepository;

public interface VeiculoRepository extends CrudRepository<Veiculo, Integer> {
    public Long countById(Integer id);
}
