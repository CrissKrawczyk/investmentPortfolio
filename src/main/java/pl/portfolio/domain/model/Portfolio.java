package pl.portfolio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn
    @ManyToOne
    private User owner;

    @Column
    private String name;

    @OneToMany(mappedBy = "portfolio", orphanRemoval = true)
    private List<Account> directAccounts;

    @OneToMany(mappedBy = "portfolio", orphanRemoval = true)
    private List<AccountGroup> groups;

    @Column
    private Boolean testPortfolio;


}
