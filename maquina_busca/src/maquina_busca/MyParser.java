package maquina_busca;

import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

public class MyParser {

	public Document parseHTML(String html, String uri) {
		return Parser.parse(html, uri);
	}
}
