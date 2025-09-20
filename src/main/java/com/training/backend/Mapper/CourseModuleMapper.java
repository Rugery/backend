package com.training.backend.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.training.backend.Dto.CourseDto;
import com.training.backend.Dto.CourseModuleDto;
import com.training.backend.Entity.Course;
import com.training.backend.Entity.CourseModule;

@Mapper(componentModel = "spring")
public interface CourseModuleMapper {

  @Mapping(target = "courses", source = "courses")
  CourseModule toEntity(CourseModuleDto dto);

  @Mapping(target = "module", ignore = true)
  Course toEntity(CourseDto dto);

  CourseModuleDto toDto(CourseModule entity);

  CourseDto toDto(Course entity);
}
