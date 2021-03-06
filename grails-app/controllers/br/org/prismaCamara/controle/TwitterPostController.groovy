/*
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU Affero General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU Affero General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 */
package br.org.prismaCamara.controle

import grails.plugins.springsecurity.Secured

import org.springframework.social.twitter.api.Twitter
import org.springframework.social.twitter.api.impl.TwitterTemplate
import org.springframework.social.twitter.connect.TwitterAdapter;

import com.the6hours.grails.springsecurity.twitter.TwitterAuthProvider;
import com.the6hours.grails.springsecurity.twitter.TwitterAuthToken;

import br.org.prismaCamara.modelo.Usuario
import br.org.prismaCamara.modelo.UsuarioTwitter
import br.org.prismaCamara.util.redes.TwitterUtil

class TwitterPostController {

	def springSecurityService
	
	def grailsApplication
	
	TwitterAuthProvider twitterAuthProvider
	TwitterAdapter twitterAdapter
	TwitterAuthToken twitterAuthToken
	
	//@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
	def postarNoMural = {
		Usuario usuario = springSecurityService.currentUser		
		String conteudo = params.mp.trim()
		
		new TwitterUtil(grailsApplication:grailsApplication).postar(usuario,conteudo)
		
		redirect(controller:'postagens')
	}
	
	@Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
	def postarNoMuralOld = {
		Usuario usuario = springSecurityService.currentUser
				UsuarioTwitter utwitter = UsuarioTwitter.where{user==usuario}.find()
				
				Properties prop = new Properties()
				def nomeArquivoConf = grailsApplication.config.grails.config.locations[1].substring(5)
				prop.load(new ByteArrayInputStream(new File(nomeArquivoConf).bytes))
				def consumerKey = prop["grails.plugins.springsecurity.twitter.consumerKey"]
						def consumerSecret = prop["grails.plugins.springsecurity.twitter.consumerSecret"]
								Twitter twitter = new TwitterTemplate(consumerKey,consumerSecret,utwitter.token,utwitter.tokenSecret)
				
				String conteudo = params.mp.trim()
				twitter.timelineOperations().updateStatus(conteudo.size()>140?conteudo[0..139]:conteudo)
				redirect(controller:'postagens')
	}
	
	
}
