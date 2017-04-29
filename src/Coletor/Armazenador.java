package Coletor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe Armazenador
 * Responsável por persistir as páginas coletadas (gravação no disco)
 * @author Douglas, Karine
 *
 */
public class Armazenador {

	/**
	 * Metodo iniciar
	 * Cria os diretorios nos quais as paginas serao salvas. Um diretorio para uma letra do alfabeto
	 */
	public static void iniciar() {
		try {
			Files.createDirectory(Paths.get("paginas"));
			for(char c='A'; c<='Z'; c++) {
				try {
					Files.createDirectory(Paths.get("paginas\\" + c));
				}
				catch(FileAlreadyExistsException e) {} // se a pasta ja existe, a operacao é ignorada
			}
		}
		catch (IOException e) {
			if(e instanceof FileAlreadyExistsException) {
				for(char c='A'; c<='Z'; c++) {
					try {
						Files.createDirectory(Paths.get("paginas\\" + c));
					}
					catch(IOException ioe) {}
				}
			}
		}
	}
	
	/**
	 * Metodo armazenar
	 * Grava uma pagina coletada no disco
	 * @param url: orl onde a pagina foi encontrada
	 * @param titulo: titulo da pagina
	 * @param pagina: corpo da pagina
	 */
	public static void armazenar(String url, String titulo, String pagina) {
		String pasta = String.valueOf(titulo.charAt(0));
		
		// remove caracteres invalidos que podem existir no titulo da pagina
		char[] invalidos = {'\\','/','?','%','*',':','|','\"','<','>','.',' '};
		for(char c : invalidos) {
			titulo = titulo.replace(c, '-');
		}
		
		titulo = titulo.toLowerCase();
		String caminho = "paginas\\" + pasta + "\\" + titulo + ".txt";
		try {
			PrintWriter saida = new PrintWriter(new File(caminho),"UTF-8");
			saida.println(titulo);
			saida.println(url);
			saida.print(pagina);
			saida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
