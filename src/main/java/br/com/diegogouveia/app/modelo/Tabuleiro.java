package br.com.diegogouveia.app.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador{
    private final int linhas;
    private final int colunas;
    private final int minas;
    private final List<Campo> campos = new ArrayList<>();
    private List<Consumer<ResultadoEvento>> observadores = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    public void registrarObservador(Consumer<ResultadoEvento> observador){
        observadores.add(observador);
    }

    private void notificarObservadores(boolean resultado){
        observadores.stream()
                .forEach(observador -> observador.accept(new ResultadoEvento(resultado)));
    }

    public void abrir(int linha, int coluna){

            campos.parallelStream()
                    .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                    .findFirst()
                    .ifPresent(campo -> campo.abrir());
    }

    private void mostrarMinas(){
        campos.stream()
                .filter(c->c.isMinado())
                .forEach( c-> c.setAberto(true));
    }

    public void alternarMarcacao(int linha, int coluna){
        campos.parallelStream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(campo -> campo.alternarMarcacao());
    }

    private void gerarCampos() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                Campo campo = new Campo(linha,coluna);
                campo.registrarObservador(this);
                campos.add(campo);
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
        long minasArmadas = 0;
        Predicate<Campo> minado = c -> c.isMinado();
        do{
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();
        }while (minasArmadas < minas);
    }

    public int getMinas() {
        return minas;
    }

    public boolean objeivoAlcancado(){
        return campos.stream().allMatch(campo -> campo.objeivoAlcancado());
    }

    public void reiniciarJogo(){
        campos.stream().forEach(campo -> campo.reiniciar());
        sortearMinas();
    }

    @Override
    public void eventoOcorreu(Campo c, CampoEvento e) {
        if(e == CampoEvento.EXPLODIR){
            mostrarMinas();
            notificarObservadores(false);
        }else if(objeivoAlcancado()){
            notificarObservadores(true);
        }
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }
    //Funcao que percorre todos os campos e possibilita uma função, na sua implementação uma lambda.
    //Recebendo o metodo add(). *Para entender a lógica acesse o uso*
    public void paraCadaCampo(Consumer<Campo> funcao){
        campos.forEach(funcao);
    }
}
