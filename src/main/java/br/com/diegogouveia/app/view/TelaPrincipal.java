package br.com.diegogouveia.app.view;

import br.com.diegogouveia.app.modelo.Tabuleiro;

import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal(){
        Tabuleiro tabuleiro = new Tabuleiro(16,30,1);
        add(new PainelTabuleiro(tabuleiro));

        setVisible(true); // Tornar a janela visível
        setTitle("Campo Minado"); // Cria o título da janela
        setLocationRelativeTo(null); // Define a posição da janela em relação ao monitor, se nulo a posição será o centro
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Define a operação após o fechamento da janela, nesse caso mata o programa
        setSize(690,438); // Define o tamanho da janela
    }

    public static void main(String[] args) {

        new TelaPrincipal();
    }
}
