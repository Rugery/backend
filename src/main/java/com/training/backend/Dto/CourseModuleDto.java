package com.training.backend.Dto;

import java.util.List;

import lombok.Data;

@Data
public class CourseModuleDto {
  private String title;
  private String description;
  private List<CourseDto> courses;
}
