package com.tache.CQRS.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String nom;


    @OneToOne(mappedBy = "createdBy",cascade =  CascadeType.ALL)
    public TacheCommand tache;


    @ManyToMany(mappedBy = "members",fetch= FetchType.LAZY)
    public Set<TacheCommand> taches;
}
