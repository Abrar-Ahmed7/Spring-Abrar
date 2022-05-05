package in.one2n.abrar.service;

import in.one2n.abrar.repo.StudentRepo;
import in.one2n.abrar.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentByEmail = studentRepo.findStudentByEmailId(student.getEmailId());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email not available");
        }
        studentRepo.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepo.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student "+ studentId + " doesn't exists");
        }
        studentRepo.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String emailId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(()-> new IllegalStateException("Student "+ studentId + " doesn't exists"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (emailId != null && emailId.length() > 0 && !Objects.equals(student.getEmailId(), emailId)) {
            Optional<Student> studentByEmail = studentRepo.findStudentByEmailId(student.getEmailId());
            if(studentByEmail.isPresent()){
                throw new IllegalStateException("email not available");
            }
            student.setEmailId(emailId);
        }

    }

    public Optional<Student> getStudent(Long studentId) {
        studentRepo.findById(studentId)
                .orElseThrow(()-> new IllegalStateException("Student "+ studentId + " doesn't exists"));

        return studentRepo.findById(studentId);
    }
}
