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
@Table(name = "DEBT_CLASS")
public class DebtClass {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "CAPTION")
    private String caption;
}
