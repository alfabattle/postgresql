package com.ashikhman.postgresql.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Article")
@Table(name = "article")
@Data
@Accessors(chain = true)
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String title;

    @OneToMany(mappedBy = "article", cascade = CascadeType.PERSIST)
    @Nullable
    private List<AuthorEntity> authors;

    public ArticleEntity addAuthor(AuthorEntity author) {
        if (null == authors) {
            authors = new ArrayList<>();
        }

        author.setArticle(this);
        authors.add(author);

        return this;
    }
}
