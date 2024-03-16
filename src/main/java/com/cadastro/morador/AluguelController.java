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
public class AluguelController {
    @Autowired private AluguelService service;


    @GetMapping("/menu/tabelaAluguel")
    public String showAluguelList(Model model) {
        List<Aluguel> listAluguel = service.listAll();
        model.addAttribute("listAluguel", listAluguel);
        return "tabelaAluguel";
    }

    @GetMapping("/menu/cadastroAluguel")
    public String showNewForm(Model model) {
        model.addAttribute("aluguel", new Aluguel());
        model.addAttribute("pageTitle", "FAZER ALUGUEL");
        return "cadastroAluguel";
    }

    @PostMapping("/menu/salvar-aluguel")
    public String saveAluguel(Aluguel aluguel, RedirectAttributes ra) {
        service.save(aluguel);
        ra.addFlashAttribute("message", "Aluguel salvo no banco de dados");
        return "redirect:/menu";
    }

    @GetMapping("/menu/editar-aluguel/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Aluguel aluguel = service.get(id);
            model.addAttribute("aluguel", aluguel);
            return "cadastroAluguel";
        } catch (AluguelNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/menu/tabelaAlugueis";
        }
    }

    @GetMapping("/menu/remover-aluguel/{id}")
    public String deleteAluguel(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "O aluguel com ID: " + id + " foi removido do banco de dados");
        } catch (AluguelNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/menu/tabelaAluguel";
    }
}

