package school.sptech.limpee.domain.csv;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;

public class FilaObj<T> {
    private int tamanho;
    private T[] fila;

    public FilaObj(int capaciade) {
        tamanho = 0;
        fila = (T[]) new Object[capaciade];
    }

    @JsonIgnore
    public boolean isEmpty() {
        return tamanho == 0;
    }
    @JsonIgnore
    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if (isFull())
            throw new IllegalStateException("Fila está cheia");

        fila[tamanho++] = info;
    }

    public T peek() {
        return fila[0];
    }

    public T poll() {
        T aux = fila[0];
        for (int i = 0; i < tamanho; i++) {
            if (i == tamanho - 1) {
                fila[i] = null;
            } else {
                fila[i] = fila[i + 1];
            }
        }
        tamanho--;
        return aux;
    }

    public void exibe() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print(fila[i] + " ");
        }
    }

    public int getTamanho(){
        return tamanho;
    }

    public T[] getFila() {
        return fila;
    }
}
