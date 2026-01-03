package pl.portfolio.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @JoinColumn
    @ManyToOne
    private Portfolio portfolio;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AccountGroup group;

    @JoinColumn
    @ManyToOne
    private Currency currency;

    @Column
    private Integer valuePercentage;

    @OneToMany(mappedBy="account")
    private List<Position> positions;

}
