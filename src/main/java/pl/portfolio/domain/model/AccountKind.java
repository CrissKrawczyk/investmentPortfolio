package pl.portfolio.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AccountKind implements DictionaryObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @JoinColumn
    @OneToMany
    private List<AccountKindYearlyLimit> limits;

    @Override
    public String getCaption() {
        return name;
    }

    @Override
    public String getId() {
        return id.toString();
    }

}
