<g:set var="presente" value="${freq?.frequenciaDia.startsWith('P')}"/>
Frequência d<g:deputadoPrefix dep="${dep}" minusculo="true"/> em <g:formatDate date="${freq.dia}" format="d/M/yy"/>: ${presente?'Presente':'Faltou'}<g:if test="${!presente}"><g:if test="${freq.justificativa}"> (${freq.justificativa})</g:if><g:else> (não justificou)</g:else>
</g:if>
${freq.urlDetalhesCurta}  Via http://goo.gl/fWAHmG
<%--
 * Copyright 2013 de José Yoshiriro (jyoshiriro@gmail.com) e Raimundo Norberto (raimundonorberto@gmail.com)
 * Este arquivo é parte do programa Olho na Câmara.
 * 
 * O Olho na Câmara é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro
 * dos termos da GNU General Public License como publicada pela Fundação do Software Livre
 * (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser
 * útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer
 * MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter
 * recebido uma cópia da GNU General Public License, sob o título 'LICENCA.txt', junto com
 * este programa, se não, acesse http://www.gnu.org/licenses/
 --%>