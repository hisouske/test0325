package com.hello.test0325.dbtable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="\"200227public\"")
public class T200227public{
    @Id
    @GeneratedValue
    @Column
    private int publiccode;
    @Column
    private String publicdata;


    public T200227public(){

    }
}