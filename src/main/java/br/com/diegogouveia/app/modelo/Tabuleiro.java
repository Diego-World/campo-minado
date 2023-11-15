package br.com.diegogouveia.app.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private int minas;
    private final List<Campo> campos = new ArrayList<>();
    public Tabuleiro(){

    }
    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }
    private void gerarCampos() {
        for (int linha = 0; linha < linhas; i++) {
            for (int coluna = 0; coluna < colunas; j++) {
                campos.add(new Campo(linha,coluna));
            }
        }
    }
    private void associarVizinhos() {
        for (Campo c1: campos) {
            for (Campo c2:campos) {
                c1.adicionarVizinho(c2);
            }
        }
    }
    private void sortearMinas() {
    }
}
