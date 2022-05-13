package br.org.coletivoJava.integracoes.restWordpress.api;

import javax.inject.Qualifier;
import br.org.coletivoJava.integracoes.restWordpress.api.produto.FabApiRestWordpressProduto;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InfoIntegracaoRestWordpressProduto {

	FabApiRestWordpressProduto tipo();
}