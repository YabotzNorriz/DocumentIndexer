package app;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import include.HtmlParser;

public class App {
	public static void main(String[] args) {
        // Diretório contendo os arquivos HTML
        String inputDirectory = "C:\\Users\\João Pedro Andrade\\Desktop\\Projetos\\Indexador de Arquivos\\DocumentIndexer\\resources\\html";
        
        String outputDatFile = "C:\\Users\\João Pedro Andrade\\Desktop\\Projetos\\Indexador de Arquivos\\DocumentIndexer\\resources\\data\\saida.dat";
        HtmlParser p = new HtmlParser();
        p.parseDirectory(inputDirectory, outputDatFile);
    }
}
