/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restWordpress;

import br.org.coletivoJava.integracoes.restWordpress.api.FabConfigWordpressApi;
import br.org.coletivoJava.integracoes.restWordpress.api.produto.FabApiRestWordpressProduto;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreWordpressTestesRegraNegocio;
import org.junit.Test;
import testes.testesSupers.TestesApiRest;

/**
 *
 * @author sfurbino
 */
public class TesteConformidade extends TestesApiRest {

    @Test
    public void testes() {
        SBCore.configurar(new ConfigCoreWordpressTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        gerarCodigosChamadasEndpoint(FabApiRestWordpressProduto.class);
        ConfigModulo configuracaoWP = SBCore.getConfigModulo(FabConfigWordpressApi.class);
        configuracaoWP.getPropriedade(FabConfigWordpressApi.CHAVE_DE_ACESSO_PUBLICA);

//        RespostaWebServiceSimples resposta = FabApiRestRokcetChatV1Users.DIRECT_MENSAGENS_CONTADORES.getAcao().getResposta();
        //      System.out.println(resposta);
        //     System.out.println(resposta.getRespostaTexto());
    }
}
