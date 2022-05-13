/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress.api.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sfurbino
 */
public class UtilDTOProdutoWocomerce {

    public static ProdutoWoocomerceDTO getProdutoDTOPrimeiroDaLista(String pJson) {

        return getProdutosDTO(pJson).get(0);
    }

    public static ProdutoWoocomerceDTO getProdutoDTOREgistroUnico(String pJson) {

        try {
            ProdutoWoocomerceDTO produto = new ObjectMapper().readValue(pJson, ProdutoWoocomerceDTO.class);
            return produto;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UtilDTOProdutoWocomerce.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static List<ProdutoWoocomerceDTO> getProdutosDTO(String pJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module
                    = new SimpleModule("AdaptadorProdutoWoocomerceJsonToDTO", new Version(1, 0, 0, null, null, null));
            module.addDeserializer(ProdutoWoocomerceDTO.class, new AdaptadorProdutoWoocomerceJsonToDTO());
            JavaType customClassCollection = objectMapper.getTypeFactory().constructCollectionType(List.class, ProdutoWoocomerceDTO.class);

            List<ProdutoWoocomerceDTO> listCar = (List<ProdutoWoocomerceDTO>) objectMapper.readValue(pJson, customClassCollection);

            return listCar;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UtilDTOProdutoWocomerce.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
