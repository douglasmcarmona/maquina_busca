package maquina_busca;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ColetorPagina {

	private static final String agente = "Mozilla/5.0 (Windows NT 6.1; WOW64) "
			+ "AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	private DNSCacher cacher;
	
	public ColetorPagina() {
		cacher = new DNSCacher();
	}
	
	public Document buscar(String url) {
		try {			
			Connection connection = Jsoup.connect(url).userAgent(agente);
			Document htmlDocument = connection.get();
			return htmlDocument;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
