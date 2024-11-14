package br.com.unifor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        try {
            // Carregar as palavras-chave
            List<String> linhasPalavrasChave = Files.readAllLines(Paths.get("arquivos/palavras_chave.txt"));
            LinkedList<String> palavrasChave = new LinkedList<>(linhasPalavrasChave);

            // Carregar o texto a ser pesquisado
            List<String> linhasTexto = Files.readAllLines(Paths.get("arquivos/texto.txt"));

            TabelaHash tabelaHash = new TabelaHash();

            // busca o texto e preencher a tabela hash
            for (int i = 0; i < linhasTexto.size(); i++) {
                String[] palavrasLinha = linhasTexto.get(i).split("\\W+"); 

                for (String palavra : palavrasLinha) {
                    if (!palavra.isEmpty()) {
                        tabelaHash.adicionarPalavra(palavra, i + 1);  // Adiciona a palavra e a linha (i+1)
                    }
                }
            }

            tabelaHash.gerarIndiceRemissivo("arquivos/indice_remissivo.txt", palavrasChave);

            System.out.println("Arquivo de indice gerado com sucesso!");

        } catch (Exception e) {
        	e.getMessage();
            e.printStackTrace();
        }
    }
}
