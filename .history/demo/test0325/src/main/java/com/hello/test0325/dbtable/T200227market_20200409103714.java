package com.hello.test0325.dbtable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
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
    
    @ManyToOne(name = "memid", insertable = false, updatable = false)
    @JoinColumnsOrFormulas(value = {
        @JoinColumnOrFormula(column =
        @JoinColumn(name = "memid",
                referencedColumnName = "memid")),
        
})
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
        this.t200227member = T200227member.builder()
        .username("zzang22yn")
        .build();

        System.out.println("builder >>>"+this);
    }

}