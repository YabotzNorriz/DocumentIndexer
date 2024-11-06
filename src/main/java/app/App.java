package app;

import java.util.Scanner;

import include.DatFilter;
import include.HtmlParser;

public class App {
    public static void main(String[] args) {
        String inputDirectory = "/home/joaopedropaes/Documentos/Projetos/Indexador de Arquivos/DocumentIndexer/resources/html";
        String outputDatFile = "/home/joaopedropaes/Documentos/Projetos/Indexador de Arquivos/DocumentIndexer/resources/data/saida.dat";
        new HtmlParser(inputDirectory, outputDatFile);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite as palavras-chave: ");
        String palavrasChaves = scanner.nextLine();

        new DatFilter(outputDatFile, palavrasChaves);

        scanner.close();
    }
}
