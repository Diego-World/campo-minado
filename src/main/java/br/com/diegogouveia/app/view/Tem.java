package br.com.diegogouveia.app.view;

import br.com.diegogouveia.app.modelo.Tabuleiro;

public class Tem {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(3,3,9);

        tabuleiro.registrarObservador(e -> {
            if(e.isGanhou()){
                System.out.println("ganhou :)");
            }else{
                System.out.println("Perdeu :(");
            }
        });

        tabuleiro.alternarMarcacao(0,0);
        tabuleiro.alternarMarcacao(0,1);
        tabuleiro.alternarMarcacao(0,2);
        tabuleiro.alternarMarcacao(1,0);
        tabuleiro.alternarMarcacao(1,1);
        tabuleiro.alternarMarcacao(1,2);
        tabuleiro.alternarMarcacao(2,0);
        tabuleiro.alternarMarcacao(2,1);

        tabuleiro.alternarMarcacao(2,2);
    }
}
