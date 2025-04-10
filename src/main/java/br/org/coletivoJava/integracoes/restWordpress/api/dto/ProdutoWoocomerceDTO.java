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

    private Long id;
    private String SKU;
    private String nome;
    private String urlFoto;
    private String descricao;
    private int estoqueQuantidade;
    private double preco;
    private double precoPromocional;
    private String jsonCompleto;
    private boolean permitidoEstoqueSobConsulta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getJsonCompleto() {
        return jsonCompleto;
    }

    public void setJsonCompleto(String jsonCompleto) {
        this.jsonCompleto = jsonCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public boolean isPermitidoEstoqueSobConsulta() {
        return permitidoEstoqueSobConsulta;
    }

    public void setPermitidoEstoqueSobConsulta(boolean permitidoEstoqueSobConsulta) {
        this.permitidoEstoqueSobConsulta = permitidoEstoqueSobConsulta;
    }

    public double getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(double precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

}
