/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 *
 * @author sfurbino
 */
@JsonDeserialize(using = AdaptadorProdutoWoocomerceJsonToDTO.class)
public class ProdutoWoocomerceDTO {

    private int id;
    private String SKU;
    private String nome;
    private String descricao;
    private int estoqueQuantidade;
    private double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoqueQuantidade() {
        return estoqueQuantidade;
    }

    public void setEstoqueQuantidade(int estoqueQuantidade) {
        this.estoqueQuantidade = estoqueQuantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
