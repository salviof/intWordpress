package br.org.coletivoJava.integracoes.restWordpress.implementacao;

import br.org.coletivoJava.integracoes.restWordpress.api.InfoIntegracaoRestWordpressProduto;
import br.org.coletivoJava.integracoes.restWordpress.api.produto.FabApiRestWordpressProduto;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestWordpressProduto(tipo = FabApiRestWordpressProduto.PRODUTO_ATUALIZAR_PRECO_ESTOQUE)
public class IntegracaoRestWordpressProdutoAtualizarPrecoEstoque
		extends
			AcaoApiIntegracaoAbstrato {

	public IntegracaoRestWordpressProdutoAtualizarPrecoEstoque(
			final FabTipoAgenteClienteApi pTipoAgente,
			final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
		super(FabApiRestWordpressProduto.PRODUTO_ATUALIZAR_PRECO_ESTOQUE,
				pTipoAgente, pUsuario, pParametro);
	}
}