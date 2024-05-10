package org.codsoft;
import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter marks of Maths:");
        int Maths = scanner.nextInt();
        System.out.println("Enter marks of Hindi:");
        int Hindi = scanner.nextInt();
        System.out.println("Enter marks of English:");
        int English = scanner.nextInt();
        System.out.println("Enter marks of Science:");
        int Science = scanner.nextInt();
        System.out.println("Enter marks of Social Science:");
        int SocialScience = scanner.nextInt();

        int totalMarks = English + Hindi + Maths + Science + SocialScience;

        float averageMarks = (float) totalMarks / 5;

        char grade;
        if (averageMarks >= 90) {
            grade = 'A';
        } else if (averageMarks >= 80) {
            grade = 'B';
        } else if (averageMarks >= 70) {
            grade = 'C';
        } else if (averageMarks >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("Average Marks: " + averageMarks);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
