package com.finan.orcamento.controller;

import com.finan.orcamento.model.ClienteModel;
import com.finan.orcamento.repositories.ClienteRepository;
import com.finan.orcamento.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String getClientePage(Model model, @RequestParam(required = false) String success, 
                                 @RequestParam(required = false) String error,
                                 @RequestParam(required = false) String message) {
        List<ClienteModel> clientes = clienteService.buscarCliente();
        model.addAttribute("clientes", clientes);
        model.addAttribute("clienteModel", new ClienteModel());
        if ("true".equals(success)) {
            model.addAttribute("successMessage", "Cliente salvo com sucesso!");
        }
        if ("true".equals(error)) {
            String errorMsg = message != null ? message : "Erro ao salvar cliente. Tente novamente.";
            model.addAttribute("errorMessage", errorMsg);
        }
        return "clientePage";
    }

    @PostMapping
    public String cadastraCliente(@ModelAttribute ClienteModel clienteModel) {
        try {
            if (clienteModel.getNome() == null || clienteModel.getNome().trim().isEmpty()) {
                return "redirect:/clientes?error=true&message=Nome é obrigatório";
            }
            if (clienteModel.getCpf() == null || clienteModel.getCpf().trim().isEmpty()) {
                return "redirect:/clientes?error=true&message=CPF é obrigatório";
            }
            clienteService.cadastrarCliente(clienteModel);
            return "redirect:/clientes?success=true";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/clientes?error=true";
        }
    }

    @GetMapping("/pesquisa")
    public String listarClientes(Model model) {
        List<ClienteModel> clientes = clienteService.buscarCliente();
        model.addAttribute("clientes", clientes);
        model.addAttribute("clienteModel", new ClienteModel());
        return "clientePage";
    }

    @GetMapping("/{id}")
    public String buscarClientePorId(@PathVariable Long id, Model model) {
        ClienteModel cliente = clienteService.buscaId(id);
        model.addAttribute("clienteModel", cliente);
        List<ClienteModel> clientes = clienteService.buscarCliente();
        model.addAttribute("clientes", clientes);
        return "clientePage";
    }

    @PostMapping("/{id}")
    public String atualizaCliente(@PathVariable Long id, @ModelAttribute ClienteModel clienteModel) {
        try {
            clienteService.atualizaCliente(clienteModel, id);
            return "redirect:/clientes?success=true";
        } catch (Exception e) {
            return "redirect:/clientes?error=true";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deletaCliente(@PathVariable Long id) {
        clienteService.deletaCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @ResponseBody
    public List<ClienteModel> searchClientes(@RequestParam String searchTerm) {
        // Busca por nome ou CPF
        List<ClienteModel> porNome = clienteRepository.findByNomeContainingIgnoreCase(searchTerm);
        List<ClienteModel> porCpf = clienteRepository.findByCpfContaining(searchTerm);
        
        // Combina resultados e remove duplicatas
        porNome.addAll(porCpf);
        return porNome.stream().distinct().toList();
    }
}

