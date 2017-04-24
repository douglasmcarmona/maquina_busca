package Coletor;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * CLASSE NAO UTILIZADA
 * @author Douglas
 *
 */
public class DNSCacher {

	private HashMap<String, String> resolvedor;
	private static final int MAX_REG = 3;
	
	public DNSCacher() {
		try {
			BufferedInputStream entrada = new BufferedInputStream(new FileInputStream("dns_cache\\dns_cache.bin"));
			BufferedReader entradaLen = new BufferedReader(new FileReader("dns_cache\\len.txt"));
			byte[] bufferIp, bufferDominio;
			resolvedor = new HashMap<String, String>(MAX_REG);
			int tamanho = 0;
			while(entradaLen.ready()) {
				tamanho = Integer.parseInt(entradaLen.readLine());
				bufferDominio = new byte[tamanho];
				entrada.read(bufferDominio);
				tamanho = Integer.parseInt(entradaLen.readLine());
				bufferIp = new byte[tamanho];
				entrada.read(bufferIp);
				String dominio = new String(bufferDominio, Charset.forName("utf-8"));
				String ip = new String(bufferIp, Charset.forName("utf-8"));
				
				resolvedor.put(dominio, ip);
			}
			entrada.close();
			entradaLen.close();
		}
		catch (FileNotFoundException e) {
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void salvar(String dominio, String ip) {
		try {
			BufferedOutputStream saida = new BufferedOutputStream(
					new FileOutputStream("dns_cache\\dns_cache.bin", true));
			BufferedWriter saidaLen = new BufferedWriter(
					new FileWriter("dns_cache\\len.txt", true));
			saida.write(dominio.getBytes());
			//saida.write('@');
			saidaLen.write(String.valueOf((dominio.length())) + "\n");
			//saida.write(System.lineSeparator().getBytes());
			saida.write(ip.getBytes());
			saidaLen.write(String.valueOf(ip.length()) + "\n");
			//saida.write(System.lineSeparator().getBytes());
			saida.flush();
			saidaLen.flush();
			saida.close();
			saidaLen.close();
			resolvedor.put(dominio, ip);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public String pesquisar(String dominio) {
		String aux = resolvedor.get(dominio);
		return aux;
	}
}