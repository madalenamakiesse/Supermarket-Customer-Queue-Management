/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examemadalenamakiesse20190346;

import java.util.Scanner;

/**
 *
 * @author Madas
 */
public class Controller {
    private int op;
    private ModoManual manualController;
    private ModoAutomatico automaticoController;
    
    public Controller() {
        this.manualController = new ModoManual();
        this.automaticoController = new ModoAutomatico();
    }
     
    public void menu(){
        Scanner ler = new Scanner(System.in);
        do{
            System.out.println("\nSeja Bem-Vindo(a) À Aplicação De Gestão Das Filas Do Supermercado!\n\tPor favor, escolha uma opção.");
            this.opcoes();
            try{
                this.op = ler.nextInt();
            }catch(Exception e){
                this.op=3;
                System.out.println("Somente números inteiros positivos são aceites. Execute novamente o programa.");
                ler.nextLine();
            }
            switch(this.op){
                case 1:
                    this.manualController.controller();
                    this.manualController = new ModoManual();
                    break;
                case 2: 
                    this.automaticoController.controller();
                    this.automaticoController = new ModoAutomatico();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("\tEscolheu uma opção fora do domínio. Tente de novo.\n");
            }
        }while(this.op!=3);
    }
    
    private void opcoes(){
        System.out.println("\t\t1-\tModo Manual\n\t\t2-\tModo Automático\n\t\t3-\tSair");
    }  
}
