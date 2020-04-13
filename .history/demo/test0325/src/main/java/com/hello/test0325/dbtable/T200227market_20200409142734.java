package com.hello.test0325.dbtable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// @Getter
// @Setter
@Data
@Entity
@Table(name="\"200227market\"")
public class T200227market{
    @Id
    @GeneratedValue
    private int marketcode;
    private String memid;
    private String marketname;
    private String marketpic;
    private String markettext;
    
  
    @ManyToOne
    @JoinColumn(name = "memid", insertable = false, updatable = false)
    private T200227member t200227member;


    public T200227market(){   
    }

    @Builder
    public T200227market(int marketcode,String memid,String marketname, String marketpic,String markettext){
        // this.id = id;
        
        this.marketcode = marketcode;
        this.memid = memid;
        this.marketname = marketname;
        this.marketpic = marketpic;         
        this.markettext = markettext;
        // T200227member member = new T200227member();
        // this.t200227member = member.getT200227member();

        System.out.println("builder >>2>"+this);
    }

}