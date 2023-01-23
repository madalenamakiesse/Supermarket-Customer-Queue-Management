/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examemadalenamakiesse20190346;

import java.util.ArrayList;

/**
 *
 * @author Madas
 */
public class Caixa {
    /* 
        cAtendidos: clientes atendidos
        clientes: lista de clientes
        id: identificador da caixa
        nuClienteFila: número de clientes na fila
        tempoMedAten: tempo médio de atendimento
        tempoRestAtenClienteTopo: tempo restante para atender cliente topo
        tempoTotalAten: tempo total de atendimento
    */
    private int cAtendidos;
    private ArrayList<Cliente> clientes;
    private int id;
    private int nuClienteFila;
    private int tempoMedAten;
    private int tempoRestAtenClienteTopo;
    private int tempoTotalAten;

    public Caixa(int id) {
        this.cAtendidos=0;
        this.clientes = new ArrayList<Cliente>();
        this.id = id;
        this.nuClienteFila = 0;
        this.tempoMedAten=0;
        this.tempoRestAtenClienteTopo=0;
        this.tempoTotalAten=0;      
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public int getNuClienteFila() {
        return nuClienteFila;
    }
    
    public void addCliente(Cliente cliente){
        this.clientes.add(cliente);
    }

    public void setTempoRestAtenClienteTopo(int tempoRestAtenClienteTopo) {
        this.tempoRestAtenClienteTopo = tempoRestAtenClienteTopo;
    }

    public int getTempoRestAtenClienteTopo() {
        return tempoRestAtenClienteTopo;
    }

    public int getTempoTotalAten() {
        return tempoTotalAten;
    }

    public void setTempoTotalAten(int tempoTotalAten) {
        this.tempoTotalAten = tempoTotalAten;
    }

    public int getcAtendidos() {
        return cAtendidos;
    }

    public void setcAtendidos(int cAtendidos) {
        this.cAtendidos = cAtendidos;
    }

    public void setTempoMedAten(int tempoMedAten) {
        this.tempoMedAten = tempoMedAten;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setNuClienteFila(int nuClienteFila) {
        this.nuClienteFila = nuClienteFila;
    }
    

    @Override
    public String toString() {
        return "Caixa: " + this.id + "\nClientes na fila: "+this.nuClienteFila+"\nTempo restante para atender cliente topo: "+this.tempoRestAtenClienteTopo+"s\nClientes atendidos: "+this.cAtendidos+"\nTempo total atendimento: "+this.tempoTotalAten+" seg\nTempo médio atendimento: "+this.tempoMedAten+" seg";
    }  
}
