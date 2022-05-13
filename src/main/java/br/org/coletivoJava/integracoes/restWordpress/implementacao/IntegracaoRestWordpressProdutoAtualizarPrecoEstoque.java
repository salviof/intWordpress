package br.org.coletivoJava.integracoes.restWordpress.implementacao;

import br.org.coletivoJava.integracoes.restWordpress.api.InfoIntegracaoRestWordpressProduto;
import br.org.coletivoJava.integracoes.restWordpress.api.dto.ProdutoNovoWoocomerceJsonToDTO;
import br.org.coletivoJava.integracoes.restWordpress.api.produto.FabApiRestWordpressProduto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@InfoIntegracaoRestWordpressProduto(tipo = FabApiRestWordpressProduto.PRODUTO_ATUALIZAR_PRECO_ESTOQUE)
public class IntegracaoRestWordpressProdutoAtualizarPrecoEstoque
        extends
        AcaoApiIntegracaoAbstrato {

    public enum TIPOS_GESTAOESTOQUE {
        PERMITIR_ENCOMENDA,
        NAO_PERMITIR_ENCOMENDA;

        @Override
        public String toString() {
            switch (this) {
                case PERMITIR_ENCOMENDA:
                    return "notify";
                case NAO_PERMITIR_ENCOMENDA:
                    return "no";

                default:
                    throw new AssertionError(this.name());

            }

        }
    }

    public IntegracaoRestWordpressProdutoAtualizarPrecoEstoque(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestWordpressProduto.PRODUTO_ATUALIZAR_PRECO_ESTOQUE,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarUrlRequisicao() {
        String urlRequisicao = super.gerarUrlRequisicao();
        int quantidadeParametrosObrigatorios = 6;
        if (getParametros().length != quantidadeParametrosObrigatorios) {
            throw new UnsupportedOperationException("Este método pede " + quantidadeParametrosObrigatorios + " parametros");
        }

        return urlRequisicao;
    }

    @Override
    public String gerarCorpoRequisicao() {

        double precoRegular = (double) parametros[1];
        double precopromocional = (double) parametros[2];
        boolean temPrecoPromocional = (boolean) parametros[3];

        int estoque = (int) parametros[4];
        boolean estoqSobEncomenda = (boolean) parametros[5];
        boolean emEstoque = true;
        if (!estoqSobEncomenda) {
            if (estoque == 0) {
                emEstoque = false;
            }
        }

        ProdutoNovoWoocomerceJsonToDTO novoPoduto = new ProdutoNovoWoocomerceJsonToDTO();
        DecimalFormat df2 = new DecimalFormat("#.##");
        df2.setRoundingMode(RoundingMode.DOWN);
        if (!temPrecoPromocional) {
            novoPoduto.setRegular_price(df2.format(precoRegular));
            novoPoduto.setSale_price(null);
        } else {
            novoPoduto.setRegular_price(df2.format(precoRegular));
            novoPoduto.setSale_price(df2.format(precopromocional));
        }

        if (estoqSobEncomenda) {
            novoPoduto.setBackorders(TIPOS_GESTAOESTOQUE.PERMITIR_ENCOMENDA.toString());
            novoPoduto.setBackorders_allowed(true);
        } else {
            novoPoduto.setBackorders(TIPOS_GESTAOESTOQUE.NAO_PERMITIR_ENCOMENDA.toString());
            novoPoduto.setBackorders_allowed(false);

        }
        novoPoduto.setStock_quantity(estoque);
        novoPoduto.setIn_stock(emEstoque);

        ObjectMapper mapper = new ObjectMapper();
        String json;

        try {
            json = mapper.writeValueAsString(novoPoduto);
            return json;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(IntegracaoRestWordpressProdutoAtualizarPrecoEstoque.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
