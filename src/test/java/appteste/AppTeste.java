package appteste;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AppTeste {
	public static void main(String[] args) {
		String inputHtmlFile = "C:\\Users\\João Pedro Andrade\\Desktop\\Projetos\\Indexador de Arquivos\\DocumentIndexer\\resources\\html\\PORTARIA Nº 2.117, DE 6 DE DEZEMBRO DE 2019 - PORTARIA Nº 2.117, DE 6 DE DEZEMBRO DE 2019 - DOU - Imprensa Nacional.html";

        try {
            // Lê o arquivo HTML e converte para um documento Jsoup
            File inputFile = new File(inputHtmlFile);
            Document document = Jsoup.parse(inputFile, "UTF-8");

            // Seleciona todas as tags <meta> do documento
            Elements metaTags = document.getElementsByTag("meta");

            // Itera sobre todas as tags <meta> e exibe os atributos
            for (Element meta : metaTags) {
                String name = meta.attr("name");
                String content = meta.attr("content");
                String charset = meta.attr("charset");

                // Exibe o conteúdo de cada atributo
                if(name.equals("keywords")) {
                	System.out.println(name);
                }

                System.out.println("------------------------------------");
            }
        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
	}
}
