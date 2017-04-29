package Coletor;

import org.jsoup.nodes.Document;

/**
 * Classe parser
 * Representa o componente parser do coletor da maquina de busca
 * @author Douglas, Karine
 *
 */
public class Parser {
	
	/**
	 * Metodo parse
	 * extrai o conteudo da tag body de uma pagina html para ser salva
	 * @param html: a pagina a ser processada
	 * @return: o corpo da pagina
	 */
	public static String parse(Document html) {
		return html.body().text();
	}

}