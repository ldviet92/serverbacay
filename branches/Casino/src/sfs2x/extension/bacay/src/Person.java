/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

/**
 *
 * @author leduc
 */
public class Person {
    private Integer Id;
    private  String name;
    private Float Bet_money;

    public Person(Integer Id, String Name, Float Bet_money) {
        this.Id = Id;
        this.name = name;
        this.Bet_money = Bet_money;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    
}
