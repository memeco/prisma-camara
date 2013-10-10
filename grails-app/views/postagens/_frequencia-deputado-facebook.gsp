
<g:set var="presente" value="${freq?.frequenciaDia=='Presença'}"/>

Assiduidade de ${dep.descricao} na Câmara dos Deputados: Dia <g:formatDate date="${freq.dia}"/> -> ${freq?freq.frequenciaDia:'Ausente'}. 
<g:if test="${!presente}">
<g:if test="${freq.justificativa}">* Justificativa para ausência: ${freq.justificativa} *</g:if>
<g:else>* A ausência não foi justificada! *</g:else>
</g:if>
<g:if test="${presente}">Participação nas Sessões Realizadas neste dia:</g:if>
<g:else>Como faltou, perdeu as sequintes sessões:</g:else>
<g:each in="${freq.frequenciasSessao}" var="fs" status="st">
${st+1}. ${fs.descricao} <g:formatDate date="${fs.inicio}" format="HH:mm"/><g:if test="${presente}"> -> ${fs.frequencia}</g:if>
</g:each>

Saiba mais em ${dep.urlDetalhes}.