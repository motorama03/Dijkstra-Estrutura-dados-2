package Grafos;

public class TesteListaAdjacenci {
	public static void main(String[] args) {
		
		ListaAdjacenci grafo = new ListaAdjacenci(7, false);
		
		// Adicionando arestas ao grafo
		grafo.adicionarAresta(1, 2, 15);
		grafo.adicionarAresta(1, 3, 9);
		grafo.adicionarAresta(3, 2, 4);
		grafo.adicionarAresta(3, 4, 3);
		grafo.adicionarAresta(3, 5, 16);
		grafo.adicionarAresta(4, 5, 6);
		grafo.adicionarAresta(4, 6, 21);
		grafo.adicionarAresta(5, 6, 7);
		
		// Mostrando a lista de Adjacencias
		grafo.mostrarListaAdjacencias();
		// Mostrando aresta de um vertice especifico
		System.out.println("Aresta de um vertice especifico");
		grafo.mostraListaUnicoVertice(1);
		// Removendo
		System.out.println("Após remoção: ");
		grafo.removerAresta(2, 1);
		// Mostrando a lista após remoção
		grafo.mostrarListaAdjacencias();
		System.out.println(grafo.dijkstra2(1, 4));
		// Verifica se é Euleriano
		grafo.verificarEuleriano();
		// Verifica se é Hamiltoniano
		grafo.verificarHamiltoniano();
		
		ListaAdjacenci grafo2  = new ListaAdjacenci(19, false);
		grafo2.adicionarAresta(1, 4, 260);
		grafo2.adicionarAresta(1, 2, 170);
		grafo2.adicionarAresta(2, 4, 135);
		grafo2.adicionarAresta(2, 3, 80);
		grafo2.adicionarAresta(3, 5, 150);
		grafo2.adicionarAresta(3, 7, 120);
		grafo2.adicionarAresta(3, 6, 100);
		grafo2.adicionarAresta(4, 5, 50);
		grafo2.adicionarAresta(5, 8, 130);
		grafo2.adicionarAresta(5, 7, 70);
		grafo2.adicionarAresta(6, 9, 80);
		grafo2.adicionarAresta(7, 9, 200);
		grafo2.adicionarAresta(8, 12, 70);
		grafo2.adicionarAresta(9, 12, 160);
		grafo2.adicionarAresta(9, 11, 100);
		grafo2.adicionarAresta(12, 13, 80);
		grafo2.adicionarAresta(12, 10, 80);
		grafo2.adicionarAresta(12, 11, 160);
		grafo2.adicionarAresta(11, 12, 80);
		grafo2.adicionarAresta(11, 15, 150);
		grafo2.adicionarAresta(11, 16, 200);
		grafo2.adicionarAresta(12, 15, 110);
		grafo2.adicionarAresta(13, 12, 100);
		grafo2.adicionarAresta(13, 14, 70);
		grafo2.adicionarAresta(14, 17, 50);
		grafo2.adicionarAresta(14, 18, 80);
		grafo2.adicionarAresta(14, 15, 120);
		grafo2.adicionarAresta(15, 17, 100);
		grafo2.adicionarAresta(15, 16, 140);
        grafo2.adicionarAresta(17, 18, 50);
        
        //System.out.println(grafo2.dijkstra2(5, 17));
	}
}
