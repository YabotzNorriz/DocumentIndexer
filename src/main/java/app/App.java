package app;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import include.HtmlParser;

public class App {
	public static void main(String[] args) {
		
		String outputDatFile = "C:\\Users\\João Pedro Andrade\\Desktop\\Projetos\\Indexador de Arquivos\\DocumentIndexer\\resources\\data\\saida.dat";
        HtmlParser p = new HtmlParser();
        p.parseFile(outputDatFile);
    }
}
