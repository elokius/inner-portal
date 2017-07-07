package com.vergl.filling.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 01.03.17
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "DEBTOR_TYPE")
public class DebtorType {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "NUMBER")
    private long number;

    @Column(name = "CAPTION")
    private String caption;
}
