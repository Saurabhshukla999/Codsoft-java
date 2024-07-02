import java.util.Scanner;

public class studentGradeCalc {
    public static void main (String [] args){

        int totalMarks = 0 ;
        double percentage;
        char grade ;
        
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("enter total number of subjects");
            int totalSubjects = scanner.nextInt();
            int[] marks = new int[totalSubjects];
            for ( int subject = 0; subject < totalSubjects; subject++) {
                    System.out.println(" enter the marks " + (subject+1));
                    marks[subject] = scanner.nextInt();
                    totalMarks += marks[subject];
            }

            percentage = (double)totalMarks/(totalSubjects*100)*100;
            if (percentage >= 90) {
                grade = 'A';
            } else if (percentage >= 80) {
                grade = 'B';
            } else if (percentage >= 70) {
                grade = 'C';
            } else if (percentage >= 60) {
                grade = 'D';
            } else if (percentage >= 50) {
                grade = 'E';
            } else {
                grade = 'F';
            }


            System.out.println("Totalmarks:" + totalMarks);
            System.out.println("Percentage:" + percentage);
            System.out.println("Grade" + grade);

            scanner.close();
        }
    }
}
