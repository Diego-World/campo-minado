# Projeto de estudo desenvolvido para console

## Objetivo do programa

Esse é uma versão do jogo Campo-Minado desenvolvido durante a formação do curso Java-pro
ministrado pelo professor Leonardo Moura, link do curso: udemy.com/course/fundamentos-de-programacao-com-java/

## Tecnologias

#### Linguagem: Java
#### Paradigmas: Programação orientada a objetos e Funcional (Lambdas e Streams)
#### Tools: jUnit5
#### IDE: Intelij

## Principais aprendizados

-> **StreamAPI**:

Coloquei em prática o uso do *StreamApi* implementado no Java 8, que torna o código muito mais simples porque é possível utilizar das interfaces funcionais como o *Predicate* 
para aplicar filtros em um Stream de dados, passando a responsabilidade do processamento interno para a própia linguagem.

-> **Testes Unitários**:

Utilizei nesse projeto o *jUnit5* para criar testes unitários para os metódos da classe Campo, os testes garantem que partes importantes da aplicação estão funcionando corretamente
o que torna a aplicação mais robusta e profissional.

-> **Java Swing**:

Biblioteca para desenvolvimento de interfaces desktop presente no pacote do Java.

-> **Padrão de projeto: OBSERVER**:

Para trabalhar com as ações dentro do jogo como abrir um campo, marcar, desmarcar, explodir uma mina e reiniciar, utilizei o padrão observer para notificar
os respectivos observadores sobre o principal evento dentro do campo minado, o pressionamento do mouse.

## Como Jogar
#### Requisitos: Java e IDE ( Recomendo o Intelij )

Na classe *Tela Principal* é onde você pode configurar a quantidade de linhas, colunas e minas!

Por Exemplo: 
> Tabuleiro(6, 10, 8);
> > O tabuleiro tem 6 Linhas, 10 Colunas e 8 minas

>Tabuleiro(7, 8, 12);
> > O tabuleiro tem 7 Linhas, 8 Colunas e 12 minas
> 
> 

