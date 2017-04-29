package Coletor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Classe ColetorPagina (Fetcher) Representa o subcomponenete responsável por
 * buscar páginas na web. Para cada página coletada, os links nela encontrados
 * sao gravados em um arquivo texto para alimentar o escalonador
 * 
 * @author Douglas
 *
 */

public class ColetorPagina {

	// coleta a página se passando por um navegador web
	private static final String agente = "Mozilla/5.0 (Windows NT 6.1; WOW64) "
			+ "AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";

	private BufferedWriter buffer;

	public ColetorPagina() {
		// System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
	}

	/**
	 * Função buscar 
	 * Coleta a página html especificada pela url
	 * @param url: url da página a ser coletada
	 * @return Document: objeto que representa o arquivo html
	 */
	public Document buscar(String url) throws IOException {
		// necessario para realizar a requisicao por IP
		Document htmlDocument = Jsoup.connect(url).userAgent(agente).get();
		buffer = new BufferedWriter(new FileWriter("thread0.txt", true));
		// coleta todos os links (tags <a>) da pagina
		Elements links = htmlDocument.getElementsByTag("a");
		// as tags validas sao as que possuem o atributo href diferente de vazio
		for (Element link : links) {
			if (link.absUrl("href").contains("en.wikipedia.org")) {
				buffer.write(link.absUrl("href"));
				buffer.newLine();
			}
		}
		buffer.flush();
		buffer.close();
		return htmlDocument;
	}
}
