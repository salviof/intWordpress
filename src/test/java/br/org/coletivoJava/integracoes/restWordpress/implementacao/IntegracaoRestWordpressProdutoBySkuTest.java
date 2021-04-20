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
public class IntegracaoRestWordpressProdutoBySkuTest extends TestesApiRest {

    @Before
    public void setUp() {
        SBCore.configurar(new ConfigCoreWordpressTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    @Test
    public void testSomeMethod() {

        IntegracaoRestWordpressProdutoBySku integracao = (IntegracaoRestWordpressProdutoBySku) FabApiRestWordpressProduto.PRODUTO_BY_SKU.getAcao("123");
        ItfResposta resposata = integracao.getResposta();
        String produtostrJson = resposata.getRetorno().toString();
        ProdutoWoocomerceDTO produtoDTO = UtilDTOProdutoWocomerce.getProdutoDTO(produtostrJson);

        System.out.println("id: " + produtoDTO.getId());
        System.out.println("nome: " + produtoDTO.getNome());
        System.out.println("SKU: " + produtoDTO.getSKU());
        System.out.println("PRCO: " + produtoDTO.getPreco());
        System.out.println("Quantidade: " + produtoDTO.getEstoqueQuantidade());
        System.out.println("___-___");

        Assert.assertNotNull("Produto não encontrado", produtoDTO);

    }

    public IntegracaoRestWordpressProdutoBySkuTest() {
    }
}
