package br.com.diegogouveia.app;

import br.com.diegogouveia.app.modelo.Tabuleiro;
import br.com.diegogouveia.app.view.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6,10,8);
        new TabuleiroConsole(tabuleiro);
    }
}