package com.finan.orcamento.controller;

import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.repositories.UsuarioRepository;
import com.finan.orcamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String getUsuarioPage(Model model, @RequestParam(required = false) String success,
                                 @RequestParam(required = false) String error,
                                 @RequestParam(required = false) String message) {
        List<UsuarioModel> usuarios = usuarioService.buscarUsuario();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioModel", new UsuarioModel());
        if ("true".equals(success)) {
            model.addAttribute("successMessage", "Usuário salvo com sucesso!");
        }
        if ("true".equals(error)) {
            String errorMsg = message != null ? message : "Erro ao salvar usuário. Tente novamente.";
            model.addAttribute("errorMessage", errorMsg);
        }
        return "usuarioPage";
    }

    @PostMapping
    public String cadastraUsuario(@ModelAttribute UsuarioModel usuarioModel) {
        try {
            if (usuarioModel.getNomeUsuario() == null || usuarioModel.getNomeUsuario().trim().isEmpty()) {
                return "redirect:/usuarios?error=true";
            }
            usuarioService.cadastrarUsuario(usuarioModel);
            return "redirect:/usuarios?success=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/usuarios?error=true";
        }
    }

    @GetMapping("pesquisa")
    public String listarUsuarios(Model model) {
        List<UsuarioModel> usuarios = usuarioService.buscarUsuario();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioModel", new UsuarioModel());
        return "usuarioPage";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<UsuarioModel> searchUsuarios(@RequestParam String searchTerm) {
        return usuarioRepository.findByNomeUsuarioContainingIgnoreCase(searchTerm);
    }

}