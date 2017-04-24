package Coletor;

public class Filtro {

	public void filtrar(String pagina) {
		pagina = removerCaracteresIndesejaveis(pagina);		
		String[] tokens = pagina.split(" ");
		
	}
	
	public String removerCaracteresIndesejaveis(String texto) {
		String retorno = "";
		char indesejaveis[] = new char[(64-33)+(96-91)+(126-123)];
		int i=0;
		for(i=0; i<=(64-33); i++) {
			indesejaveis[i] = (char)(i+33);
		}
		for(; i<=(96-91)+(64-33); i++) {
			indesejaveis[i] = (char)(i+59);
		}
		for(; i<=(64-33)+(96-91)+(126-123); i++) {
			indesejaveis[i] = (char)(i+27);
		}
		
		return texto.replaceAll(indesejaveis.toString(), "");
	}
}
