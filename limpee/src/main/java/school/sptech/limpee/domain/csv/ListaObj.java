package school.sptech.limpee.domain.csv;

import school.sptech.limpee.domain.usuario.Usuario;

public class ListaObj<T> {
        private T[] vetor;
        private int nroElem;

        public ListaObj(int tamanho) {
            vetor = (T[]) new Object[tamanho];
            nroElem = 0;
        }

        public void adiciona(T elemento) {
            if (nroElem >= vetor.length) {
                System.out.println("Lista está cheia");
            }
            else {
                vetor[nroElem++] = elemento;
            }
        }

        public void exibe() {
            if (nroElem == 0) {
                System.out.println("\nA lista está vazia.");
            }
            else {
                System.out.println("\nElementos da lista:");
                for (int i = 0; i < nroElem; i++) {
                    System.out.println(vetor[i]);
                }
            }
        }

        public int busca(T elementoBuscado) {
            for (int i = 0; i < nroElem; i++) {
                if (vetor[i].equals(elementoBuscado)) {
                    return i;
                }
            }
            return -1;
        }

        public boolean removePeloIndice (int indice) {
            if (indice < 0 || indice >= nroElem) {
                System.out.println("\nIndice invalido!");
                return false;
            }

            for (int i = indice; i < nroElem-1; i++) {
                vetor[i] = vetor[i+1];
            }

            nroElem--;
            return true;
        }

        public boolean removeElemento(T elementoARemover) {
            return removePeloIndice(busca(elementoARemover));
        }

        public int getTamanho() {
            return nroElem;
        }

        public T getElemento(int indice) {
            if (indice < 0 || indice >= nroElem) {
                return null;
            }
            else {
                return vetor[indice];
            }
        }
        
        public void setElemento(int indice, T usuario){
            if (!(indice < 0 || indice >= nroElem)){
                vetor[indice] = usuario;
            }
        }

    public Usuario pesquisaBinaria(int ranking, ListaObj<Usuario> prestadores) {
        int inicio = 0;
        int meio = 0;
        int fim = nroElem;

        while (inicio <= fim) {
            meio = (fim + inicio) / 2;

            if (prestadores.getElemento(meio).getRanking() == ranking) {
                return prestadores.getElemento(meio);
            }
            else if (ranking < prestadores.getElemento(meio).getRanking()){
                fim = meio - 1;
            }
            else {
                inicio = meio + 1;
            }
        }

        return null;
    }

    public void limpa() {
            nroElem = 0;
        }
}
