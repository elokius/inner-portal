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
 * @since 20.03.17
 */
@Entity
@Getter
@Setter
@Table(name = "STAT_FORM_GROUP")
public class StatFormGroup {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "CAPTION")
    private String caption;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SELECT_PART_ID", nullable = false)
    private SelectPart selectPart;

    @OneToMany(mappedBy = "statFormGroup", fetch = FetchType.EAGER)
    private List<StatForm> statForms = new ArrayList<>(0);
}
