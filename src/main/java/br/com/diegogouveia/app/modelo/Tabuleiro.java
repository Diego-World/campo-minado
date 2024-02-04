package br.com.diegogouveia.app.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Tabuleiro implements CampoObservador{
    private final int LINHAS;
    private final int COLUNAS;
    private final int MINAS;
    private final List<Campo> campos = new ArrayList<>();
    private List<Consumer<ResultadoEvento>> observadores = new ArrayList<>();

    public Tabuleiro(int LINHAS, int COLUNAS, int MINAS) {
        this.LINHAS = LINHAS;
        this.COLUNAS = COLUNAS;
        this.MINAS = MINAS;

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
        for (int linha = 0; linha < LINHAS; linha++) {
            for (int coluna = 0; coluna < COLUNAS; coluna++) {
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
        }while (minasArmadas < MINAS);
    }

    public int getMinas() {
        return MINAS;
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
        return LINHAS;
    }

    public int getColunas() {
        return COLUNAS;
    }
    //Funcao que percorre todos os campos e possibilita uma função, na sua implementação uma lambda.
    //Recebendo o metodo add(). *Para entender a lógica acesse o uso*
    public void paraCadaCampo(Consumer<Campo> funcao){
        campos.forEach(funcao);
    }
}
