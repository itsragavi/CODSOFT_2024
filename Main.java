import java.util.Scanner;

public class Main {

    // Method to calculate total, average, and grade
    void add(int maths, int social, int science, int tamil, int english) {
        int totalMarks = maths + social + science + tamil + english;
        double averagePercentage = (double) totalMarks / 5;

        String grade;
        if (averagePercentage >= 90) {
            grade = "O";
        } else if (averagePercentage >= 80) {
            grade = "A+";
        } else if (averagePercentage >= 70) {
            grade = "A";
        } else if (averagePercentage >= 60) {
            grade = "B+";
        } else {
            grade = "B";
        }

        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Mathematics mark:");
        int maths = scan.nextInt();
        System.out.println("Enter Social mark:");
        int social = scan.nextInt();
        System.out.println("Enter Science mark:");
        int science = scan.nextInt();
        System.out.println("Enter Tamil mark:");
        int tamil = scan.nextInt();
        System.out.println("Enter English mark:");
        int english = scan.nextInt();

        Main main = new Main();
        main.add(maths, social, science, tamil, english);

        scan.close();
    }
}
