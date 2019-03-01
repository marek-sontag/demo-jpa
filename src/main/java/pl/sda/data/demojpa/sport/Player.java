package pl.sda.data.demojpa.sport;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    int age;

    protected Player() {}

    public Player(String name) {
        this.name = name;
    }
}
