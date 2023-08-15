package TeamTaskViewer.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "tache")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "description")
    private String description; // Description de la tâche réalisée

    @Column(name = "`current_time`") // Echapper le nom de la colonne avec des backticks
    private LocalDateTime currentTime;
    private LocalDateTime laterTime;

    public Tache(Long id, String description, LocalDateTime currentTime, LocalDateTime laterTime) {
        this.id = id;
        this.description = description;
        this.currentTime = currentTime;
        this.laterTime = laterTime;
    }
}
