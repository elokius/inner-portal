package com.vergl.filling.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project name: Fssp60Raid.
 *
 * @author admin06
 * @version 1.0
 * @since 13.03.17
 */
@Entity
@Setter
@Getter
@Table(name = "JOIN_PART")
public class JoinPart {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "PARAMETER_NAME")
    private String parameterName;

    @Column(name = "JOIN_QUERY")
    private String joinQuery;
}
