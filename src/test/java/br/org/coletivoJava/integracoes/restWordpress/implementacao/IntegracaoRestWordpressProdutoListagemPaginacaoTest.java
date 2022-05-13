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
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import testes.testesSupers.TestesApiRest;

/**
 *
 * @author sfurbino
 */
public class IntegracaoRestWordpressProdutoListagemPaginacaoTest extends TestesApiRest {

    @Before
    public void setUp() {
        SBCore.configurar(new ConfigCoreWordpressTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    @Test
    public void testSomeMethod() {
        int quantidade = 1;
        int pagina = 1;
        List<ProdutoWoocomerceDTO> produtoWoocomerce = new ArrayList();
        while (quantidade > 0) {
            IntegracaoRestWordpressProdutoListagemPaginacao integracao = (IntegracaoRestWordpressProdutoListagemPaginacao) FabApiRestWordpressProduto.PRODUTO_LISTAGEM_PAGINACAO.getAcao(pagina);
            ItfResposta resposata = integracao.getResposta();
            String produto = resposata.getRetorno().toString();

            List<ProdutoWoocomerceDTO> produtos = UtilDTOProdutoWocomerce.getProdutosDTO(produto);

            if (produtos != null) {
                quantidade = produtos.size();
                produtoWoocomerce.addAll(produtos);
                System.out.println("_________________PRODUTOS__________");
                for (ProdutoWoocomerceDTO produtoBean : produtos) {
                    System.out.println("id: " + produtoBean.getId());
                    System.out.println("nome: " + produtoBean.getNome());
                    System.out.println("SKU: " + produtoBean.getSKU());
                    System.out.println("PRCO: " + produtoBean.getPreco());
                    System.out.println("Quantidade: " + produtoBean.getEstoqueQuantidade());
                    System.out.println("___-___");
                }
            } else {
                quantidade = 0;
            }

            pagina++;
        }

        System.out.println("Foram importado " + produtoWoocomerce.size() + " produtos");

        System.out.println("_________________fim__________");
        //tilDTOIntWordpress.getProdutosByJson(produto);

    }

    public IntegracaoRestWordpressProdutoListagemPaginacaoTest() {
    }

}
