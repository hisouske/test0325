package com.hello.test0325.dbtable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

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

        @Builder
        public T200227market(String username,String password,int publiccode,String memcourse, String emailadd,String authority){
            // this.id = id;
            this.username = username;
            this.password = password;
            this.publiccode = publiccode;
            this.memcourse = memcourse;
            this.emailadd = emailadd;
            this.Authority = authority;
    
            System.out.println("builder >>>"+this);
        }
    
    }
}