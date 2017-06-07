package wdsr.exercise5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wdsr.exercise5.hsqldb.MyHsqlServer;
import wdsr.exercise5.models.ClassTab;
import wdsr.exercise5.models.Enrollment;
import wdsr.exercise5.models.Faculty;
import wdsr.exercise5.models.Student;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws InterruptedException {
		DatabaseHandler.startConnection("SA","");
        DatabaseHandler.dropTables();
        
        DatabaseHandler.createStudentsTable();
        DatabaseHandler.createFacultyTable();
        DatabaseHandler.createClassTable();
        DatabaseHandler.createEnrollmentTable();
        
        DatabaseHandler.insertStudent(new Student("John Smith","male",23,2));
        DatabaseHandler.insertStudent(new Student("Rebecca Milson","female",27,3));
        DatabaseHandler.insertStudent(new Student("George Heartbreaker","male",19,1));
        DatabaseHandler.insertStudent(new Student("Deepika Chopra","female",25,3));
        DatabaseHandler.insertFaculty(new Faculty("Engineering"));
        DatabaseHandler.insertFaculty(new Faculty("Philosophy"));
        DatabaseHandler.insertFaculty(new Faculty("Law and administration"));
        DatabaseHandler.insertFaculty(new Faculty("Languages"));
        
        DatabaseHandler.insertClass(new ClassTab("Introduction to labour law",102));
        DatabaseHandler.insertClass(new ClassTab("Graph algorithms",100));
        DatabaseHandler.insertClass(new ClassTab("Existentialism in 20th century",101));
        DatabaseHandler.insertClass(new ClassTab("English grammar",103));
        DatabaseHandler.insertClass(new ClassTab("From Plato to Kant",101));
        
        DatabaseHandler.insertEnrollment(new Enrollment(1L,1000L));
        DatabaseHandler.insertEnrollment(new Enrollment(1L,1002L));
        DatabaseHandler.insertEnrollment(new Enrollment(1L,1003L));
        DatabaseHandler.insertEnrollment(new Enrollment(1L,1004L));
        DatabaseHandler.insertEnrollment(new Enrollment(2L,1002L));
        DatabaseHandler.insertEnrollment(new Enrollment(2L,1003L));
        DatabaseHandler.insertEnrollment(new Enrollment(4L,1000L));
        DatabaseHandler.insertEnrollment(new Enrollment(4L,1002L));
        DatabaseHandler.insertEnrollment(new Enrollment(4L,1003L));


        DatabaseHandler.getAllStudents();
        DatabaseHandler.getAllStudentsWithoutSubject();
        DatabaseHandler.getAllFemaleStudents();
        DatabaseHandler.getAllFacultiesNamesWithoutStudents();
        DatabaseHandler.getTheEldestPerson();
        
        DatabaseHandler.closeConnection();
	}
}
