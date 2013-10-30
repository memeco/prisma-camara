package br.org.prismaCamara.util

import br.org.prismaCamara.modelo.Deputado
import java.awt.image.BufferedImage

import javax.imageio.ImageIO

import org.imgscalr.Scalr

/**
 * Classe utilitária para uso de imagens locais no Servidor 
 * @author jyoshiriro
 *
 */
class ImagensUtil {
	
	/**
	 * Retorna o array de bytes de uma imagem local no servidor a partir do nome indicado em "nomeArquivo"
	 * @param nomeArquivo
	 * @return o array de bytes do arquivo ou <b>null</b> se o arquivo não existe
	 */
	static byte[] getImagemLocal(String nomeArquivo) {
		def arquivo = new File(nomeArquivo)
		if (arquivo.exists()) {
			arquivo.bytes
		} else {
			null
		}
	}

	/**
	 * Retorna o array de bytes da miniatura da imagem da URL indicada em "urlImagemOriginal"
	 * @param urlImagemOriginal
	 * @param nomeArquivo Nome do arquivo que será salvo localmente no servidor. Se <b>null</b>, nenhum arquivo será salvo.
	 * @return
	 */
	static byte[] getMiniatura(String urlImagemOriginal, String nomeArquivo) {
		getMiniatura(urlImagemOriginal.toURL().bytes, nomeArquivo)
	}
	
	/**
	 * Retorna o array de bytes da miniatura da imagem do array de bytes do parâmetro "original"
	 * @param original
	 * @param nomeArquivo Nome do arquivo que será salvo localmente no servidor. Se <b>null</b>, nenhum arquivo será salvo.
	 * @return O array de bytes ou <b>null</b>
	 */
	static byte[] getMiniatura(byte[] original, String nomeArquivo) {

		def imageIn = ImageIO.read(new ByteArrayInputStream(original));
		if ((!original) || (!imageIn)) {
			return null
		}		
		BufferedImage scaledImage = Scalr.resize(imageIn, 72).getSubimage(3, 7, 48, 48);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream()
		ImageIO.write(scaledImage, "jpg", bos);
		
		def bmini = bos.buf
		
		if (nomeArquivo) {
			new File(nomeArquivo).bytes = bmini
		}
		
		bmini
	}
}
