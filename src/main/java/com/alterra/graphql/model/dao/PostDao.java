package com.alterra.graphql.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "M_Post")
public class PostDao implements Serializable {

    private static final long serialVersionUID = -5641380973263210344L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private  String title;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "category")
    private String category;

    @ManyToOne
    private AuthorDao author;
}
