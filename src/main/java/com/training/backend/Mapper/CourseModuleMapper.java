package com.training.backend.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.training.backend.Dto.CourseDto;
import com.training.backend.Dto.CourseModuleDto;
import com.training.backend.Entity.Course;
import com.training.backend.Entity.CourseModule;

@Mapper(componentModel = "spring")
public interface CourseModuleMapper {

  // Map CourseModuleDto to CourseModule entity
  @Mapping(target = "courses", source = "courses")
  @Mapping(target = "id", ignore = true)
  CourseModule toEntity(CourseModuleDto dto);

  // Map CourseDto to Course entity
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "insignias", ignore = true)
  @Mapping(target = "progress", ignore = true)
  @Mapping(target = "module", ignore = true)
  Course toEntity(CourseDto dto);

  // Map CourseModule entity to CourseModuleDto
  CourseModuleDto toDto(CourseModule entity);

  // Map Course entity to CourseDto
  CourseDto toDto(Course entity);
}
