package org.example;
import org.example.controller.StudentController;
import org.example.model.Student;
import org.example.view.StudentView;

import java.util.*;

import static java.lang.System.in;

public class Application {


    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        boolean run = true;
        List<Student> students = new ArrayList<>();



        System.out.println("Welcome to Student Management System");
        while (run){
            System.out.println("================ Menu ===============");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. retrieve Student from Database");
            System.out.print("Choose:  ");

            // todo : future updates.....
//            System.out.println("4. update Student");
//            System.out.println("5. remove Student");
            String choice = sc.nextLine();
            String[] choices = {"1", "2", "3"};

            while (!Arrays.asList(choices).contains(choice)) {
                System.out.println("Invalid choice. Please enter a valid option (1, 2, or 3): ");
                choice = sc.nextLine();
            }

            switch (choice) {

                case "1":
                    Student newStudent = addStudent();
                    students.add(newStudent);
                    System.out.println("Student: "+newStudent.getName()+" has been added Successfully! ");
                    System.out.println();
                    System.out.println("=====================================");
                    break;

                case "2":

                    if(students.isEmpty()){
                        System.out.println("No students added");
                        System.out.println();
                        System.out.println("=====================================");
                    }else {
                        for (Student student1 : students) {
                            viewStudent(student1.getName(), student1.getRollNo());
                        }
                    }
                    break;
                case "3":
                    // Retrieve Student from Database logic
                    System.out.println("Enter Student roll Number: ");

                    String rollNumber = sc.nextLine();
                    Student student = retriveStudent(rollNumber, students);

                    if (student == null){
                        System.out.println("No student found with roll Number: "+ rollNumber);
                        break;
                    }

                    viewStudent(student.getName(), student.getRollNo());

                    break;
                default:
                    // This should never be reached due to the validation loop
                    break;
            }





        }


    }

    private static Student retriveStudent(String rollNumber, List<Student> students){
        for(Student student: students){

            if(student.getRollNo().equals(rollNumber)){
                return student;
            }
        }
        return null;
    }


    private static Student addStudent(){

        Scanner sc = new Scanner(in);
        System.out.print("Enter Student name: ");
        String name = sc.nextLine();
        System.out.println();
        System.out.print("Enter Student roll Number: ");
        String rollNumber = sc.nextLine();
        Student newStudent = new Student();
        newStudent.setName(name);
        newStudent.setRollNo(rollNumber);

        return newStudent;

    }



    private static void viewStudent(String name, String rollNumber){
        StudentView view = new StudentView();
        view.printStudentDetails(name, rollNumber);
    }


}