package maquina_busca;

import java.io.*;
import java.nio.file.Files;

public class DNSCacher {

	
	public void salvar(String dominio, String ip) {
		try {
			Files.createFile("dns_cache\\dns_cache.bin", );
			saida.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean pesquisar(String dominio) {
		try {
			BufferedReader entrada = new BufferedReader(new FileReader());
			if(entrada.read(dominio.toCharArray(), 0, dominio.length()) == -1) return false;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
