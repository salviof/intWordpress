/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress.api.produto;

import br.org.coletivoJava.integracoes.restWordpress.api.FabConfigWordpressApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.importacao.FabTipoArquivoImportacao;

/**
 *
 * @author sfurbino
 */
@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://woocommerce.github.io/woocommerce-rest-api-docs/v3.html#introduction",
        tipoAutenticacao = FabTipoAutenticacaoRest.USUARIO_SENHA_SIMPLES,
        nomeIntegracao = FabConfigWordpressApi.NOME_APLICACAO,
        configuracao = FabConfigWordpressApi.class
)
public enum FabApiRestWordpressProduto implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/wp-json/wc/v3/products",
            tipoConexao = FabTipoConexaoRest.GET,
            urlDocumentacao = "https://woocommerce.github.io/woocommerce-rest-api-docs/v3.html#view-list-of-products")
    PRODUTO_BY_SKU,
    @InfoConsumoRestService(getPachServico = "/wp-json/wc/v3/products",
            tipoConexao = FabTipoConexaoRest.GET,
            urlDocumentacao = "https://woocommerce.github.io/woocommerce-rest-api-docs/v3.html#view-list-of-products")
    PRODUTO_LISTAGEM_PAGINACAO,
    @InfoConsumoRestService(getPachServico = "/wp-json/wc/v3/products/<id>",
            tipoConexao = FabTipoConexaoRest.PUT,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            parametrosGet = "id",
            parametrosPost = {"price", "stock_quantity"},
            urlDocumentacao = "https://documenter.getpostman.com/view/9632769/SWLiamS3#b4b49c87-4cec-483b-9f36-afd2b078f806"
    )
    PRODUTO_ATUALIZAR_PRECO_ESTOQUE,

}
