package br.com.atos.controle_produtos.controller;

import br.com.atos.controle_produtos.model.Produto;
import br.com.atos.controle_produtos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "listar";
    }

    @GetMapping("/produtos/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "formulario";
    }

    @PostMapping("/produtos/salvar")
    public String salvarProduto(@Valid Produto produto,
                                BindingResult result) {

        if (result.hasErrors()) {
            return "formulario";
        }

        produtoService.salvar(produto);
        return "redirect:/produtos";
    }
}