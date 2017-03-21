package maquina_busca;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.jsoup.nodes.Document;
public class Coletor {

	//InetAddress ip = InetAddress.getByName(new URL(url).getHost());
	public static final int MAX_PAGINAS = 1;
	
	public static void main(String args[]) {
		ColetorPagina cp = new ColetorPagina();
		DNSCacher dnsc = new DNSCacher();
		Escalonador esc = new Escalonador();
		MyParser myp = new MyParser();	
		
		String url = "http://www.wladmirbrandao.com.br";
		Document in = cp.buscar(url);
		Document doc = myp.parseHTML(in.toString(), url);
		InetAddress ip;
		try {
			ip = InetAddress.getByName(new URL(url).getHost());
			dnsc.salvar(ip.getHostName(), ip.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		esc.pagVisitada(url);
	}
}
