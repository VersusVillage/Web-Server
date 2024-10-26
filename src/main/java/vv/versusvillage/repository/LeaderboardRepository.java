package vv.versusvillage.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import vv.versusvillage.domain.Leaderboard;

import java.util.List;
import java.util.Optional;

public interface LeaderboardRepository extends MongoRepository<Leaderboard, String> {
    @Query("{'gameName': ?0, 'playerId': ?1}")
    List<Leaderboard> findByGameNameAndPlayerIdCustom(String gameName, String playerId);

    @Query("{'gameName' :  ?0}")
    List<Leaderboard> findByGameName(String gameName);
}
