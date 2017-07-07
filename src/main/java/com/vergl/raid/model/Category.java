package com.vergl.raid.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 01.02.17
 */
@Entity
@Setter
@Getter
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "CAPTION")
    private String caption;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "raidCategory")
    private Set<Raid> raids = new HashSet<>(0);
}
