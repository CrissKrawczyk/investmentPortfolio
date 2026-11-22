package pl.portfolio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class AccountGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn
    @ManyToOne
    private Portfolio portfolio;

    @JoinColumn
    @ManyToOne
    private AccountGroup group;

    @Column
    private String name;

    @Column
    private Integer valuePercentage;

    @OneToMany(mappedBy = "group", orphanRemoval = true)
    private List<Account> accounts;

    @OneToMany(mappedBy = "group", orphanRemoval = true)
    private List<AccountGroup> groups;

}
