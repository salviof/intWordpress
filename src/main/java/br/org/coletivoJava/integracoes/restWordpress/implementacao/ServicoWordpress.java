/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress.implementacao;

import br.org.coletivoJava.integracoes.restWordpress.api.dto.ProdutoWoocomerceDTO;
import br.org.coletivoJava.integracoes.restWordpress.api.dto.UtilDTOProdutoWocomerce;
import br.org.coletivoJava.integracoes.restWordpress.api.produto.FabApiRestWordpressProduto;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfResposta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sfurbino
 */
public class ServicoWordpress {

    public static List<ProdutoWoocomerceDTO> getProdutoTodos() {
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
            } else {
                quantidade = 0;
            }
            pagina++;
        }
        return produtoWoocomerce;
    }

    public static ProdutoWoocomerceDTO atualizarProduto(ProdutoWoocomerceDTO pProduto) {
        return null;
    }

}
