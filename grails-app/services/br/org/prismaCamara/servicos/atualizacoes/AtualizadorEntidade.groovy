package br.org.prismaCamara.servicos.atualizacoes

import grails.plugins.rest.client.RestBuilder
import groovy.text.SimpleTemplateEngine
import groovy.util.slurpersupport.GPathResult
import hackathon2013.Parametro

import org.springframework.web.util.UriUtils

abstract class AtualizadorEntidade {
	
	abstract String getSiglaDeParametro();
	abstract def atualizar();
	
	/**
	 * Recuperar a URL de atualização da entidade sem usar parâmetros para compo-la
	 * @return A String com a URL e seus parâmetros preenchidos, se for o caso
	 */
	String getUrlAtualizacao() {
		getUrlAtualizacao(null)
	}
	
	/**
	 * Recuperar a URL de atualização da entidade
	 * @param parametrosValores {@link Map} com os parâmetros a serem substituidos (opcional)
	 * @return A String com a URL e seus parâmetros preenchidos, se for o caso
	 */
	String getUrlAtualizacao(parametrosValores) {
		def texto = Parametro.findBySigla(getSiglaDeParametro()).valor
		
		Writable template = new SimpleTemplateEngine().createTemplate(texto).make(parametrosValores)
		
		return template.toString();
	}

	
	/**
	 * Constroi um {@link GPathResult} a partir de uma String com o conteúdo de um XML
	 * @param texto Conteúdo do XML
	 * @return
	 */
	protected GPathResult getXMLDeTexto(String texto) {
		return new XmlSlurper().parseText(texto)
	}

	/**
	 * Constroi um {@link GPathResult} a partir de um conteúdo obtido de uma URL
	 * @param texto URL a ser acessada
	 * @return
	 */
	protected GPathResult getXML(String url) {
		getXML(url, null)
	}
	
	/**
	 * Constroi um {@link GPathResult} a partir de um conteúdo obtido de uma URL
	 * @param texto URL a ser acessada
	 * @param parametros Parâmetros a serem passados junto a URL
	 * @return
	 */
	protected GPathResult getXML(String url, Map parametros) {
		try {
			url = UriUtils.encodeQuery(url, 'UTF-8')
			def xml = new XmlSlurper().parseText(url.toURL().text)
			return xml 
		} catch(e) {
			def msg = "Não foi possível recuperar o XML da resposta para ${url}: ${e.message}"
			log.error(msg)
			throw new Exception(msg)
		}
		
	}
}
