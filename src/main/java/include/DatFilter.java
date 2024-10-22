package include;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatFilter {
	
	public static Set<String> PALAVRAS_IGNORADAS = new HashSet<>(Arrays.asList(
        "a", "as", "ao", "aos", "até", "de", "da", "das", "do", "dos", "e", "em", "mas", "na", 
	    "nas", "nem", "no", "nos", "o", "os", "ou", "para", "pelo", "pelos", "pois", "por", 
	    "que", "se", "um", "uma", "uns", "umas"
	));
	
	private String datFile;
	private String palavrasChave;

	public DatFilter(String datFile, String palavrasChave) {
		super();
		this.datFile = datFile;
		this.palavrasChave = palavrasChave;
		filtro(datFile, palavrasChave);
	}

	public DatFilter() {
		super();
	}

	public String getDatFile() {
		return datFile;
	}

	public void setDatFile(String datFile) {
		this.datFile = datFile;
	}

	public String getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(String palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	private boolean filtro(String datFile, String palavrasChave) {

		Set<String> palavrasEncontradas = new HashSet<>(); 
        List<String> listaPalavras = preencherListaPalavrasChave(palavrasChave);
        List<String> listaLinhasCompletas = new ArrayList<>();
	
        try (BufferedReader reader = new BufferedReader(new FileReader(datFile))) {
        	
            String linha;
            String linhaAnterior = "";
            
            if (listaPalavras.size() == 0 || listaPalavras == null) {
            	return false;
            }
            System.out.println(listaPalavras.toString());
            // Percorre o arquivo linha por linha
            while ((linha = reader.readLine()) != null) {
    
                for (String palavra: listaPalavras) {
                	if (linha.toLowerCase().contains(palavra)) {
                    	palavrasEncontradas.add(palavra);
                    }
                }
                
                if (palavrasEncontradas.containsAll(listaPalavras)) {
                	listaLinhasCompletas.add(linha);
                	System.out.println(linha);
                } else {
                	System.out.println("Nem todas as palavras foram encontradas no arquivo.");
                }
                
                linhaAnterior = linha;
                palavrasEncontradas.clear();
            }

            if (!palavrasEncontradas.containsAll(listaPalavras)) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
	}

	private List<String> preencherListaPalavrasChave(String palavrasChaves) {
		 String[] palavrasBusca = palavrasChaves.trim().toLowerCase().split(",");
		 List<String> listaPalavras = Arrays.asList(palavrasBusca);
		 String palavra = "";
		 
		 for (int i = 0; i < listaPalavras.size(); i++) {
			 if (PALAVRAS_IGNORADAS.contains(listaPalavras.get(i))) {
				 palavra = listaPalavras.get(i);
				 System.out.println("Palavra removida: " + palavra);
				 listaPalavras.remove(i);
			 }
			 System.out.println(listaPalavras.get(i));
		 }
		 
		 return listaPalavras;
	}
}
