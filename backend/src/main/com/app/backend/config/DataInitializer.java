package com.app.backend.config;

import com.app.backend.model.User;
import com.app.backend.model.Category;
import com.app.backend.repository.UserRepository;
import com.app.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ejecutando DataInitializer...");

        // Eliminar y recrear usuarios para asegurar contraseñas incorrectas y no se arme un mierdero

        if (userRepository.existsByUsername("admin")) {
            User existingAdmin = userRepository.findByUsername("admin").orElse(null);

            if (existingAdmin != null) {
                userRepository.delete(existingAdmin);
                System.out.println("Usuario Admin existente eliminado pa que sea serio");
            }
        } 

        if (userRepository.existsByUsername("Coordinador")) {
            User existingCoord = userRepository.findByUsername("Coordinador").orElse(null);

            if (existingCoord!= null) {
                userRepository.delete(existingCoord);
                System.out.println("Usuario Coordinador existente eliminado pa que sea serio");
            }
        } 

        // Crear usuario Admin
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEmail("admin@app.com");
        admin.setRole(User.Role.ADMIN);
        admin.setActive(true);
        userRepository.save(admin);
        System.out.println("Usuario Admin creado - username: admin, password: admin123");

        // Crear usuario Coordinador
        User coordinador = new User();
        coordinador.setUsername("coordinador");
        coordinador.setPassword(passwordEncoder.encode("coord123"));
        coordinador.setEmail("coordinador@app.com");
        coordinador.setRole(User.Role.COORDINADOR);
        coordinador.setActive(true);
        userRepository.save(coordinador);
        System.out.println("Usuario Coordinador creado - username: coordinador, password: coord123"
        );

        System.out.println("DataInitializer completado exitosamente te lo aseguro por dieguito maradona");
    }

    
}