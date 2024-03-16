package com.cadastro.morador;

import org.springframework.data.repository.CrudRepository;

public interface RegistroRepository extends CrudRepository<Registro, Integer> {
    public Long countById(Integer id);
}
