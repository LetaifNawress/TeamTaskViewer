package TeamTaskViewer.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;
    @ManyToMany(mappedBy = "projects")
    private List<Client> clients = new ArrayList<>();
}
