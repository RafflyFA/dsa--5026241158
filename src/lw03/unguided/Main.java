package lw03.unguided;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Set<String> registeredStudents = new HashSet<>();
        Set<String> successfulCheckins = new HashSet<>();
        int rejectedAttempts = 0;
        //Ngebaca Registrations.txt dan masukin ke registeredStudents
        Scanner registrationScanner = new Scanner(new File("src/lw03/unguided/registrations.txt"));
        while (registrationScanner.hasNextLine()) {
            String studentId = registrationScanner.nextLine();
            registeredStudents.add(studentId);
        }
        registrationScanner.close();
        //ini untuk baca code process check-innya
        Scanner checkinScanner = new Scanner(new File("src/lw03/unguided/checkins.txt"));
        System.out.println("===== Event Check-In Results =====");
        while (checkinScanner.hasNextLine()) {
            String studentId = checkinScanner.nextLine();
            if (!registeredStudents.contains(studentId)) {
                System.out.println(studentId + ": Rejected (not registered)");
                rejectedAttempts++;
            } else if (successfulCheckins.contains(studentId)) {
                System.out.println(studentId + ": Rejected (already checked in)");
                rejectedAttempts++;
            } else {
                successfulCheckins.add(studentId);
                System.out.println(studentId + ": Checked in");
            }
        }
        checkinScanner.close();
        //Code untuk menghasilkan bagian final event summary nya start dri int absentStudents = registeredStudents.size() - successfulCheckins.size();
        int absentStudents = registeredStudents.size() - successfulCheckins.size();
        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudents.size());
        System.out.println("Successful check-ins: " + successfulCheckins.size());
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}