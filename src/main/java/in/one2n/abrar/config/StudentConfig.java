package in.one2n.abrar.config;

import in.one2n.abrar.entity.Student;
import in.one2n.abrar.repo.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo) {
        return args ->{
           Student abu = new Student(
                    "Abrar",
                    LocalDate.of(1999, 1, 1),
                    "male",
                    "abrar@one2n.in",
                    "Cbe"
            );
           Student azar = new Student(
                   "Azar",
                   LocalDate.of(1998,1,1),
                   "male",
                   "azar@az.in",
                   "Cbe"
           );
            studentRepo.saveAll(List.of(abu, azar));
        };
    }
}
