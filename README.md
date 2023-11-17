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

## Como Jogar
#### Requisitos: Java e IDE ( Recomendo o Intelij )

Faça o clone do repositório, execute a aplicação e digite as coodernadas!

**X** = Para a Linha

**Y** = Para a coluna

Após digitar as coodernadas, selecione se deseja Abrir o campo ou Marcar/Desmarcar

**1** = Abrir Campo

**2** = Marcar/Desmarcar

## Como configurar seu jogo
Na classe *Aplicacao* é onde está o construtor do tabuleiro do campo minado, você pode difinir através desse construtor o número de linhas, colunas e minas!

Por Exemplo: 
> Tabuleiro(6, 10, 8);
> > O tabuleiro tem 6 Linhas, 10 Colunas e 8 minas

>Tabuleiro(7, 8, 12);
> > O tabuleiro tem 7 Linhas, 8 Colunas e 12 minas
