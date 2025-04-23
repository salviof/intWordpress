/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress.implementacao;

import br.org.coletivoJava.integracoes.restWordpress.api.dto.ProdutoNovoWoocomerceJsonToDTO;
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
        try {
            int quantidade = 1;
            int pagina = 1;
            List<ProdutoWoocomerceDTO> produtoWoocomerce = new ArrayList();
            while (quantidade > 0) {
                IntegracaoRestWordpressProdutoListagemPaginacao integracao = (IntegracaoRestWordpressProdutoListagemPaginacao) FabApiRestWordpressProduto.PRODUTO_LISTAGEM_PAGINACAO.getAcao(pagina);
                ItfResposta resposata = integracao.getResposta();
                if (!resposata.isSucesso()) {
                    throw new UnsupportedOperationException("Erro comunicando com Wordpress" + resposata.getRetorno());
                }

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
        } catch (Throwable t) {
            throw new UnsupportedOperationException("Erro obtendo produtos do wordpress" + t.getMessage());

        }
    }

    public static ProdutoWoocomerceDTO getProdutoBySKU(String pSku) {
        try {
            ProdutoWoocomerceDTO produtoDTOBySku = UtilDTOProdutoWocomerce.getProdutoDTOPrimeiroDaLista(FabApiRestWordpressProduto.PRODUTO_BY_SKU.getAcao(pSku).getResposta().getRetorno().toString());
            return produtoDTOBySku;
        } catch (Throwable t) {
            throw new UnsupportedOperationException("Erro obtendo produto do wordpress" + t.getMessage());
        }

    }

    public static ProdutoWoocomerceDTO atualizarProduto(int pSku, double pValoRegular, double pvalorProocional, int pEstoque, boolean pConsideradoDesconto, boolean pEstoqueSobConsulta) {
        try {

            ProdutoWoocomerceDTO produtoDTOBySku = UtilDTOProdutoWocomerce.getProdutoDTOPrimeiroDaLista(FabApiRestWordpressProduto.PRODUTO_BY_SKU.getAcao(String.valueOf(pSku)).getResposta().getRetorno().toString());
            Long idProduto = produtoDTOBySku.getId();
            ItfResposta resposta = FabApiRestWordpressProduto.PRODUTO_ATUALIZAR_PRECO_ESTOQUE.getAcao(idProduto, pValoRegular, pvalorProocional, pConsideradoDesconto, pEstoque, pEstoqueSobConsulta).getResposta();
            if (!resposta.isSucesso()) {
                throw new UnsupportedOperationException("Falha Atualizando produto");
            }
            String produtostrJson = resposta.getRetorno().toString();
            ProdutoWoocomerceDTO produtoAtualizadoDTO = UtilDTOProdutoWocomerce.getProdutoDTOREgistroUnico(produtostrJson);
            return produtoAtualizadoDTO;
        } catch (Throwable t) {
            throw new UnsupportedOperationException("Erro atualizando produto do wordpress" + t.getMessage());
        }
    }

}
