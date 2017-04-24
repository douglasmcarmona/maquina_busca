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
 * Representa o subcomponente escalonador do coletor da máquina de busca. Mantem um conjunto de paginas visitadas
 * e uma fila de paginas nao visitadas que é realimentada através de um arquivo que contém todas as urls encontradas
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
	 * Função proxima
	 * recupera e remome a proxima url da fila
	 * @return url
	 */
	public String proxima() {
		return pagsEscalonadas.poll();
	}
	
	/**
	 * Função vazio
	 * verifica se a fila está vazia
	 * @return true se estiver vazia, false caso contrário
	 */
	public boolean vazio() {
		return pagsEscalonadas.isEmpty();
	}
	
	public int numPagsEscalonadas() {
		return pagsEscalonadas.size();
	}

	/**
	 * Método pagNaoVisitada
	 * Insere uma url na fila de escalonamento ou no conjunto de paginas coletadas
	 * @param url: url processada
	 * @param inserir: true se a página foi escalonada, false se a página foi visitada
	 */
	public void pagNaoVisitada(String url, boolean inserir) {
		if (inserir)
			pagsEscalonadas.add(url);
		else
			pagsVisitadas.add(url);
	}
	
	/**
	 * Método escalonar
	 * realimenta o escalonador com novas urls
	 * @throws IOException
	 */
	public void escalonar() throws IOException {
		while(buffer.ready()) {
			pagNaoVisitada(buffer.readLine(), true);
		}
	}
}
