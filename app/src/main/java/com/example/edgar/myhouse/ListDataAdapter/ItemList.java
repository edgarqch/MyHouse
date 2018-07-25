package com.example.edgar.myhouse.ListDataAdapter;

/**
 * Created by edgar on 23-07-18.
 */

public class ItemList {
    private String facade;
    private String state;
    private String ubication;
    private String characteristics;
    private String price;
    public ItemList(String facade, String state, String ubication, String characteristics, String price){
        this.facade = facade;
        this.state = state;
        this.ubication = ubication;
        this.characteristics = characteristics;
        this.price = price;
    }
    public String getFacade(){
        return this.facade;
    }
    public String getState(){
        return this.state;
    }
    public String getUbication(){
        return this.ubication;
    }
    public String getCharacteristics(){
        return this.characteristics;
    }
    public String getPrice(){
        return this.price;
    }
}
