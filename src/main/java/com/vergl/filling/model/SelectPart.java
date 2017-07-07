package com.vergl.filling.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 26.04.17
 */
@Entity
@Setter
@Getter
@Table(name = "SELECT_PART")
public class SelectPart {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "PARAMETER_NAME")
    private String parameterName;

    @Column(name = "SELECT_QUERY")
    private String selectQuery;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "selectPart")
    List<StatFormGroup> statFormGroups = new ArrayList<>(0);
}
