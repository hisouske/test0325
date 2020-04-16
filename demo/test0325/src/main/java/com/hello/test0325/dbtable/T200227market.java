package com.hello.test0325.dbtable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false)
    private int marketcode;
    @Column(name = "memid", updatable = false, insertable = false)
    private String username;
    @Column
    private String marketname;
    @Column
    private String marketpic;
    @Column
    private String markettext;
    
  @ManyToOne(cascade = CascadeType.ALL,optional = true, fetch = FetchType.LAZY)
  @JoinColumn(name ="memid")
  private T200227member join200227member;


  @OneToMany(mappedBy="join200227market") //연관관계에있어서 주인이 아님 수정삭제 등 갱신 불가
  private List<T200227item> t200227items = new ArrayList<>();

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