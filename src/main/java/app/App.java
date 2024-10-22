package app;
import java.util.Scanner;

import include.DatFilter;
import include.HtmlParser;

public class App {
	public static void main(String[] args) {
		String inputDirectory = "C:\\Users\\João Pedro Andrade\\Desktop\\Projetos\\Indexador de Arquivos\\DocumentIndexer\\resources\\html";
		String outputDatFile = "C:\\Users\\João Pedro Andrade\\Desktop\\Projetos\\Indexador de Arquivos\\DocumentIndexer\\resources\\data\\saida.dat";
        HtmlParser p = new HtmlParser();
//        p.parseFile(outputDatFile);
        p.parseDirectory(inputDirectory, outputDatFile);
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite as palavras-chave: ");
        String palavrasChaves = scanner.nextLine();
        
        new DatFilter(outputDatFile, palavrasChaves);
       
        scanner.close();
    }  
}
