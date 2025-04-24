package jpolanco.springbootapp;

import jpolanco.springbootapp.user.infrastructure.adapters.output.mysql.RoleRepositoryMySQL;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepositoryMySQL roleRepository;

    public DataInitializer(RoleRepositoryMySQL roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!roleRepository.existsByName("ADMIN")) {
            roleRepository.save("ADMIN");
        }
        if (!roleRepository.existsByName("USER")) {
            roleRepository.save("USER");
        }
        if (!roleRepository.existsByName("ORGANIZER")) {
            roleRepository.save("ORGANIZER");
        }
    }
}
