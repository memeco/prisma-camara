package hackathon2013

class Proposicao {
	
	// segundo pesquisado no site do congresso e após teste nos web services
	static final Integer PRIMEIRO_ANO = 1934
	
	Integer idProposicao
	Integer numero
	String ano
	Date dataApresentacao
	String txtEmenta // será truncado em 256 caracteres
	String txtExplicacaoEmenta // será truncado em 144 caracteres
	String txtApreciacao // será truncado em 144 caracteres
	
	Deputado autor
	TipoProposicao tipoProposicao
	
	String nomeAutor // em registros antigos não há relação, apenas o nome do autor  // será truncado em 200 caracteres

	Date ultimoDespacho	
	String txtUltimoDespacho // será truncado em 144 caracteres
	String situacao
	
	Date ultimaVotacao

	static hasMany = [votacoes:Votacao]
	
	static transients = ['PRIMEIRO_ANO','descricao','urlDetalhes']
	
	static mapping = {
		autor(cascade:'all')
		tipoProposicao(cascade:'all')
	}
	
	static constraints = {
		ano(maxSize:4)
		txtEmenta(maxSize:256)
		txtExplicacaoEmenta(maxSize:144)
		txtApreciacao(maxSize:144)
		txtUltimoDespacho(maxSize:144, nullable:true)
		situacao(maxSize:256) 
		ultimoDespacho(nullable:true)

		autor(nullable:true)
		nomeAutor(nullable:true , maxSize:200)
	}
	
	public String getDescricao() {
		"${tipoProposicao.sigla} ${numero}/${ano}"
	}
	
	void setNomeAutor(String nomeAutor) {
		this.nomeAutor=nomeAutor?.size()>200?nomeAutor[0..199]:nomeAutor
	}
	
	void setTxtEmenta(String txtEmenta) {
		this.txtEmenta=txtEmenta?.size()>256?txtEmenta[0..255]:txtEmenta
	}
	
	void setTxtExplicacaoEmenta(String txtExplicacaoEmenta) {
		this.txtExplicacaoEmenta=txtExplicacaoEmenta?.size()>144?txtExplicacaoEmenta[0..143]:txtExplicacaoEmenta
	}
	
	void setTxtUltimoDespacho(String txtUltimoDespacho) {
		this.txtUltimoDespacho=txtUltimoDespacho?.size()>144?txtUltimoDespacho[0..143]:txtUltimoDespacho
	}
	
	void setTxtApreciacao(String txtApreciacao) {
		this.txtApreciacao=txtApreciacao?.size()>144?txtApreciacao[0..143]:txtApreciacao
	}
	
	// garantindo que a "ultimaVotacao" nunca fique vazia, sendo pelo menos igual a data de sua apresentação
	def beforeValidate() {
		ultimaVotacao=ultimaVotacao?:dataApresentacao
	}
	
	def getUrlDetalhes() {
		//http://www.camara.gov.br/proposicoesWeb/fichadetramitacao?idProposicao=377011
		"${Parametro.findBySigla('url_proposicao_site').valor}${idProposicao}"
	}
		
}
