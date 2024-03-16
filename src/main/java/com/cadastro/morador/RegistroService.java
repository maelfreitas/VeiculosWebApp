package com.cadastro.morador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroService {
    @Autowired private RegistroRepository repo;

    public List<Registro> listAll() {
        return (List<Registro>) repo.findAll();
    }

    public void save(Registro registro) {
        repo.save(registro);
    }
    public Registro get(Integer id) throws RegistroNotFoundException {
        Optional<Registro> result = repo.findById(id);

        if(result.isPresent()) {
            return result.get();
        }
        throw new RegistroNotFoundException("Registro não encontrado pelo ID " + id);
    }
    public void delete(Integer id) throws RegistroNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0) {
            throw new RegistroNotFoundException("Registro não encontrado pelo ID " + id);

        }
        repo.deleteById(id);
    }
}
