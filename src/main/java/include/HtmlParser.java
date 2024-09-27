package include;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlParser {
    private String inputHTMLFile;
    private String outputDATFile;
    private String inputFolder;
    
    public HtmlParser() {
    	super();
    }

	public HtmlParser(String inputHTMLFile, String outputDATFile, String inputFolder) {
		super();
		this.inputHTMLFile = inputHTMLFile;
		this.outputDATFile = outputDATFile;
		this.inputFolder = inputFolder;
	}

	public String getInputFolder() {
		return inputFolder;
	}

	public void setInputFolder(String inputFolder) {
		this.inputFolder = inputFolder;
	}

	public String getInputHTMLFile() {
		return inputHTMLFile;
	}
	
	
	public void setInputHTMLFile(String inputHTMLFile) {
		this.inputHTMLFile = inputHTMLFile;
	}
	
	public String getOutputDATFile() {
		return outputDATFile;
	}
	
	public void setOutputDATFile(String outputDATFile) {
		this.outputDATFile = outputDATFile;
	}

	public boolean parseFile(String inputHtmlFile, String outputDatFile) {
		try {
	    	// O objeto File faz parte do java.io
	        File inputFile = new File(inputHtmlFile);
	        // Transforma em UTF-8 para que seja possível a leitura de alguns caracteres da língua portuguesa
	        Document documentoHTML = Jsoup.parse(inputFile, "UTF-8");

	        String text = documentoHTML.text();
	        
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputDatFile))) {
	            writer.write(text);
	            System.out.println("Arquivo .dat criado com sucesso!");
	        } catch (IOException e) {
	        	System.err.println("Erro ao criar o arquivo .dat" + e.getMessage());
	        	return false;
	        }
	    } catch (IOException f) {
	        System.err.println("Erro ao processar o arquivo: " + f.getMessage());
	        return false;
	    }
		return true;
	}
	
	public boolean parseDirectory(String inputDirectory,String outputDatFile) {
        File pasta = new File(inputDirectory);

        if (!pasta.exists() || !pasta.isDirectory()) {
            System.err.println("O diretório especificado não existe ou não é um diretório.");
            return false;
        }

        File[] arquivosHtml = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".html"));

        if (arquivosHtml == null || arquivosHtml.length == 0) {
            System.out.println("Nenhum arquivo HTML encontrado no diretório.");
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputDatFile))) {
            // Processa cada arquivo HTML
        	int i = 1;
            for (File file : arquivosHtml) {
                if (file.isFile()) {
                    try {
                        Document document = Jsoup.parse(file, "UTF-8");
                        String text = document.text();

                        // Escreve o nome do arquivo e o conteúdo no arquivo de saída
                        writer.write("[Arquivo" + i + "]:" + file.getName());
                        writer.newLine();
                        writer.write(text);
                        i++;
                    } catch (IOException e) {
                        System.err.println("Erro ao processar o arquivo " + file.getName() + ": " + e.getMessage());
                        return false;
                    }
                }
            }
            System.out.println("Todos os arquivos HTML foram processados e o conteúdo foi salvo em " + outputDatFile);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo .dat: " + e.getMessage());
            return false;
        }
        return true;
	}
}

