package maquina_busca;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Escalonador {

	private Set<String> pagsVisitadas;
	private List<String> pagsNaoVisitadas;
	
	public Escalonador() {
		pagsVisitadas = new HashSet<String>();
		pagsNaoVisitadas = new LinkedList<String>();
	}

	public Set<String> getPagsVisitadas() {
		return pagsVisitadas;
	}

	public void setPagsVisitadas(Set<String> pagsVisitadas) {
		this.pagsVisitadas = pagsVisitadas;
	}

	public List<String> getPagsNaoVisitadas() {
		return pagsNaoVisitadas;
	}

	public void setPagsNaoVisitadas(List<String> pagsNaoVisitadas) {
		this.pagsNaoVisitadas = pagsNaoVisitadas;
	}
	
	public void pagVisitada(String url) {
		pagsVisitadas.add(url);
	}
	
	public void pagNaoVisitada(String url, boolean inserir) {
		if(inserir) pagsNaoVisitadas.add(url);
		else {
			pagsNaoVisitadas.remove(url);
			pagsVisitadas.add(url);
		}
	}
}
