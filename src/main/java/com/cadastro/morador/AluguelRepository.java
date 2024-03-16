package com.cadastro.morador;

import org.springframework.data.repository.CrudRepository;

public interface AluguelRepository extends CrudRepository<Aluguel, Integer> {
    public Long countById(Integer id);
}
