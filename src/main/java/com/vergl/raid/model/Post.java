package com.vergl.raid.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vergl on 29.01.2017.
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "CAPTION")
    private String caption;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personPost")
    private Set<Person> persons = new HashSet<>(0);

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", number=" + number +
                ", caption='" + caption + '\'' +
                '}';
    }
}
