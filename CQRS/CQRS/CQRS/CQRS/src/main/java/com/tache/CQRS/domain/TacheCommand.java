package com.tache.CQRS.domain;

import javax.persistence.*;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TacheCommand {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated( EnumType.STRING)
    private TypeT typeT;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;

    @OneToOne
    public User createdBy;

    @ManyToMany()
    public Set<User> members;

}
