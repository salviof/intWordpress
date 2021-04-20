/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress.api.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import java.io.IOException;
import java.util.Iterator;

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
        JsonNode node = oc.readTree(jp);

        for (Iterator iterator = node.fieldNames(); iterator.hasNext();) {
            String campo = (String) iterator.next();
            System.out.println(campo);

        }
        produto.setSKU(node.get("sku").asText());
        produto.setId(node.get("id").intValue());
        produto.setPreco(node.get("price").asDouble());
        produto.setNome(node.get("name").asText());
        produto.setEstoqueQuantidade(node.get("stock_quantity").asInt());

        return produto;

    }

}
