package br.org.coletivoJava.integracoes.restWordpress.implementacao;

import br.org.coletivoJava.integracoes.restWordpress.api.FabConfigWordpressApi;
import br.org.coletivoJava.integracoes.restWordpress.api.InfoIntegracaoRestWordpressProduto;
import br.org.coletivoJava.integracoes.restWordpress.api.produto.FabApiRestWordpressProduto;
import br.org.coletivoJava.integracoes.restWordpress.implementacao.oauth.UtilIntegradorWordpress;
import com.icoderman.woocommerce.HttpMethod;
import com.icoderman.woocommerce.oauth.OAuthConfig;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;
import java.util.HashMap;
import java.util.Map;

@InfoIntegracaoRestWordpressProduto(tipo = FabApiRestWordpressProduto.PRODUTO_BY_SKU)
public class IntegracaoRestWordpressProdutoBySku
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestWordpressProdutoBySku(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ComoUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestWordpressProduto.PRODUTO_BY_SKU, pTipoAgente, pUsuario,
                pParametro);
    }

    @Override
    public String gerarUrlRequisicao() {
        String urlRequisicao = super.gerarUrlRequisicao();
        if (getParametros().length != 1) {
            throw new UnsupportedOperationException("Este método pede um parametro: SKU, que é obrigatório");
        }

        String chavePublica = getTokenGestao().getTokenCompleto().getComoTokenChavePublicaPrivada().getChavePublica();
        String chavePrivada = getTokenGestao().getTokenCompleto().getComoTokenChavePublicaPrivada().getChavePrivada();
        String urlServidor = getConfiguracao().getPropriedade(FabConfigWordpressApi.URLWORDPRESS);

        OAuthConfig config = new OAuthConfig(urlServidor, chavePublica,
                chavePrivada);
        Map<String, String> parametros = new HashMap();
        parametros.put("sku", getParametros()[0].toString());

        String assinaturaRequisicao = UtilIntegradorWordpress.getAsQueryString(config, urlRequisicao, HttpMethod.GET, parametros);
        String url = UtilIntegradorWordpress.buildUrlRequisicao(urlRequisicao, assinaturaRequisicao);
        return url;
    }

    @Override
    public String gerarCorpoRequisicao() {
        return super.gerarCorpoRequisicao(); //To change body of generated methods, choose Tools | Templates.
    }

}
