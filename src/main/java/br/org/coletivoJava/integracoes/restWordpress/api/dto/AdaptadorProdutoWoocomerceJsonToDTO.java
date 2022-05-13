/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress.api.dto;

import br.org.coletivoJava.integracoes.restWordpress.implementacao.IntegracaoRestWordpressProdutoAtualizarPrecoEstoque;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

/**
 *
 * @author sfurbino
 */
public class AdaptadorProdutoWoocomerceJsonToDTO extends StdDeserializer<ProdutoWoocomerceDTO> {

    public AdaptadorProdutoWoocomerceJsonToDTO() {
        this(null);
    }

    public AdaptadorProdutoWoocomerceJsonToDTO(Class<ProdutoWoocomerceDTO> t) {
        super(t);
    }

    @Override
    public ProdutoWoocomerceDTO deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        ProdutoWoocomerceDTO produto = new ProdutoWoocomerceDTO();
        ObjectCodec oc = jp.getCodec();
        ObjectNode node = oc.readTree(jp);
        produto.setSKU(node.get("sku").asText());
        produto.setId(node.get("id").intValue());
        double precoJsonWordpress = node.get("price").asDouble();
        produto.setPreco(precoJsonWordpress);
        produto.setNome(node.get("name").asText());
        produto.setEstoqueQuantidade(node.get("stock_quantity").asInt());
        produto.setPrecoPromocional(node.get("sale_price").asDouble());
        boolean naoPermiteEcomendas = node.get("backorders").asText().equals("no");
        if (naoPermiteEcomendas) {
            produto.setPermitidoEstoqueSobConsulta(false);
        } else {
            boolean tipoEstrategiaNOtificar = node.get("backorders").asText().equals(IntegracaoRestWordpressProdutoAtualizarPrecoEstoque.TIPOS_GESTAOESTOQUE.PERMITIR_ENCOMENDA.toString());
            produto.setPermitidoEstoqueSobConsulta(tipoEstrategiaNOtificar);
        }
        node.remove("description");
        node.remove("meta_data");
        node.remove("yoast_head");
        node.remove("short_description");

        ArrayNode imagens = (ArrayNode) node.get("images");

        if (imagens != null && imagens.size() > 0) {
            String urlFoto = imagens.get(0).get("src").asText();
            produto.setUrlFoto(urlFoto);
        }
        node.remove("images");

        produto.setJsonCompleto(node.toString());

        return produto;

    }

}
