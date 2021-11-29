package com.example.appllibres;

public class Llibre {

    public String titol;
    public Long data;
    public int valoracio;

    public Llibre(){

    }

    public Llibre(String titol, Long data, int valoracio){
        this.titol = titol;
        this.data = data;
        this.valoracio = valoracio;
    }

    public String getTitol(){
        return titol;
    }

    public Long getData(){
        return data;
    }

    public int getValoracio(){
        return valoracio;
    }

    public void setTitol(String titol){
        this.titol = titol;
    }

    public void setData(Long data){
        this.data = data;
    }

    public void setValoracio(int valoracio){
        this.valoracio = valoracio;
    }

}
