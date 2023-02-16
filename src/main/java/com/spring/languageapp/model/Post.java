package com.spring.languageapp.model;

import javax.persistence.*;

@MappedSuperclass
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "post_seq")
    @SequenceGenerator(name = "post_seq",
            sequenceName = "post_seq",
            initialValue = 1,
            allocationSize = 1)
    private Long id;


    public Post(){}

    public Post(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
