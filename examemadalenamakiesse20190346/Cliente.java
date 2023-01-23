/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examemadalenamakiesse20190346;

/**
 *
 * @author Madas
 */
public class Cliente {
    private int id;
    private int numeroProdutos;

    public Cliente(int id, int numeroProdutos) {
        this.id = id;
        this.numeroProdutos = numeroProdutos;
    }

    public int getId() {
        return id;
    }

    public int getNumeroProdutos() {
        return numeroProdutos;
    }

    @Override
    public String toString() {
        return "    Cliente " + id + "\n    " + numeroProdutos + " produtos";
    }
    
}
