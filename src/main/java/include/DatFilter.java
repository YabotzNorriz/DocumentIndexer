package include;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatFilter {

    public static Set<String> PALAVRAS_IGNORADAS = new HashSet<>(Arrays.asList(
            "a", "as", "ao", "aos", "até", "de", "da", "das", "do", "dos", "e", "em", "mas", "na",
            "nas", "nem", "no", "nos", "o", "os", "ou", "para", "pelo", "pelos", "pois", "por",
            "que", "se", "um", "uma", "uns", "umas"));

    private String datFile;
    private String palavrasChave;
    // public static Map<Integer, Map<String, String>> mapaNomePath;
    public static Map<String, String> mapaNomePath;
    // public static List<Map<String, String>> listaMapas;

    public DatFilter(String datFile, String palavrasChave) {
        super();
        this.datFile = datFile;
        this.palavrasChave = palavrasChave;
        // listaMapas = new ArrayList<>();
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

    // Map<Integer, Map<String, String>
    // public static Map<Integer, Map<String, String>> getMapaNomePath() {
    // return mapaNomePath;
    // }
    //
    // public static void setMapaNomePath(Map<Integer, Map<String, String>>
    // mapaNomePath) {
    // DatFilter.mapaNomePath = mapaNomePath;
    // }

    // Map<String, String>
    public static Map<String, String> getMapaNomePath() {
        return mapaNomePath;
    }

    public static void setMapaNomePath(Map<String, String> mapaNomePath) {
        DatFilter.mapaNomePath = mapaNomePath;
    }

    private boolean filtro(String datFile, String palavrasChave) {

        Set<String> palavrasEncontradas = new HashSet<>();
        List<String> listaPalavras = preencherListaPalavrasChaves(palavrasChave);
        Map<String, String> mapaNomePath = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(datFile))) {

            String linha;
            String path = "";
            String[] partesArquivo = { "" };
            String[] partesPath = { "" };
            String nomeArquivo = "";
            // Integer index = 0;

            if (listaPalavras.size() == 0 || listaPalavras == null) {
                return false;
            }
            // Percorre o arquivo linha por linha
            while ((linha = reader.readLine()) != null) {

                if (linha.startsWith("[arquivo")) {
                    partesArquivo = linha.split(":");
                    nomeArquivo = partesArquivo[1];
                    System.out.println("Parte 1 nome: " + partesArquivo[0] + "\nParte 2 nome: " + partesArquivo[1]);
                }

                if (linha.startsWith("[path")) {
                    partesPath = linha.split(":");
                    path = partesPath[1];
                    System.out.println("Parte 1 path: " + partesPath[0] + "\nParte 2 path: " + partesPath[1]);
                }

                for (String palavra : listaPalavras) {
                    Pattern padrao = Pattern.compile("\\b" + Pattern.quote(palavra) + "\\b");
                    Matcher matcher = padrao.matcher(linha);

                    while (matcher.find()) {
                        palavrasEncontradas.add(matcher.group());
                    }
                }

                if (palavrasEncontradas.containsAll(listaPalavras)) {
                    mapaNomePath.put(nomeArquivo, path);
                    // listaMapas.add(mapaNomePath);
                    System.out.println("Encontrada");
                } else {
                    System.err.println("Nem todas as palavras foram encontradas no arquivo.");
                }

                // if (palavrasEncontradas.containsAll(listaPalavras)) {
                // subMap.put(partesArquivo[1], partesPath[1]);
                // mapaNomePath.put(index, subMap);
                // index++;
                // System.out.println("Encontrada");
                // } else {
                // System.err.println("Nem todas as palavras foram encontradas no arquivo.");
                // }

                palavrasEncontradas.clear();
            }

            // for (Map.Entry<Integer, Map<String, String>> entrada :
            // mapaNomePath.entrySet()) {
            // System.out.println("Arquivo: " + entrada.getKey() + ", path: " +
            // entrada.getValue());
            // }

            setMapaNomePath(mapaNomePath);

            for (Map.Entry<String, String> entrada : mapaNomePath.entrySet()) {
                System.out.println("Arquivo: " + entrada.getKey() + ", path: " + entrada.getValue());
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

    private List<String> preencherListaPalavrasChaves(String palavrasChaves) {
        String[] palavrasBusca = palavrasChaves.trim().toLowerCase().split(",");
        List<String> listaPalavras = new ArrayList<>();

        for (String palavraChave : palavrasBusca) {
            palavraChave = palavraChave.strip().trim().toLowerCase();
            if (!PALAVRAS_IGNORADAS.contains(palavraChave)) {
                listaPalavras.add(palavraChave);
                System.out.println("Palavra: " + palavraChave + " adicionada");
            } else {
                System.err.println("Palavra: " + palavraChave + " não adicionada");
            }
        }

        if (listaPalavras.isEmpty()) {
            return Arrays.asList("");
        }

        return listaPalavras;
    }
}
