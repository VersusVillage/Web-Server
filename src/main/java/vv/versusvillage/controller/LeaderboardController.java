package vv.versusvillage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vv.versusvillage.domain.Leaderboard;
import vv.versusvillage.repository.LeaderboardRepository;
import vv.versusvillage.service.LeaderboardService;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

//    @GetMapping
//    public ResponseEntity<List<Leaderboard>> getLeaderboard() {
//        List<Leaderboard> leaderboards = leaderboardService.findALlLeaderboards();
//        return ResponseEntity.status(HttpStatus.CREATED).body(leaderboards);
//    }

    @GetMapping
    public ResponseEntity<List<Leaderboard>> getLeaderboard(@RequestParam(required = false, defaultValue = "runner") String gameName,
                                                            @RequestParam(required = false, defaultValue = "asc") String sort,
                                                            @RequestParam(required = false) String playerId) {
        System.out.println(gameName);
        System.out.println(playerId);
        System.out.println(sort);
        List<Leaderboard> leaderboards = leaderboardService.findLeaderboard(gameName, sort, playerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(leaderboards);
    }


    @PostMapping
    public ResponseEntity<Leaderboard> addLeaderboard(@RequestBody Leaderboard leaderboard) {
        Leaderboard savedLeaderboard = leaderboardService.addLeaderboard(leaderboard);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLeaderboard);
    }
}
