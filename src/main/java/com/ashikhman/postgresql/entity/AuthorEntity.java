package com.ashikhman.postgresql.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity(name = "Author")
@Table(name = "author")
@Data
@Accessors(chain = true)
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String fullName;

    @ManyToOne
    private ArticleEntity article;
}
