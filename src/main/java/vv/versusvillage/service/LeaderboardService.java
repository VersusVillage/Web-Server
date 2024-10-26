package vv.versusvillage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vv.versusvillage.domain.Leaderboard;
import vv.versusvillage.repository.LeaderboardRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    @Autowired
    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    public Leaderboard addLeaderboard(Leaderboard leaderboard) {
        return leaderboardRepository.save(leaderboard);
    }

    public List<Leaderboard> findLeaderboard(String gameName, String sort, String playerId) {
        List<Leaderboard> leaderboards;

        if (playerId != null && !playerId.isEmpty()) {
            leaderboards = leaderboardRepository.findByGameNameAndPlayerIdCustom(gameName, playerId);
        } else {
            leaderboards = leaderboardRepository.findByGameName(gameName);
        }


        if ("asc".equalsIgnoreCase(sort)) {
            leaderboards.sort(Comparator.comparingInt(Leaderboard::getRanking)); // 랭킹 기준 오름차순 정렬
        } else if ("desc".equalsIgnoreCase(sort)) {
            leaderboards.sort(Comparator.comparingInt(Leaderboard::getRanking).reversed()); // 랭킹 기준 내림차순 정렬
        }

        return leaderboards;
    }

    public List<Leaderboard> findALlLeaderboards() {
        return leaderboardRepository.findAll();
    }
}
