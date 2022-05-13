package br.org.coletivoJava.integracoes.restWordpress.implementacao;

import br.org.coletivoJava.integracoes.restWordpress.api.FabConfigWordpressApi;
import br.org.coletivoJava.integracoes.restWordpress.api.InfoIntegracaoRestWordpressProduto;
import br.org.coletivoJava.integracoes.restWordpress.api.produto.FabApiRestWordpressProduto;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenChaveUnica;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.TokenDeAcessoExternoChavePublicaPrivada;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestWordpressProduto(tipo = FabApiRestWordpressProduto.PRODUTO_BY_SKU)
public class GestaoTokenRestWordpress extends GestaoTokenChaveUnica {

    private static String assinaturabaseSHA256;

    @Override
    public boolean validarToken() {
        return false;
    }

    @Override
    public ItfTokenDeAcessoExterno loadTokenArmazenado() {
        String chavePublica = getConfig().getPropriedade(FabConfigWordpressApi.CHAVE_DE_ACESSO_PUBLICA);
        String chavePrivada = getConfig().getPropriedade(FabConfigWordpressApi.CHAVE_DE_ACESSO_PRIVADA);
        String urlServidor = getConfig().getPropriedade(FabConfigWordpressApi.URLWORDPRESS);

        TokenDeAcessoExternoChavePublicaPrivada token = new TokenDeAcessoExternoChavePublicaPrivada(chavePublica, chavePrivada);

        return token;
    }

    public GestaoTokenRestWordpress(final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario) {
        super(FabApiRestWordpressProduto.class, pTipoAgente, pUsuario);
    }

}
