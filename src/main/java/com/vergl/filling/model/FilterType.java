package com.vergl.filling.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 10.03.17
 */
@Entity
@Setter
@Getter
@Table(name = "FILTER_TYPE")
public class FilterType {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "CAPTION")
    private String caption;

    @Column(name = "JOIN_PART")
    private String joinPart;

    @Column(name = "WHERE_PART")
    private String wherePart;

    @Column(name = "DESCRIPTION")
    private String description;


}
