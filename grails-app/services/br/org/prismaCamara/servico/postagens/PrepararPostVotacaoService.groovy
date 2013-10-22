package br.org.prismaCamara.servico.postagens

import grails.gsp.PageRenderer
import groovy.util.logging.Log4j
import br.org.prismaCamara.mensagem.Postagem
import br.org.prismaCamara.mensagem.PostagemVotacaoProposicao
import br.org.prismaCamara.modelo.Proposicao
import br.org.prismaCamara.modelo.Usuario

@Log4j
class PrepararPostVotacaoService extends PrepararPost {

	def usuarioService
	
	@Override
	public String getNomeTipoInformacao() {
		return "votacaoProposicao";
	}
	
	public Postagem getPostagem() {
		return new PostagemVotacaoProposicao()
	}
	
	@Override
	public void preparar(Usuario usuario, Long idEntidade) {
		def postagemPreparada = prepararPostagem(usuario, idEntidade, [prop:Proposicao.get(idEntidade),tipo:usuario.tipoRede])
		if (postagemPreparada)
			log.debug("Postagem preparada com sucesso para ${usuario.id} em ${nomeTipoInformacao} (idEntidade: ${idEntidade})")
	}

}