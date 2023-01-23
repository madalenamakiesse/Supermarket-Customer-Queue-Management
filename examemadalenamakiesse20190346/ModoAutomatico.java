/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examemadalenamakiesse20190346;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Madas
 */
public class ModoAutomatico extends Modo{
    
    private int intervaloTempoMaximo;

    public ModoAutomatico() {
        this.intervaloTempoMaximo=15;   
        ler = new Scanner(System.in);
        this.caixas = new ArrayList<Caixa>();
        this.idCliente = 1;
        this.aleatorio =  new Random();
        this.tempoAtProduto=5;
        this.nuCaixas=4;
        this.dados = new ArrayList<String>();
        this.leitura = null;
    }
    
    /**
     *
     */
    @Override
    protected void receberValoresFicheiro(){
        String valor = new String();
        String[] vAuxiliar;
        this.idCliente = 0;
        valor=this.dados.get(0);
        if(valor.startsWith("Tempo atendimento de um produto: ")){
            vAuxiliar = valor.split("Tempo atendimento de um produto: ");
            String[] vA = vAuxiliar[1].split("s");
            this.tempoAtProduto = Integer.parseInt(vA[0]);  
            valor=this.dados.get(1);
        }
        if(valor.startsWith("Número caixas: ")){
            vAuxiliar = valor.split("Número caixas: ");
            String[] vA = vAuxiliar[1].split(" ");
            this.nuCaixas = Integer.parseInt(vA[0]); 
            valor=this.dados.get(2);
        }
        if(valor.startsWith("Intervalo máximo entre clientes: ")){
            vAuxiliar = valor.split("Intervalo máximo entre clientes: ");
            String[] vA = vAuxiliar[1].split("s");
            this.intervaloTempoMaximo = Integer.parseInt(vA[0]);  
        }
        //Para cada caixa, vai guardar os dados que ela tem.
        int i = 3;
        int contNuCaixas = 0;
        while(contNuCaixas<=this.nuCaixas && i<this.dados.size()){
            valor=this.dados.get(i);
            if(valor.startsWith("Caixa: ")){
                contNuCaixas++;
                vAuxiliar = valor.split("Caixa: ");
                String[] vA = vAuxiliar[1].split(" ");
                int idCaixa = Integer.parseInt(vA[0]);  
                Caixa caixa = new Caixa(idCaixa); 
                this.caixas.add(caixa);
            }
            if(valor.startsWith("Clientes na fila: ")){
                vAuxiliar = valor.split("Clientes na fila: ");
                String[] vA = vAuxiliar[1].split(" ");
                this.caixas.get(contNuCaixas-1).setNuClienteFila(Integer.parseInt(vA[0]));
                this.idCliente+=Integer.parseInt(vA[0]);
            }
            if(valor.startsWith("Tempo restante para atender cliente topo: ")){
                vAuxiliar = valor.split("Tempo restante para atender cliente topo: ");
                String[] vA = vAuxiliar[1].split("s");
                this.caixas.get(contNuCaixas-1).setTempoRestAtenClienteTopo(Integer.parseInt(vA[0])); 
            }
            if(valor.startsWith("Clientes atendidos: ")){
                vAuxiliar = valor.split("Clientes atendidos: ");
                String[] vA = vAuxiliar[1].split(" ");
                this.caixas.get(contNuCaixas-1).setcAtendidos(Integer.parseInt(vA[0])); 
            }
            if(valor.startsWith("Tempo total atendimento: ")){
                vAuxiliar = valor.split("Tempo total atendimento: ");
                String[] vA = vAuxiliar[1].split(" ");
                this.caixas.get(contNuCaixas-1).setTempoTotalAten(Integer.parseInt(vA[0])); 
            }
            if(valor.startsWith("Tempo médio atendimento: ")){
                vAuxiliar = valor.split("Tempo médio atendimento: ");
                String[] vA = vAuxiliar[1].split(" ");
                this.caixas.get(contNuCaixas-1).setTempoMedAten(Integer.parseInt(vA[0])); 
            }
            ArrayList<Cliente> clientesAux = new ArrayList<Cliente>();
            if(valor.startsWith("    Cliente ")){
                int aux = this.caixas.get(contNuCaixas-1).getNuClienteFila();
                for(int nuClientesAux=0; nuClientesAux<aux; nuClientesAux++){
                    valor=this.dados.get(i);
                    int idClienteContador=0, produtos=0;
                    if(valor.startsWith("    Cliente ")){
                        vAuxiliar = valor.split("    Cliente ");
                        String[] vA = vAuxiliar[1].split(" ");
                        idClienteContador = Integer.parseInt(vA[0]); 
                        i++;
                        valor=this.dados.get(i);
                        if(valor.contains("produtos")){
                            vAuxiliar = valor.split(" ");
                            produtos = Integer.parseInt(vAuxiliar[vAuxiliar.length-2]); 
                            Cliente cliente = new Cliente(idClienteContador, produtos);
                            clientesAux.add(cliente);
                        }
                    }
                    i++;
                }
                this.caixas.get(contNuCaixas-1).setClientes(clientesAux);
                i--;
            }
            i++;
        }    
    }

    @Override
    protected void escreverFicheiro(String nomeFich) throws IOException{
        FileWriter ficheiro = new FileWriter(nomeFich);
        PrintWriter guardarFicheiro = new PrintWriter(ficheiro);
        
        guardarFicheiro.printf("Tempo atendimento de um produto: %ds\n", this.tempoAtProduto);
        guardarFicheiro.printf("Número caixas: %d\n", this.caixas.size());
        guardarFicheiro.printf("Intervalo máximo entre clientes: %ds\n\n", this.intervaloTempoMaximo);

        for(int i=0; i< caixas.size(); i++){
            guardarFicheiro.println(caixas.get(i).toString());
            for(int j=0; j<caixas.get(i).getNuClienteFila(); j++){
                guardarFicheiro.println(caixas.get(i).getClientes().get(j));
            }
            guardarFicheiro.println();
        }
        guardarFicheiro.close();
    }
    
    @Override
    public void controller() {
        //Antes de executar o programa, verifica se não existe um ficheiro
        try {
            abrirFicheiro("memoriaAutomatico.txt");
        } catch (IOException ex) {
            Logger.getLogger(ModoManual.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se o ficheiro for igual a null, ele pede os dados ao usuário
        if(this.dados.isEmpty()){
            int opO;
            System.out.println("\t\t\tEstá No Modo Automático!\n\tAntes de aceder ao menu, informe se gostaria de considerar os valores por omissão.\n\t\t1-\tSim\n\t\t2-\tNão");
            do{
                try{
                    opO = ler.nextInt();  
                }catch(Exception e){
                    System.out.println("Somente números inteiros são aceites. Foram considerados os valores por omissão.");
                    opO=1;
                    ler.nextLine();
                }  
                if(opO!=1 && opO!=2){
                    System.out.println("Opção fora do domínio. Tente novamente.");
                }
            }while(opO!=1 && opO!=2);        
            switch(opO){
                case 1:
                    for(int i=0; i<this.nuCaixas;i++){
                        this.op3();
                    }
                    this.programa();
                    break; 
                case 2:
                    //Leitura do tempo de atendimento de um produto. São considerados números positivos
                    System.out.print("\nTempo De Atendimento De Um Produto: ");
                    try{
                        this.tempoAtProduto = ler.nextInt();
                    }catch(Exception e){
                        System.out.println("Tempo Inválido, considerado 5 por omissão.");
                        this.tempoAtProduto=5;
                        ler.nextLine();
                    }finally{
                        if(this.tempoAtProduto<0){
                            System.out.println("Somente são aceites tempos positivos. Considerou-se 5 s por omissão.");
                            this.tempoAtProduto=5;
                        }
                        //Leitura do número de caixas. São considerados números positivos
                        System.out.print("Número De Caixas: ");
                        try{
                            this.nuCaixas = ler.nextInt();
                        }catch(Exception e){
                            System.out.println("Número De Caixas Inválido, considerado 4 por omissão.");
                            this.nuCaixas=4;
                            ler.nextLine();
                        }finally{
                            if(this.nuCaixas<0){
                                System.out.println("Somente são aceites número de caixas positivos. Considerou-se 4 por omissão.");
                                this.nuCaixas=4;
                            }
                            //Leitura do intervalo de tempo máximo entre clientes. São considerados números positivos
                            System.out.print("Intervalo de tempo máximo entre clientes: ");
                            try{
                                this.intervaloTempoMaximo=ler.nextInt();
                            }catch(Exception e){
                                System.out.println("Intervalo de tempo máximo entre clientes inválido, considerado 15 s por omissão.");
                                this.intervaloTempoMaximo=15;
                                ler.nextLine();
                            }finally{
                                if(this.nuCaixas<0){
                                    System.out.println("Somente são aceites intervalo de tempo máximo entre clientes positivos. Considerou-se 15 por omissão.");
                                    this.intervaloTempoMaximo=15;
                                }
                                for(int i=0; i<this.nuCaixas;i++){
                                    this.op3();
                                }
                                this.programa();
                            }
                        }
                    } 
                    break;
                } 
        }
        else{
            System.out.println("Recuperação dos dados anteriores realizada com sucesso.");
            this.programa();
        }
    }

    @Override
    protected void programa() {
        //Apresentação do menu e leitura da opção
        do{
            System.out.println("\n\nSeja Bem-Vindo(a) Ao Menu Do Modo Automático!\n\tPor favor, escolha uma opção.");
            this.opcoes();
            try{
               this.op = ler.nextInt(); 
            }catch(Exception e){
                System.out.println("Opcção inválida, somente são aceites números inteiros. Execute novamente o programa.");
                this.op=2;
                ler.nextLine();
            }
            switch(this.op){
                case 1:
                    this.op1();
                    this.op2();
                    this.op3();
                    this.op4();
                    this.op5();
                    break;
                case 2: 
                    this.op6();
                    break;
                default:
                    System.out.println("\tEscolheu uma opção fora do domínio. Tente de novo.\n");
            }
        }while(this.op!=2);
    }

    @Override
    protected void opcoes() {
        System.out.println("\t\t1-\tContinuar\n\t\t2-\tSair");
    }

    @Override
    protected void op2() {
        if(caixas.isEmpty()){
            System.out.println("Criou-se uma caixa para atender o cliente.");
            this.op3(); 
            this.op2();
        }
        else{
            int nprodutos;
            do{
                nprodutos = aleatorio.nextInt(121);
            }while(2>nprodutos || nprodutos>120);
            Cliente cliente = new Cliente(this.idCliente, nprodutos);
            this.idCliente++;
            int n = caixas.get(0).getNuClienteFila();
            int j = 0;
            for(int i =1; i< caixas.size(); i++){
                if(n>caixas.get(i).getNuClienteFila()){
                    n=caixas.get(i).getNuClienteFila();
                    j=i;
                }  
            }
            if(n==0){
                caixas.get(j).setTempoRestAtenClienteTopo(cliente.getNumeroProdutos()*this.tempoAtProduto);
            }
            caixas.get(j).setNuClienteFila(caixas.get(j).getNuClienteFila()+1);
            caixas.get(j).addCliente(cliente);
            System.out.println("Cliente criado com sucesso.");
        }
    }

    @Override
    protected void op5() {
        int x;
        //Gerar um número aleatório X
        do{
            x = aleatorio.nextInt(this.intervaloTempoMaximo+1);
        }while(1>x || x>this.intervaloTempoMaximo);     
        for(int i =0; i< caixas.size(); i++){
            int tt =  x, tempoRestante;
            do{
                tempoRestante = caixas.get(i).getTempoRestAtenClienteTopo();
                if(tt<tempoRestante){
                    caixas.get(i).setTempoTotalAten(caixas.get(i).getTempoTotalAten()+tt);
                    caixas.get(i).setTempoRestAtenClienteTopo(tempoRestante-tt);
                    tt-=tempoRestante;
                }
                else if(tt==tempoRestante){
                    caixas.get(i).setcAtendidos(caixas.get(i).getcAtendidos()+1);
                    caixas.get(i).setTempoTotalAten(caixas.get(i).getTempoTotalAten()+tt);
                    caixas.get(i).setTempoMedAten(caixas.get(i).getTempoTotalAten()/caixas.get(i).getcAtendidos());
                    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
                    clientes = caixas.get(i).getClientes();
                    if(clientes.size()!=0){
                        clientes.remove(0);
                        caixas.get(i).setClientes(clientes);
                        caixas.get(i).setNuClienteFila(clientes.size());
                        if(clientes.size()>0){
                            caixas.get(i).setTempoRestAtenClienteTopo(clientes.get(0).getNumeroProdutos()*this.tempoAtProduto);
                        }
                        else{
                            caixas.get(i).setTempoRestAtenClienteTopo(0);
                        } 
                    }
                    tt-=tempoRestante;
                }
                else{
                    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
                    clientes = caixas.get(i).getClientes();
                    if(clientes.size()!=0){
                        caixas.get(i).setcAtendidos(caixas.get(i).getcAtendidos()+1);
                        caixas.get(i).setTempoTotalAten(caixas.get(i).getTempoTotalAten()+tempoRestante);
                        caixas.get(i).setTempoMedAten(caixas.get(i).getTempoTotalAten()/caixas.get(i).getcAtendidos());
                        tt-=tempoRestante;
                        clientes.remove(0);
                        caixas.get(i).setClientes(clientes);
                        caixas.get(i).setNuClienteFila(clientes.size());
                        if(clientes.size()>0){
                            caixas.get(i).setTempoRestAtenClienteTopo(clientes.get(0).getNumeroProdutos()*this.tempoAtProduto);
                        }
                        else{
                            caixas.get(i).setTempoRestAtenClienteTopo(0);
                            tt=0;
                        }
                    }
                    else{
                        tt=0;
                    }
                }   
            }while(tt>0); 
        }
    }
    
    @Override
    protected void op6(){
        try {
            this.escreverFicheiro("memoriaAutomatico.txt");
        } catch (IOException ex) {
            Logger.getLogger(ModoManual.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.caixas = new ArrayList<Caixa>();
    }
    
}
