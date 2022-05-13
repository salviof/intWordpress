/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress.implementacao;

import br.org.coletivoJava.integracoes.restWordpress.api.dto.ProdutoWoocomerceDTO;
import br.org.coletivoJava.integracoes.restWordpress.api.dto.UtilDTOProdutoWocomerce;
import br.org.coletivoJava.integracoes.restWordpress.api.produto.FabApiRestWordpressProduto;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreNumerosOperacoes;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreWordpressTestesRegraNegocio;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import testes.testesSupers.TestesApiRest;

/**
 *
 * @author sfurbino
 */
public class IntegracaoRestWordpressProdutoAtualizarPrecoEstoqueTest extends TestesApiRest {

    @Before
    public void setUp() {
        SBCore.configurar(new ConfigCoreWordpressTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    @Test
    public void _1precoRecular() {
        // TODO review the generated test code and remove the default call to fail.

        ProdutoWoocomerceDTO produtoDTOBySku = UtilDTOProdutoWocomerce.getProdutoDTOPrimeiroDaLista(FabApiRestWordpressProduto.PRODUTO_BY_SKU.getAcao("123").getResposta().getRetorno().toString());
        int id = produtoDTOBySku.getId();
        int estoque = 66;
        boolean estoqueSobConsulta = false;
        boolean consideradoDEsconto = false;
        double valorPromocional = 0;
        double valorREgular = 15.8;
        IntegracaoRestWordpressProdutoAtualizarPrecoEstoque integracaoAtualizacao = (IntegracaoRestWordpressProdutoAtualizarPrecoEstoque) FabApiRestWordpressProduto.PRODUTO_ATUALIZAR_PRECO_ESTOQUE
                .getAcao(id, valorREgular, valorPromocional, consideradoDEsconto, estoque, estoqueSobConsulta);
        ItfResposta resposata = integracaoAtualizacao.getResposta();
        Assert.assertTrue("REsposta falhou", resposata.isSucesso());
        String produtostrJson = resposata.getRetorno().toString();
        ProdutoWoocomerceDTO produtoAtualizadoDTO = UtilDTOProdutoWocomerce.getProdutoDTOREgistroUnico(produtostrJson);
        Assert.assertNotNull("Produto não encontrado", produtoAtualizadoDTO);
        Assert.assertEquals("O estoque não parece ", estoque, produtoAtualizadoDTO.getEstoqueQuantidade());
        Assert.assertTrue("O valor não foi atualizado ", UtilSBCoreNumerosOperacoes.compararDouble(valorREgular, produtoAtualizadoDTO.getPreco()));

    }

    @Test
    public void _2precoPromocional() {
        ProdutoWoocomerceDTO produtoDTOBySku = UtilDTOProdutoWocomerce.getProdutoDTOPrimeiroDaLista(FabApiRestWordpressProduto.PRODUTO_BY_SKU.getAcao("123").getResposta().getRetorno().toString());
        int id = produtoDTOBySku.getId();
        int estoque = 30;
        boolean estoqueSobConsulta = false;
        boolean consideradoDEsconto = false;
        double valorPromocional = 10.35;
        double valorREgular = 15.7;
        IntegracaoRestWordpressProdutoAtualizarPrecoEstoque integracaoAtualizacao = (IntegracaoRestWordpressProdutoAtualizarPrecoEstoque) FabApiRestWordpressProduto.PRODUTO_ATUALIZAR_PRECO_ESTOQUE
                .getAcao(id, valorREgular, valorPromocional, consideradoDEsconto, estoque, estoqueSobConsulta);
        ItfResposta resposata = integracaoAtualizacao.getResposta();
        Assert.assertTrue("REsposta falhou", resposata.isSucesso());
        String produtostrJson = resposata.getRetorno().toString();
        ProdutoWoocomerceDTO produtoAtualizadoDTO = UtilDTOProdutoWocomerce.getProdutoDTOREgistroUnico(produtostrJson);
        Assert.assertNotNull("Produto não encontrado", produtoAtualizadoDTO);
        Assert.assertEquals("O estoque não parece ", estoque, produtoAtualizadoDTO.getEstoqueQuantidade());
    }

    @Test
    public void _3estpqueSobConsulta() {

    }

}
