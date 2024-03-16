package com.cadastro.morador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {
    @Autowired private AluguelRepository repo;

    public List<Aluguel> listAll() {
        return (List<Aluguel>) repo.findAll();
    }

    public void save(Aluguel aluguel) {
        repo.save(aluguel);
    }
    public Aluguel get(Integer id) throws AluguelNotFoundException {
        Optional<Aluguel> result = repo.findById(id);

        if(result.isPresent()) {
            return result.get();
        }
        throw new AluguelNotFoundException("Aluguel não encontrado pelo ID " + id);
    }
    public void delete(Integer id) throws AluguelNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0) {
            throw new AluguelNotFoundException("Aluguel não encontrado pelo ID " + id);

        }
        repo.deleteById(id);
    }
}
