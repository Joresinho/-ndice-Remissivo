package br.com.unifor;
import java.util.HashMap;
import java.util.LinkedList;

public class TabelaHash {
    private HashMap<Character, LinkedList<Palavra>> tabela;

    public TabelaHash() {
        tabela = new HashMap<>();
        // Inicializando a tabela hash com todas as letras do alfabeto
        for (char c = 'a'; c <= 'z'; c++) {
            tabela.put(c, new LinkedList<>());
        }
    }

    public void adicionarPalavra(String palavra, int linha) {
        char primeiraLetra = Character.toLowerCase(palavra.charAt(0));
        LinkedList<Palavra> lista = tabela.get(primeiraLetra);

        // Verifica se a palavra já está na lista encadeada
        for (Palavra p : lista) {
            if (p.getPalavra().equalsIgnoreCase(palavra)) {
                p.addOcorrencia(linha);
                return;
            }
        }

        // Se a palavra não existir, cria uma nova e adiciona à lista
        Palavra novaPalavra = new Palavra(palavra);
        novaPalavra.addOcorrencia(linha);
        lista.add(novaPalavra);
    }
    
    public void gerarIndiceRemissivo(String arquivoSaida, LinkedList<String> palavrasChave) throws Exception {
    	StringBuilder resultado = new StringBuilder();
    	
    	for (String chave : palavrasChave) {
    		char primeiraLetra = Character.toLowerCase(chave.charAt(0));
    		LinkedList<Palavra> lista = tabela.get(primeiraLetra);
    		
    		for (Palavra p : lista) {
    			if (p.getPalavra().equalsIgnoreCase(chave)) {
    				resultado.append(p.toString()).append("\n");
    			}
    		}
    	}
    	
    	java.nio.file.Files.write(java.nio.file.Paths.get(arquivoSaida), resultado.toString().getBytes());
    }

    public LinkedList<Palavra> buscarPalavrasChave(String palavraChave) {
        char primeiraLetra = Character.toLowerCase(palavraChave.charAt(0));
        return tabela.get(primeiraLetra);
    }

}
