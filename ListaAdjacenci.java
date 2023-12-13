package Grafos;
import java.util.*;

class Aresta{
	int origem;
	int destino;
	int peso;
	
	public Aresta(int origem, int destino, int peso) {
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}
}

public class ListaAdjacenci {
	private int nVertices;
	private List<List<Aresta>> adjacencias;
	private boolean direcionado;
	private int[] anterior;
	
	public ListaAdjacenci(int nVertices, boolean direcionado) {
		this.nVertices=nVertices;
		this.direcionado=direcionado;
		this.adjacencias=new ArrayList<>(nVertices);
		for(int i=0;i<nVertices;i++) {
			adjacencias.add(new ArrayList<Aresta>());
		}
		this.anterior = new int [nVertices];
	Arrays.fill(anterior, -1);
	}
	
	public void adicionarAresta(int u, int v, int peso) {
		Aresta aresta = new Aresta(u, v, peso);
		adjacencias.get(u).add(aresta);
		if(!direcionado) {
			Aresta arestaInvertida = new Aresta(v,u,peso);
			adjacencias.get(v).add(arestaInvertida);
		}
	}
	
	public void AdicionaAresta(int u, int v) {
		adicionarAresta(u, v, 1);
	}
	
	public void mostrarListaAdjacencias() {
		for(int i=0;i<nVertices;i++) {
			System.out.print("Vértice "+i+" : ");
			for(Aresta aresta : adjacencias.get(i)) {
				System.out.print("("+aresta.destino+", Peso: "+aresta.peso+")");
			}
			System.out.println();
		}
	}
	
	public void removerAresta(int u, int v) {
		List<Aresta>arestaU=adjacencias.get(u);
		for(Aresta aresta : arestaU) {
			if(aresta.destino==v) {
				arestaU.remove(aresta);
				break;
			}
		}
		if(!direcionado) {
			List<Aresta>arestaV=adjacencias.get(v);
			for(Aresta aresta : arestaV) {
				if(aresta.destino==u) {
					arestaV.remove(aresta);
					break;
				}
			}
		}
	}
	
	public void mostraListaUnicoVertice(int num) {
		//int vertice;
		for(Aresta aresta : adjacencias.get(num)) {
			System.out.print("("+aresta.destino+", Peso: "+aresta.peso+")");
		}
		System.out.println();
	}
	
	public boolean saoAdjacentes(int u, int v) {
		for(Aresta aresta : adjacencias.get(u)) {
			if(aresta.destino==v) {
				return true;
			}
		}
		return false;
	}

	public void dijkstra(int origem, int destino) {
        int[] distancia = new int[nVertices];
        int[] predecessores = new int[nVertices];
        boolean[] visitado = new boolean[nVertices];
        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[origem] = 0;

        PriorityQueue<Node> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(node -> node.distancia));
        filaPrioridade.add(new Node(origem, 0));

        while (!filaPrioridade.isEmpty()) {
            int u = filaPrioridade.poll().vertice;
            if (visitado[u]) continue;

            visitado[u] = true;

            for (Aresta aresta : adjacencias.get(u)) {
                int v = aresta.destino;
                int pesoAresta = aresta.peso;

                if (!visitado[v] && distancia[u] != Integer.MAX_VALUE && distancia[u] + pesoAresta < distancia[v]) {
                    distancia[v] = distancia[u] + pesoAresta;
                    predecessores[v] = u;
                    filaPrioridade.add(new Node(v, distancia[v]));
                }
            }
        }

        System.out.println("Distâncias mínimas a partir da origem " + origem + ":");
        for (int i = 0; i < nVertices; i++) {
            System.out.println("Para o vértice " + i + ": " + distancia[i]);
        }

        System.out.println("Caminho mínimo para o vértice " + destino + ": " + obterCaminhoMinimo(origem, destino, predecessores));
    }

    private List<Integer> obterCaminhoMinimo(int origem, int destino, int[] predecessores) {
        List<Integer> caminho = new ArrayList<>();
        for (int atual = destino; atual != origem; atual = predecessores[atual]) {
            caminho.add(atual);
        }
        caminho.add(origem);
        Collections.reverse(caminho);
        System.out.println("Peso : ");
        return caminho;
    }

    static class Node {
        int vertice;
        int distancia;

        public Node(int vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }
    }
    
    public boolean verificarCompleto() {
        int numArestasEsperado = nVertices * (nVertices - 1) / 2;
        int numArestasAtual = 0;

        for (List<Aresta> arestas : adjacencias) {
            numArestasAtual += arestas.size();
        }

        return numArestasAtual == numArestasEsperado;
    }
    public boolean verificarConexo() {
        boolean[] visitado = new boolean[nVertices];
        int verticeInicial = 0; 

        Arrays.fill(visitado, false);

        dfs(verticeInicial, visitado);

        for (boolean v : visitado) {
            if (!v) {
                return false; 
            }
        }

        return true;
    }

    private void dfs(int vertice, boolean[] visitado) {
        visitado[vertice] = true;

        for (Aresta aresta : adjacencias.get(vertice)) {
            int destino = aresta.destino;
            if (!visitado[destino]) {
                dfs(destino, visitado);
            }
        }
    }
    
    public void verificarEuleriano() {
        int verticesImpares = 0;

        for (List<Aresta> arestas : adjacencias) {
            int grau = arestas.size();

            if (grau % 2 != 0) {
                verticesImpares++;
            }
        }

        if (verticesImpares == 0) {
            System.out.println("O grafo é euleriano.");
        } else if (verticesImpares == 2) {
            System.out.println("O grafo é semieuleriano.");
        } else {
            System.out.println("O grafo não é euleriano nem semieuleriano.");
        }
    }
    
    public void verificarHamiltoniano() {
        boolean[] visitado = new boolean[nVertices];
        Arrays.fill(visitado, false);

        if (verificarCaminhoHamiltoniano(0, visitado, 1)) {
            System.out.println("O grafo é hamiltoniano.");
        } else {
            System.out.println("O grafo não é hamiltoniano.");
        }
    }

    public void verificarSemiHamiltoniano() {
        boolean[] visitado = new boolean[nVertices];
        Arrays.fill(visitado, false);

        for (int i = 0; i < nVertices; i++) {
            if (verificarCaminhoHamiltoniano(i, visitado, 1)) {
                System.out.println("O grafo é semi-hamiltoniano.");
                return;
            }

            Arrays.fill(visitado, false);
        }

        System.out.println("O grafo não é semi-hamiltoniano.");
    }

    private boolean verificarCaminhoHamiltoniano(int vertice, boolean[] visitado, int contador) {
        visitado[vertice] = true;

        if (contador == nVertices) {
            return true;
        }

        for (Aresta aresta : adjacencias.get(vertice)) {
            int destino = aresta.destino;

            if (!visitado[destino]) {
                if (verificarCaminhoHamiltoniano(destino, visitado, contador + 1)) {
                    return true;
                }
            }
        }

        visitado[vertice] = false;
        return false;
    }
    
    public List<Integer> dijkstra2(int origem, int destino) {
        Queue<Integer> fila = new LinkedList<>();
        int[] distancias = new int[nVertices];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origem] = 0;
        fila.add(origem);

        while (!fila.isEmpty()) {
            int u = fila.poll();

            for (Aresta aresta : adjacencias.get(u)) {
                int v = aresta.destino;
                int pesoUV = aresta.peso;

                if (distancias[u] != Integer.MAX_VALUE && distancias[u] + pesoUV < distancias[v]) {
                    distancias[v] = distancias[u] + pesoUV;
                    fila.add(v);
                    anterior[v] = u;
                }
            }
        }

        List<Integer> caminhoMaisCurto = new ArrayList<>();
        int atual = destino;
        int pesoTotal = distancias[destino];
        while (atual != -1) {
            caminhoMaisCurto.add(atual);
            atual = anterior[atual];
        }
        Collections.reverse(caminhoMaisCurto);

        System.out.println("Peso Total: " + pesoTotal);
        return caminhoMaisCurto;
    }
	
}


