package com.teste.sitaf.Models.RepositoryModel;

import jakarta.persistence.*;

import java.security.InvalidParameterException;

@Entity
@Table(name = "Category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;
    public String Name;

    public void setName(String name){

        if(name.isEmpty())
            throw new InvalidParameterException("Name can't be null");

        this.Name = name;
    }
}
