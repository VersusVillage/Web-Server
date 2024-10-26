package vv.versusvillage.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "leaderboards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leaderboard {
    @Id
    private String _id;
    
    private String playerId;
    private String gameName;
    private int ranking;
    private Map<String, Object> attributes = new HashMap<>();
}
