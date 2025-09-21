package com.training.backend.Entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "progress")
public class Progress {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Relation with User
  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference(value = "user-progress")
  private User user;

  // Relation with Course
  @ManyToOne
  @JoinColumn(name = "course_id")
  @JsonBackReference(value = "course-progress")
  private Course course;

  @Enumerated(EnumType.STRING)
  private ProgressStatus status;

  private LocalDate dateUpdated;

  // Method for JSON serialization to get the course title
  @JsonGetter("courseTitle")
  public String getCourseTitle() {
    return course != null ? course.getTitle() : null;
  }

}
