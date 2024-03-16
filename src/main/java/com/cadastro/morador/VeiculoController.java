package com.cadastro.morador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class VeiculoController {
    @Autowired private VeiculoService service;

    @GetMapping("/menu/tabelaVeiculo")
    public String showVeiculoList(Model model) {
        List<Veiculo> listVeiculo = service.listAll();
        model.addAttribute("listVeiculo", listVeiculo);
        return "tabelaVeiculo";
    }

    @GetMapping("/menu")
    public String showMenu(Model model) {
        return "menu"; //REFERENCIA AO ARQUIVO HTML MENU PRINCIPAL
    }

    @GetMapping("/menu/cadastroVeiculo")
    public String showNewForm(Model model) {
        model.addAttribute("veiculo", new Veiculo());
        model.addAttribute("pageTitle", "CADASTRO DE ESPAÇOS");
        return "cadastroVeiculo";
    }

    @PostMapping("/menu/salvar-veiculo")
    public String saveVeiculo(Veiculo veiculo, RedirectAttributes ra) {
        service.save(veiculo);
        ra.addFlashAttribute("message", "Veiculo salvo no banco de dados");
        return "redirect:/menu";
    }
    @GetMapping("/menu/editar-veiculo/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Veiculo veiculo = service.get(id);
            model.addAttribute("veiculo", veiculo);
            return "cadastroVeiculo";
        } catch (VeiculoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/menu/tabelaVeiculo";
        }
    }
    @GetMapping("/menu/remover-veiculo/{id}")
    public String deleteVeiculo(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "O carro com ID: " + id + " foi removido do banco de dados");
        } catch (VeiculoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/menu/tabelaVeiculo";
    }
}

