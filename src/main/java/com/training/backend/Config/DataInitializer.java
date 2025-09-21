package com.training.backend.Config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.training.backend.Entity.Course;
import com.training.backend.Entity.CourseModule;
import com.training.backend.Entity.Role;
import com.training.backend.Entity.User;
import com.training.backend.Repository.CourseModuleRepository;
import com.training.backend.Repository.CourseRepository;
import com.training.backend.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
  private final UserRepository userRepository;
  private final CourseModuleRepository courseModuleRepository;
  private final CourseRepository courseRepository;

  @Override
  public void run(String... args) throws Exception {
    // Initial Data

    // --- USERS ---
    User admin = new User();
    admin.setUsername("admin");
    admin.setEmail("admin@example.com");
    admin.setPassword("admin123");
    admin.setRole(Role.ADMIN);

    User user = new User();
    user.setUsername("rugery");
    user.setEmail("rugeryde@gmail.com");
    user.setPassword("user123");
    user.setRole(Role.USER);

    userRepository.saveAll(Arrays.asList(admin, user));

    // --- MODULES ---
    CourseModule fullstack = new CourseModule();
    fullstack.setTitle("🖥 Fullstack");
    fullstack.setDescription("Capacitación completa en desarrollo Fullstack.");

    CourseModule apis = new CourseModule();
    apis.setTitle("🔗 APIs e Integraciones");
    apis.setDescription("Aprende a crear y consumir APIs e integrar sistemas.");

    CourseModule cloud = new CourseModule();
    cloud.setTitle("☁ Cloud");
    cloud.setDescription("Conceptos y prácticas en la nube.");

    CourseModule dataEngineer = new CourseModule();
    dataEngineer.setTitle("📊 Data Engineer");
    dataEngineer.setDescription("Procesamiento y análisis de datos a nivel profesional.");

    courseModuleRepository.saveAll(Arrays.asList(fullstack, apis, cloud, dataEngineer));

    // --- COURSES ---
    Course fs1 = new Course();
    fs1.setTitle("Curso Fullstack 1: Frontend Básico");
    fs1.setDescription("Introducción a HTML, CSS y JavaScript");
    fs1.setModule(fullstack);

    Course fs2 = new Course();
    fs2.setTitle("Curso Fullstack 2: Backend Básico");
    fs2.setDescription("Introducción a Java, Spring Boot y bases de datos");
    fs2.setModule(fullstack);

    Course api1 = new Course();
    api1.setTitle("Curso APIs 1: REST API");
    api1.setDescription("Creación de servicios REST con Spring Boot");
    api1.setModule(apis);

    Course api2 = new Course();
    api2.setTitle("Curso APIs 2: Integración de servicios");
    api2.setDescription("Consumir APIs externas y manejo de autenticación");
    api2.setModule(apis);

    Course cloud1 = new Course();
    cloud1.setTitle("Curso Cloud 1: Fundamentos");
    cloud1.setDescription("Conceptos de nube y proveedores principales");
    cloud1.setModule(cloud);

    Course cloud2 = new Course();
    cloud2.setTitle("Curso Cloud 2: Deploy en la nube");
    cloud2.setDescription("Desplegar aplicaciones y servicios en la nube");
    cloud2.setModule(cloud);

    Course de1 = new Course();
    de1.setTitle("Curso Data Engineer 1: Bases de Datos");
    de1.setDescription("Modelado, consultas y optimización de bases de datos");
    de1.setModule(dataEngineer);

    Course de2 = new Course();
    de2.setTitle("Curso Data Engineer 2: ETL y Procesamiento de Datos");
    de2.setDescription("Creación de pipelines y procesamiento de datos");
    de2.setModule(dataEngineer);

    courseRepository.saveAll(Arrays.asList(fs1, fs2, api1, api2, cloud1, cloud2, de1, de2));

    System.out.println("Datos iniciales cargados: usuarios, módulos y cursos.");
  }
}
