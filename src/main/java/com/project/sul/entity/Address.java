package com.project.sul.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String zipCode;

    private String streetAdr;

    private String detailAdr;

    public Address(String zipCode, String streetAdr, String detailAdr) {
        this.zipCode = zipCode;
        this.streetAdr = streetAdr;
        this.detailAdr = detailAdr;
    }
}
