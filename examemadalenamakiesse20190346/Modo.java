/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examemadalenamakiesse20190346;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 *
 * @author Madas
 */
public abstract class Modo {
    /*
        op: opção
        tempoAtProduto: tempo de atendimento de um produto
        nuCaixas: número de caixas
        idCliente: guarda o id para o próximo cliente a ser criado.
    */
    protected int op;
    protected Scanner ler;
    protected int tempoAtProduto;
    protected int nuCaixas;
    protected int idCliente;
    protected Random aleatorio;
    protected ArrayList<Caixa> caixas; 
    protected BufferedReader leitura;
    protected ArrayList<String> dados;
    
    /**
     *
     */
    public abstract void controller();
    
    /**
     *
     */
    protected abstract void programa();
    
    protected void op1(){
        if(caixas.isEmpty()){
            System.out.println("Temporariamente, ainda não existem caixas disponíveis. Crie uma caixa e teste novamente.");
        }
        else{
            for(Caixa c : this.caixas){
                System.out.println(c.toString());
                for(Cliente cliente : c.getClientes()){
                    System.out.println(cliente.toString());
                }
                System.out.println("");
            }
        }
    }
    
    protected abstract void op2();
    
    protected void op3(){
        Caixa caixa = new Caixa(this.caixas.size() + 1);
        this.caixas.add(caixa);
        System.out.println("Caixa adicionada com sucesso.");
    }
    
    protected void op4(){
        if(this.caixas.isEmpty()){
            System.out.println("Temporariamente, a operação não pode ser executada.Crie uma caixa e teste novamente.");
        }
        else{
            Predicate <Caixa> condicao = caixa -> caixa.getNuClienteFila()==0;
            this.caixas.removeIf(condicao);
            System.out.println("Caixas retiradas com sucesso.");
        }
    }
    
    protected void abrirFicheiro(String nomeFich) throws IOException{
        File ficheiro = new File (nomeFich);
        try{
            this.leitura = new BufferedReader (new FileReader(nomeFich));
        }catch(IOException e){
            System.out.println("Não existe ficheiro. Continuar a executar...\n");
        }
        //Guarda-se todos os dados do ficheiro num array de string para facilitar a manipulação
        if(this.leitura!=null){
            String linha = this.leitura.readLine();
            while(linha!=null){
                dados.add(linha);
                linha = this.leitura.readLine();
            }
            this.receberValoresFicheiro();
        }
        
    }
    
    protected abstract void op5();
    
    protected abstract void op6();
    
    protected abstract void opcoes(); 
    protected abstract void escreverFicheiro(String nomeFich) throws IOException;
    protected abstract void receberValoresFicheiro();
}
