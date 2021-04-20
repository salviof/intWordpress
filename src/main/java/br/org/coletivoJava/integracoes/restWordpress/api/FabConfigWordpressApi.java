/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress.api;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.tipoModulos.integracaoOauth.FabPropriedadeModuloIntegracaoOauth;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.tipoModulos.integracaoOauth.InfoPropriedadeConfigRestIntegracao;

/**
 *
 * @author sfurbino
 * @since 07/12/2019
 * @version 1.0
 */
public enum FabConfigWordpressApi implements ItfFabConfigModulo {

    @InfoPropriedadeConfigRestIntegracao(tipoPropriedade = FabPropriedadeModuloIntegracaoOauth.URL_SERVIDOR_API)
    URLWORDPRESS,
    @InfoPropriedadeConfigRestIntegracao(tipoPropriedade = FabPropriedadeModuloIntegracaoOauth.CHAVE_PUBLICA)
    CHAVE_DE_ACESSO_PUBLICA,
    @InfoPropriedadeConfigRestIntegracao(tipoPropriedade = FabPropriedadeModuloIntegracaoOauth.CHAVE_PRIVADA)
    CHAVE_DE_ACESSO_PRIVADA;

    public static final String NOME_APLICACAO = "Wordpress";

    @Override
    public String getValorPadrao() {
        switch (this) {
            case URLWORDPRESS:
                return "https://localhost:8666";
            case CHAVE_DE_ACESSO_PUBLICA:
                return "CONFIGURE_SUA_CHAVE_PUBLICA";
            case CHAVE_DE_ACESSO_PRIVADA:
                return "CONFIGURE_SUA_CHAVE_PRIVADA";

            default:
                throw new AssertionError(this.name());

        }

    }

}
