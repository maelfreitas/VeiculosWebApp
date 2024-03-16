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
public class RegistroController {
    @Autowired private RegistroService service;

    @GetMapping("/menu/tabelaRegistro")
    public String showRegistroList(Model model) {
        List<Registro> listRegistro = service.listAll();
        model.addAttribute("listRegistro", listRegistro);
        return "tabelaRegistro";
    }


    @GetMapping("/menu/cadastroRegistro")
    public String showNewForm(Model model) {
        model.addAttribute("registro", new Registro());
        model.addAttribute("pageTitle", "FAZER REGISTRO");
        return "cadastroRegistro";
    }

    @PostMapping("/menu/salvar-registro")
    public String saveRegistro(Registro registro, RedirectAttributes ra) {
        service.save(registro);
        ra.addFlashAttribute("message", "Registro salvo no banco de dados");
        return "redirect:/menu";
    }
    @GetMapping("/menu/editar-registro/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Registro registro = service.get(id);
            model.addAttribute("registro", registro);
            return "cadastroRegistro";
        } catch (RegistroNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/menu/tabelaRegistro";
        }
    }
    @GetMapping("/menu/remover-registro/{id}")
    public String deleteRegistro(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "O registro com ID: " + id + " foi removido do banco de dados");
        } catch (RegistroNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/menu/tabelaRegistro";
    }
}

