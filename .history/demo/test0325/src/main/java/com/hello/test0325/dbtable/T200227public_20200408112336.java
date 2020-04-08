package com.hello.test0325.dbtable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
public class T200227public{
    @Id
    @GeneratedValue
    private String publiccode;


}