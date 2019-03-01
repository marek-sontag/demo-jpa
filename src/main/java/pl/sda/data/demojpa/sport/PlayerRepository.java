package pl.sda.data.demojpa.sport;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long>, QuerydslPredicateExecutor<Player> {
}
