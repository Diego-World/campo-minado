package br.com.diegogouveia.app.view;

import br.com.diegogouveia.app.modelo.Tabuleiro;

import javax.swing.*;
import java.awt.*;

// Panel é um componente que agrupa outros componentes
public class PainelTabuleiro extends JPanel {
    public PainelTabuleiro(Tabuleiro tabuleiro){
        setLayout(new GridLayout(
                tabuleiro.getLinhas(), tabuleiro.getColunas())); // Como os componentes são organizados dentro da tela, GRID = Grade (Linhas e colunas)

        // A lógica aqui é associar os campos do tabuleiro a um botão, então FOREACH(Campo) campo-> add(new Botão(campo)) // Adicione um botão para esse campo
        tabuleiro.paraCadaCampo(c->add(new BotaoCampo(c)));

        tabuleiro.registrarObservador(e->{
            // TODO mostrar resultado pro usuario!
        });
    }
}