package com.hello.test0325.dbtable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name="\"200227item\"")
public class T200227item{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false)
    private int unioncode;
    @Column(updatable = false, insertable = false)
    private int marketcode;
    @Column
    private String itemname;
    @Column
    private String onlinechk;
    @Column
    private int price;
    @Column
    private String itempic;
    @Column
    private String itemtext;

    
  @ManyToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name ="marketcode")
  private T200227market join200227market;


    public T200227item(){
    }   
  

    @Builder
    public T200227item(int unioncode,int price, String itemname, String onlinechk,String itempic ,String itemtext ,T200227market market){
        // this.id = id;
        this.unioncode = unioncode;
        this.itemname = itemname;
        this.onlinechk = onlinechk;
        this.itempic = itempic;
        this.itemtext = itemtext;
        this.join200227market = market;
        this.price = price;

        System.out.println("builder >>3>"+this);
    }

}