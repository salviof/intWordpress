/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress.api.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 *
 * @author sfurbino
 */
public class AdaptadorProdutoWoocomerceDTOToJson extends StdSerializer<ProdutoWoocomerceDTO> {

    public AdaptadorProdutoWoocomerceDTOToJson() {
        this(null);
    }

    public AdaptadorProdutoWoocomerceDTOToJson(Class<ProdutoWoocomerceDTO> t) {
        super(t);
    }

    @Override
    public void serialize(ProdutoWoocomerceDTO t, JsonGenerator jg, SerializerProvider sp) throws IOException {
        jg.writeStartObject();
        jg.writeStringField("SKU", t.getSKU());
        jg.writeEndObject();
    }

}
