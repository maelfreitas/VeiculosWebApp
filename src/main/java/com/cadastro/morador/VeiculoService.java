package com.cadastro.morador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    @Autowired private VeiculoRepository repo;

    public List<Veiculo> listAll() {
        return (List<Veiculo>) repo.findAll();
    }

    public void save(Veiculo veiculo) {
        repo.save(veiculo);
    }
    public Veiculo get(Integer id) throws VeiculoNotFoundException {
        Optional<Veiculo> result = repo.findById(id);

        if(result.isPresent()) {
            return result.get();
        }
        throw new VeiculoNotFoundException("Veiculo não encontrado pelo ID " + id);
    }
    public void delete(Integer id) throws VeiculoNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0) {
            throw new VeiculoNotFoundException("Veiculo não encontrado pelo ID " + id);

        }
        repo.deleteById(id);
    }
}
