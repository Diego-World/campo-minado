package br.com.diegogouveia.app.modelo;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Campo {
    private boolean aberto;
    private boolean minado;
    private boolean marcado;
    private final int linha;
    private final int coluna;
    private List<Campo> vizinhos = new ArrayList<>();
    private Set<CampoObservador> observadores = new LinkedHashSet<>(); // Estrutura Set usada com a função de não permitir repetição
    public Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void registrarObservador(CampoObservador observador){
        observadores.add(observador);
    }

    private void notificarObservadores(CampoEvento evento){
        observadores.stream()
                .forEach(observador -> observador.eventoOcorreu(this,evento));
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

            if(marcado){
                notificarObservadores(CampoEvento.MARCAR);
            }else{
                notificarObservadores(CampoEvento.DESMARCAR);
            }
        }
    }

    public boolean abrir(){
        if(!aberto && !marcado){

            if(minado){
            notificarObservadores(CampoEvento.EXPLODIR);
                return true;
            }
            setAberto(true);
            if(vizinhacaSegura()){
                vizinhos.forEach(v->v.abrir());
            }
            return true;
        }else{
            return false;
        }
    }

    public boolean vizinhacaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public boolean isMarcado(){
        return marcado;
    }

     void setAberto(boolean aberto) {
        this.aberto = aberto;

        if(aberto){
            notificarObservadores(CampoEvento.ABRIR);
        }
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

    public int minasNaVizinhanca(){
        return (int) vizinhos.stream()
                .filter(v -> v.minado)
                .count();
    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
        notificarObservadores(CampoEvento.REINICIAR);
    }

}
