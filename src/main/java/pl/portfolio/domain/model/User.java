package pl.portfolio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(unique=true)
    private String login;

    @Column(unique=true)
    private String password;

    @Column
    private String forename;

    @Column
    private String surname;


}
