package br.org.coletivoJava.integracoes.restWordpress.implementacao;

import br.org.coletivoJava.integracoes.restWordpress.api.FabConfigWordpressApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoHeaderBuilder;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class IntegracaoRestWordpress_HeaderPadrao
        extends
        AcaoApiIntegracaoHeaderBuilder {

    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String UTF_8 = "UTF-8";

    public IntegracaoRestWordpress_HeaderPadrao(final ItfAcaoApiRest pAcao) {
        super(pAcao);
    }

    @Override
    public void buildHeaderPadrao() {
        super.buildHeaderPadrao(); //To change body of generated methods, choose Tools | Templates.
        String chavePublica = acao.getTokenGestao().getTokenCompleto().getComoTokenChavePublicaPrivada().getChavePublica();
        String chavePrivada = acao.getTokenGestao().getTokenCompleto().getComoTokenChavePublicaPrivada().getChavePrivada();
        String urlServidor = acao.getConfiguracao().getPropriedade(FabConfigWordpressApi.URLWORDPRESS);
        System.out.println("Conectando com " + urlServidor);

        if (urlServidor.startsWith("https://")) {
            //cabecalho.put("consumerKey", chavePublica);
            //cabecalho.put("consumerSecret", chavePrivada);
            //cabecalho.put("version", "wc/v3");

            String stringUsuarioSenha = chavePublica + ":" + chavePrivada;
            byte[] encodedAuth = Base64.encodeBase64(stringUsuarioSenha.getBytes(StandardCharsets.UTF_8));
            String base64Credentials = new String(java.util.Base64.getEncoder().encode(stringUsuarioSenha.getBytes()));
            String authorizationHeader = "Basic " + base64Credentials;
            String hmacGerada = null;
            try {
                Mac macInstance = Mac.getInstance(HMAC_SHA256);

                SecretKeySpec secretKey = new SecretKeySpec(chavePrivada.getBytes(UTF_8), HMAC_SHA256);
                macInstance.init(secretKey);
                hmacGerada = Base64.encodeBase64String(macInstance.doFinal(stringUsuarioSenha.getBytes(UTF_8)));
            } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            String authHeaderValue = "Basic " + new String(encodedAuth);
            byte[] message;
            try {
                message = (chavePublica + ":" + chavePrivada).getBytes("UTF-8");
                String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);

                cabecalho.put("Authorization", "Basic " + encoded);
                // cabecalho.put("Content-Type", "application/json");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(IntegracaoRestWordpress_HeaderPadrao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
