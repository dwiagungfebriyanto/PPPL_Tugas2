import org.example.Student;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTest {

    private ArrayList<Student> students;

    @BeforeAll
    public void initClass() {
        students = new ArrayList<>();
    }

    @AfterAll
    public void cleanClass() {
        students.clear();
    }

    @BeforeEach
    public void initMethod() {
        Student student0 = new Student("DAF0", 10);
        Student student1 = new Student("DAF1", 11);
        students.add(student0);
        students.add(student1);
    }

    @AfterEach
    public void cleanMethod() {
        students.clear();
    }

    @Test
    public void testDataCreation() {
        assertAll(
                () -> assertNotNull(students),
                () -> assertEquals(2, students.size()),
                () -> assertEquals("DAF0", students.get(0).getName()),
                () -> assertEquals(10, students.get(0).getAge())
        );
    }

    @Test
    public void testStudentEnrollment() {
        students.get(0).enrollCourse("PPPL");
        students.get(1).enrollCourse("PPBO");
        assertAll(
                () -> assertTrue(students.get(0).getEnrolledCourses().contains("PPPL")),
                () -> assertTrue(students.get(1).getEnrolledCourses().contains("PPBO"))
        );
    }

    @Test
    public void testStudentGrade() {
        students.get(1).setGrade("PPPL", "A");
        students.get(1).setGrade("PPBO", "C-");
        assertAll(
                () -> assertEquals("A", students.get(1).getGrade("PPPL"), "yg ini"),
                () -> assertEquals("C-", students.get(1).getGrade("PPBO"), "yg itu")
        );
    }

}