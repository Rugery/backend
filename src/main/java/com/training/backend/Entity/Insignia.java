package com.training.backend.Entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "insignia")
public class Insignia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String imageUrl;

  // Relation with User
  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference(value = "user-insignia")
  private User user;

  // Relation with Course
  @ManyToOne
  @JoinColumn(name = "course_id")
  @JsonIgnoreProperties({ "insignias", "module", "progress" })
  private Course course;

  private LocalDate dateAwarded;
}
