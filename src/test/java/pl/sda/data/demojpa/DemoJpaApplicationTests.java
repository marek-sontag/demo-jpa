package pl.sda.data.demojpa;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.data.demojpa.sport.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoJpaApplicationTests {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    public void testPlayersAndTeam() {
        Player p1 = new Player("Szmal");
        p1.setAge(30);
        Player p2 = new Player("Lijewski");
        p1.setAge(35);

        playerRepository.save(p1);
        playerRepository.save(p2);

        Team team = new Team("Vive");
        team.addPlayer(p1);
        team.addPlayer(p2);

        teamRepository.save(team);

        assertThat(teamRepository.count()).isEqualTo(1);
        assertThat(playerRepository.count()).isEqualTo(2);

        teamRepository.deleteAll();

        assertThat(teamRepository.count()).isEqualTo(0);
        assertThat(playerRepository.count()).isEqualTo(2);
    }

    @Test
    public void testQueryDsl() {
        Player jordan = new Player("Jordan");
        jordan.setAge(20);
        playerRepository.save(jordan);

        Player realJordan = new Player("Jordan");
        realJordan.setAge(55);
        playerRepository.save(realJordan);

        Player shaq = new Player("Shaq");
        playerRepository.save(shaq);

        BooleanExpression jordanExpression = QPlayer.player.name.eq("Jordan");
        OrderSpecifier<Integer> orderByAge = QPlayer.player.age.desc();

        Iterable<Player> jordans = playerRepository.findAll(jordanExpression, orderByAge);

        jordans.forEach(j -> assertThat(j.getName()).isEqualTo("Jordan"));
    }
}
