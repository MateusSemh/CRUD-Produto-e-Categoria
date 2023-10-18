package com.example.aula3;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import com.example.aula3.models.Categoria;
import com.example.aula3.models.Produto;
import com.example.aula3.services.CategoriaService;
import com.example.aula3.services.ProdutoService;

public class MinhaAplicacao {

    @Autowired
    private static ProdutoService produtoService;

    @Autowired
    private static CategoriaService categoriaService;

    public static void main(String[] args) {
        SpringApplication.run(MinhaAplicacao.class, args);

        // Criando alguns produtos
        Produto produto1 = new Produto();
        produto1.setNome("Produto 1");
        produto1.setPreco(100.0);

        Produto produto2 = new Produto();
        produto2.setNome("Produto 2");
        produto2.setPreco(150.0);

        // Salvando os produtos
        produtoService.createProduto(produto1);
        produtoService.createProduto(produto2);

        // Obtendo todos os produtos e imprimindo no console
        System.out.println("Todos os produtos:");
        produtoService.getAllProdutos().forEach(System.out::println);

        // Criando uma categoria
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        // Associando produtos à categoria
        produto1.setCategoria(categoria);
        produto2.setCategoria(categoria);
        categoria.setProdutos(Arrays.asList(produto1, produto2));

        // Salvando a categoria (os produtos serão salvos automaticamente devido à cascata)
        categoriaService.createCategoria(categoria);

        // Obtendo a categoria com produtos e imprimindo no console
        System.out.println("\nCategoria com produtos:");
        Categoria categoriaComProdutos = categoriaService.getCategoriaById(categoria.getId()).orElse(null);
        if (categoriaComProdutos != null) {
            System.out.println("Categoria: " + categoriaComProdutos.getNome());
            System.out.println("Produtos da categoria:");
            categoriaComProdutos.getProdutos().forEach(prod -> System.out.println(prod.getNome()));
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }
}
