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

    private String streetAddr;

    private String detailAddr;

    public Address(String zipCode, String streetAddr, String detailAddr) {
        this.zipCode = zipCode;
        this.streetAddr = streetAddr;
        this.detailAddr = detailAddr;
    }
}
