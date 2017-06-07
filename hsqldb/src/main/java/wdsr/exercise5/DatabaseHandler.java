package wdsr.exercise5;

import wdsr.exercise5.models.ClassTab;
import wdsr.exercise5.models.Enrollment;
import wdsr.exercise5.models.Faculty;
import wdsr.exercise5.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseHandler {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);
    private static Connection connection;

    public static void startConnection(String username, String password) {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (Exception e) {
        	log.error("ERROR: failed to load JDBC driver - " + e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(
                    "jdbc:hsqldb:hsql://127.0.0.1:9001/test-db", username,password);
        } catch (SQLException e) {
            log.error("Connection failed: " + e.getMessage());
        }
    }
    
    public static void closeConnection() {
        try {
            connection.close();
        }
        catch(SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static void createStudentsTable() {
    	try {
			connection.prepareStatement("CREATE TABLE IF NOT EXISTS Student (" +
			        "id INTEGER PRIMARY KEY generated always as identity (START WITH 1), name VARCHAR(20)," + 
					"sex VARCHAR(10), age INTEGER, level INTEGER)").executeUpdate();
			log.info("Created table Student");
		} catch (SQLException e) {
			log.error("Cannot create table Student" + e.getMessage());
		}
    }

    public static void createEnrollmentTable() {
    	try {
    		connection.prepareStatement("CREATE TABLE IF NOT EXISTS Enrollment ( fkey_student "
					+ "INTEGER FOREIGN KEY REFERENCES Student (id), "
					+ "fkey_class INTEGER FOREIGN KEY REFERENCES Class (id))").executeUpdate();
			log.info("Created table Enrollment");
    		
			log.info("Created table Enrollment");
		} catch (SQLException e) {
			log.error("Cannot create table Enrollment" + e.getMessage());
		}
    }

    public static void createClassTable() {
    	try {
    		connection.prepareStatement("CREATE TABLE IF NOT EXISTS Class (" +
			        "id INTEGER PRIMARY KEY generated always as identity (START WITH 1000), "
			        + "name VARCHAR(50), " + "fkey_faculty INTEGER FOREIGN KEY REFERENCES Faculty (pkey))")
					.executeUpdate();
    		
			log.info("Created table Class");
		} catch (SQLException e) {
			log.error("Cannot create table Class" + e.getMessage());
		}
    }

    public static void createFacultyTable() {
    	try {
			connection.prepareStatement("CREATE TABLE IF NOT EXISTS Faculty (" +
			        "pkey INTEGER PRIMARY KEY generated always as identity (START WITH 100), "
			        + "name VARCHAR(50))").executeUpdate();
			log.info("Created table Faculty");
		} catch (SQLException e) {
			log.error("Cannot create table Faculty");
		}
    }

    public static void insertStudent(Student student) {
        try {
            connection.prepareStatement("INSERT INTO student (name, sex, age, level)" +
                    " VALUES(" +
                    "'" + student.getName() + "'," +
                    "'" + student.getSex() + "'," +
                    student.getAge() + "," +
                    student.getLevel() +
                    ")").executeUpdate();
            log.info("Created students");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
;
    }

    public static void insertFaculty(Faculty faculty) {
    	try {
    		connection.prepareStatement("INSERT INTO Faculty (name)" +
			        " VALUES('" + faculty.getName() + "')").executeUpdate();
    		log.info("Created Faculty");
    		
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
    }

    public static void insertClass(ClassTab class_) {
    	try {
    		connection.prepareStatement("INSERT INTO Class (name, fkey_faculty)" +
			        " VALUES('" + class_.getName() + "','" + class_.getFkey_faculty() + "')")
					.executeUpdate();
    		log.info("Created vlass");
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
    }

    public static void insertEnrollment(Enrollment enrollment) {
    	try {
			connection.prepareStatement("INSERT INTO Enrollment (fkey_class, fkey_student)" +
			        " VALUES('" + enrollment.getFkey_class() + "','" + enrollment.getFkey_student() 
			        + "')").executeUpdate();
		} catch (SQLException e) {
			log.error("inserting failed " + e.getMessage());
		}
    }

    public static void dropTables() {
        try {
            connection.prepareStatement("DROP TABLE enrollment").executeUpdate();
            connection.prepareStatement("DROP TABLE student").executeUpdate();
            connection.prepareStatement("DROP TABLE class").executeUpdate();
            connection.prepareStatement("DROP TABLE faculty").executeUpdate();

            log.info("tables dropped successfully");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static void getAllStudents() {
        try {
            ResultSet result = connection.prepareStatement("SELECT id , name FROM Student").executeQuery();
            log.info(" All students: ");
            while (result.next()) {
                log.info(
                        "pkey= "+result.getInt("id") + " " +
                                "name= "+result.getString("name"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static void getAllStudentsWithoutSubject() {
        try {
            ResultSet result = connection.prepareStatement("SELECT id , name " +
                    "FROM student" +
                    " LEFT JOIN enrollment on student.id = enrollment.fkey_student" +
                    " WHERE enrollment.fkey_student IS NULL").executeQuery();
            log.info(
                    "  All students without subjest: ");
            while (result.next()) {
                log.info(
                        "pkey= "+result.getInt("id") + ", " +
                                "name= "+       result.getString("name")
                );
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

    }

    public static void getAllFemaleStudents() {

        try {
            ResultSet result = connection.prepareStatement("SELECT student.id , student.name " +
                    "FROM student" +
                    " LEFT JOIN enrollment on student.id = enrollment.fkey_student" +
                    " WHERE student.sex LIKE 'female'" +
                    " AND enrollment.fkey_class = 1002").executeQuery();
            log.info(
                    "All female students: ");
            while (result.next()) {
                log.info(
                        "pkey= "+result.getInt("id") + ", " +
                                "name= "+result.getString("name")
                );
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static void getAllFacultiesNamesWithoutStudents() {
        try {
        	ResultSet result = connection.prepareStatement("SELECT faculty.name " +
                    "FROM faculty" +
                    " JOIN class on faculty.pkey = class.fkey_faculty" +
                    " LEFT JOIN enrollment on class.id = enrollment.fkey_class" +
                    " WHERE enrollment.fkey_class IS NULL").executeQuery();
			log.info("All faculties names");
			while (result.next()) {
                log.info("name= " + result.getString("name"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static void getTheEldestPerson() {
        try {
            ResultSet result = connection.prepareStatement("SELECT MAX(age) AS max_age FROM student"
            ).executeQuery();
            log.info(
                    "The eldest person: ");
            while (result.next()) {
                log.info("Max age = "+result.getInt("max_age"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
