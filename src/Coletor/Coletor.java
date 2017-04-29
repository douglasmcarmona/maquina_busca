package Coletor;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.nodes.Document;

/**
 * Classe Coletor
 * Representa o componente Coletor da máquina de busca. A máquina é alimentada pela base de dados do
 * site wikipedia.org, por ser o site com maior quantidade de dados acessíveis sobre entidades
 * 
 * Controle de versão:
 * 
 * 1.0 - coletor de página (fetcher) + escalonador + parser + armazenador funcionais. Sistema sequencial
 * 
 * @author Douglas, Karine
 *
 */
public class Coletor {

	public static final int MAX_PAGINAS = 10000;

	public static void main(String args[]) {
		try {
			Escalonador esc = new Escalonador();
			ColetorPagina cp = new ColetorPagina();
			BufferedWriter buffer = new BufferedWriter(new FileWriter("thread0.txt", false));
			BufferedWriter log_tempo = new BufferedWriter(new FileWriter("timelog.txt", true));
			
			String semente1 = "https://en.wikipedia.org/wiki/Category:Living_people?from=";
			String semente2 = "https://en.wikipedia.org/wiki/Category:Dead_people";
			String semente3 = "https://en.wikipedia.org/wiki/Category:Places";
			buffer.write(semente1);
			buffer.newLine();
			buffer.write(semente2);
			buffer.newLine();
			buffer.write(semente3);
			buffer.newLine();
			buffer.flush();
			buffer.close();
			
			esc.escalonar();
			Armazenador.iniciar();
			int cont = 0;
			
			long inicio = System.currentTimeMillis();
			while(!esc.vazio() && cont <= MAX_PAGINAS) {
				String url = "";
				try {
					url = esc.proxima();
					if(!esc.foiVisitada(url)) {
						//Thread.sleep(4000); // para evitar bloqueio de IP por muitas requisições em um curto período de tempo
						Document doc = cp.buscar(url);
						String texto = Parser.parse(doc);
						String titulo = doc.title();
						esc.pagNaoVisitada(url, false);
						Armazenador.armazenar(url, titulo, texto);
						cont += 1;
						if(esc.vazio()) {
							esc.escalonar();
						}
					}
					System.out.println("Paginas coletadas: " + cont);
				}
				catch(Exception e) {
					continue; // ignora paginas que nao foram corretamente coletadas
				}
			}			
			long fim = System.currentTimeMillis();
			
			esc.getBuffer().close();
			log_tempo.write("paginas coletadas: " + cont + "\tTempo: " + ((fim-inicio)/1000) + "s\n");
			log_tempo.flush();
			log_tempo.close();
			System.out.println("time elapsed: " + ((fim-inicio)/1000));
		}
		catch (IOException | ClassCastException e ) {
			if(e instanceof FileNotFoundException) { }
			
		}
		
	}
}
