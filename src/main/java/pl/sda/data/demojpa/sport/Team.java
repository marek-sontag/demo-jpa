package pl.sda.data.demojpa.sport;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @OneToMany(cascade = CascadeType.DETACH)
    Set<Player> players = new HashSet<>();

    protected Team() {}

    public Team(String name) {
        this.name = name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
