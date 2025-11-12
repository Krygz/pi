package com.finan.orcamento.controller;

import com.finan.orcamento.model.ClienteModel;
import com.finan.orcamento.model.OrcamentoModel;
import com.finan.orcamento.model.UsuarioModel;
import com.finan.orcamento.service.ClienteService;
import com.finan.orcamento.service.OrcamentoService;
import com.finan.orcamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/orcamentos")
public class OrcamentoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private OrcamentoService orcamentoService;

    @GetMapping
    public String getOrcamentoPage(Model model, @RequestParam(required = false) String error,
                                   @RequestParam(required = false) String success,
                                   @RequestParam(required = false) String message) {
        model.addAttribute("newOrcamento", new OrcamentoModel());
        model.addAttribute("orcamentos", orcamentoService.buscarCadastro());
        if ("true".equals(error)) {
            String errorMsg = message != null ? message : "Erro ao salvar orçamento. Tente novamente.";
            model.addAttribute("errorMessage", errorMsg);
        }
        if ("true".equals(success)) {
            model.addAttribute("successMessage", "Orçamento salvo com sucesso!");
        }
        return "orcamentoPage";
    }

    @GetMapping("/api")
    public ResponseEntity<List<OrcamentoModel>>buscaTodosOrcamentos(){
        return ResponseEntity.ok(orcamentoService.buscarCadastro());
    }
    @GetMapping(path="/pesquisaid/{id}")
    public ResponseEntity<OrcamentoModel>buscaPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(orcamentoService.buscaId(id));
    }

    @PostMapping(path="/put/{id}")
    public ResponseEntity<OrcamentoModel>atualizaOrcamento(@RequestBody OrcamentoModel orcamentoModel, @PathVariable Long id){
        OrcamentoModel orcamentoNewObj= orcamentoService.atualizaCadastro(orcamentoModel, id);
        return ResponseEntity.ok().body(orcamentoNewObj);
    }
    @DeleteMapping(path="/delete/{id}")
    public void deleteOrcamento(@PathVariable Long id){
        orcamentoService.deletaOrcamento(id);
    }

    @PostMapping
    public String cadastraOrcamento(@RequestParam(required = false) Long idUsuario,
                                   @RequestParam(required = false) Long idCliente,
                                   @ModelAttribute OrcamentoModel orcamentoModel) {
        try {
            // Validação: Cliente é obrigatório
            if (idCliente == null) {
                return "redirect:/orcamentos?error=true";
            }
            
            // Validação: Valor é obrigatório
            if (orcamentoModel.getValorOrcamento() == null || orcamentoModel.getValorOrcamento().doubleValue() <= 0) {
                return "redirect:/orcamentos?error=true";
            }
            
            // Validação: Estado é obrigatório
            if (orcamentoModel.getIcmsEstados() == null) {
                return "redirect:/orcamentos?error=true";
            }
            
            if (idUsuario != null) {
                UsuarioModel usuario = usuarioService.buscaId(idUsuario);
                orcamentoModel.setUsuario(usuario);
            }
            
            ClienteModel cliente = clienteService.buscaId(idCliente);
            orcamentoModel.setCliente(cliente);
            
            orcamentoModel.calcularIcms();
            
            orcamentoService.cadastrarOrcamento(orcamentoModel);
            
            return "redirect:/orcamentos?success=true";
        } catch (Exception e) {
            System.err.println("ERRO ao cadastrar orçamento: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/orcamentos?error=true";
        }
    }

    @GetMapping("/clientes/search")
    @ResponseBody
    public List<ClienteModel> searchClientes(@RequestParam String searchTerm) {
        // Busca por nome ou CPF
        List<ClienteModel> porNome = clienteService.buscarPorNome(searchTerm);
        List<ClienteModel> porCpf = clienteService.buscarPorCpf(searchTerm);
        
        // Combina resultados e remove duplicatas
        porNome.addAll(porCpf);
        return porNome.stream().distinct().toList();
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }

    @GetMapping("/database")
    public String databasePage() {
        return "database";
    }

}
