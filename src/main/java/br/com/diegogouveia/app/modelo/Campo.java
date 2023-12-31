package br.com.diegogouveia.app.modelo;

import br.com.diegogouveia.app.exceptions.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {
    private boolean aberto;
    private boolean minado;
    private boolean marcado;
    private final int linha;
    private final int coluna;
    private List<Campo> vizinhos = new ArrayList<>();
    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public boolean adicionarVizinho(Campo vizinho){
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna+deltaLinha;

        if(deltaGeral == 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        }else{
            return false;
        }
    }

    public void alternarMarcacao(){
        if(!aberto){
            marcado = !marcado;
        }
    }

    public boolean abrir(){
        if(!aberto && !marcado){
            aberto = true;

            if(minado){
            throw new ExplosaoException();
            }

            if(vizinhacaSegura()){
                vizinhos.forEach(v->v.abrir());
            }
            return true;
        }else{
            return false;
        }
    }

    boolean vizinhacaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public boolean isMarcado(){
        return marcado;
    }

     void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public void minar(){
        minado = true;
    }
    public boolean isMinado(){
        return minado;
    }
    public boolean isAberto(){
        return aberto;
    }
    public boolean isFechado(){
        return !isAberto();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    boolean objeivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = marcado && minado;
        return desvendado || protegido;
    }

    long minasNaVizinhanca(){
        return vizinhos.stream()
                .filter(v -> v.minado)
                .count();
    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString(){

        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";

        if(marcado){
            return "X";
        }else if (aberto && minado){
            return  "*";
        }else if(aberto && minasNaVizinhanca() <= 1) {
            return RED + minasNaVizinhanca() + RESET;
        }else if(aberto && minasNaVizinhanca() == 2) {
            return GREEN + minasNaVizinhanca() + RESET;
        } else if (aberto && minasNaVizinhanca() == 3) {
            return YELLOW + minasNaVizinhanca() + RESET;
        }else if(aberto){
            return " ";
        }else{
            return "?";
        }
    }
}
