package com.hello.test0325.dbtable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name="\"200227market\"")
public class T200227market{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int marketcode;
    @Column(name = "memid")
    private String username;
    @Column
    private String marketname;
    @Column
    private String marketpic;
    @Column
    private String markettext;
    
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinTable(name = "PARENT_CHILD",
    joinColumns = @JoinColumn(name = "marketcode",insertable = false, updatable = false),
    inverseJoinColumns = @JoinColumn(name = "memid",insertable = false, updatable = false))
  private T200227member join200227member;

    // @ManyToOne(targetEntity = T200227member.class, fetch =FetchType.LAZY)
    // @JoinColumn(name = "memid", insertable = false, updatable = false)
    // private T200227member t200227member;


    public T200227market(){   
    }

    @Builder
    public T200227market(int marketcode,String memid,String marketname, String marketpic,String markettext,T200227member member){
        // this.id = id;
        
        this.marketcode = marketcode;
       // this.memid = memid;
        this.marketname = marketname;
        this.marketpic = marketpic;         
        this.markettext = markettext;
        // T200227member member = new T200227member();
        this.join200227member = member;

        System.out.println("builder >>2>"+this);
    }

}