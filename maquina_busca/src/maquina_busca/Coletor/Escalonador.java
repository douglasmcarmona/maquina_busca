package Coletor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Classe escalonador
 * Representa o subcomponente escalonador do coletor da m�quina de busca. Mantem um conjunto de paginas visitadas
 * e uma fila de paginas nao visitadas que � realimentada atrav�s de um arquivo que cont�m todas as urls encontradas
 * na coleta no presente momento
 * @author Douglas, Karine
 *
 */
public class Escalonador {

	private Set<String> pagsVisitadas;
	private PriorityQueue<String> pagsEscalonadas;
	private BufferedReader buffer;
	
	/**
	 * Construtor
	 * @throws FileNotFoundException: caso o arquivo nao exista
	 */
	public Escalonador() throws FileNotFoundException {
		pagsVisitadas = new HashSet<String>();
		pagsEscalonadas = new PriorityQueue<String>(500);
		buffer = new BufferedReader(new FileReader("thread0.txt"));
	}

	public Set<String> getPagsVisitadas() {
		return pagsVisitadas;
	}

	public void setPagsVisitadas(Set<String> pagsVisitadas) {
		this.pagsVisitadas = pagsVisitadas;
	}

	public PriorityQueue<String> getPagsEscalonadas() {
		return pagsEscalonadas;
	}

	public void setPagsEscalonadas(PriorityQueue<String> pagsEscalonadas) {
		this.pagsEscalonadas = pagsEscalonadas;
	}

	public void pagVisitada(String url) {
		pagsVisitadas.add(url);
	}
	
	public boolean foiVisitada(String url) {
		return pagsVisitadas.contains(url);
	}
	
	public boolean proximaFoiVisitada() {
		return pagsVisitadas.contains(pagsEscalonadas.peek());
	}
	
	public BufferedReader getBuffer() {
		return this.buffer;
	}
	
	/**
	 * Fun��o proxima
	 * recupera e remome a proxima url da fila
	 * @return url
	 */
	public String proxima() {
		return pagsEscalonadas.poll();
	}
	
	/**
	 * Fun��o vazio
	 * verifica se a fila est� vazia
	 * @return true se estiver vazia, false caso contr�rio
	 */
	public boolean vazio() {
		return pagsEscalonadas.isEmpty();
	}
	
	public int numPagsEscalonadas() {
		return pagsEscalonadas.size();
	}

	/**
	 * M�todo pagNaoVisitada
	 * Insere uma url na fila de escalonamento ou no conjunto de paginas coletadas
	 * @param url: url processada
	 * @param inserir: true se a p�gina foi escalonada, false se a p�gina foi visitada
	 */
	public void pagNaoVisitada(String url, boolean inserir) {
		if (inserir)
			pagsEscalonadas.add(url);
		else
			pagsVisitadas.add(url);
	}
	
	/**
	 * M�todo escalonar
	 * realimenta o escalonador com novas urls
	 * @throws IOException
	 */
	public void escalonar() throws IOException {
		while(buffer.ready()) {
			pagNaoVisitada(buffer.readLine(), true);
		}
	}
}
