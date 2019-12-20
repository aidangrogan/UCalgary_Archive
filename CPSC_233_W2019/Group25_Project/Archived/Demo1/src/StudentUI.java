import java.util.ArrayList;

/**
 * The part of textUI for Students. The GUI design will refer its logic.
 */

public class StudentUI {

    public StudentUI(String uuid) {

        Database.initializeAcademicRequirements();
        Database.initializeStudentInformation(uuid);

        Student user = (Student) Database.accounts.get(uuid);

        String  select;
        String  courseId;
        boolean exitSubMenu;

        while (true) {

            System.out.println(Constants.COLOR_WRAPPER("STUDENT PANEL", Constants.WHITE_BOLD));
            System.out.println();

            System.out.println(" 1. List the academic requirement");
            System.out.println(" 2. List the courses currently take");
            System.out.println(" 3. List the grades of courses taken");
            //System.out.println(" 4. List the internship requirement");
            System.out.println(" 4. Search a course by the course id");
            System.out.println(" 5. Modify personal information");
            System.out.println(" 6. Logout");

            System.out.println();
            System.out.print(Constants.COLOR_WRAPPER("Select number: ", Constants.WHITE_BOLD));
            select = Constants.IN.nextLine();
            System.out.println();
            switch (select) {

                case "1":

                    // print all stuff in the academic requirement
                    System.out.println(Constants.COLOR_WRAPPER("MANDATORY COURSES: ", Constants.WHITE_BOLD));
                    System.out.println();
                    System.out.println(Constants.COLOR_WRAPPER("COURSEID  COURSE UNITS ", Constants.WHITE_BOLD));
                    System.out.println();
                    for (String i : Database.mandatoryCourses) {
                        System.out.printf("%8s", i + "   " + Database.courses.get(i).getCourseUnits() + " UNITS\n");
                    }
                    System.out.println();
                    System.out.println(Constants.COLOR_WRAPPER("OPTIONAL COURSES: ", Constants.WHITE_BOLD) + "(You must take at least 18" +
                            " units)");
                    System.out.println();
                    System.out.println(Constants.COLOR_WRAPPER("COURSEID  COURSE UNITS ", Constants.WHITE_BOLD));
                    System.out.println();
                    for (String i : Database.optionalCourses) {
                        System.out.printf("%8s", i + "   " + Database.courses.get(i).getCourseUnits() + " UNITS\n");
                    }
                    System.out.println();
                    break;

                case "2":

                    System.out.println(Constants.COLOR_WRAPPER("COURSES CURRENTLY TAKING: ", Constants.WHITE_BOLD));
                    System.out.println();
                    ArrayList<String> currentCourses = user.getCurrentCoursesList();
                    for (String i : currentCourses) {
                        System.out.println(i);
                    }
                    System.out.println();
                    Constants.PRESS_ENTER_KEY_TO_CONTINUE();
                    System.out.println();
                    break;

                case "3":

                    System.out.println(Constants.COLOR_WRAPPER("COURSES TAKEN: ", Constants.WHITE_BOLD));
                    System.out.println();
                    ArrayList<CourseAndGrade> takenCourses = user.getTakenCoursesList();
                    for (CourseAndGrade i : takenCourses) {
                        System.out.println(i);
                    }
                    System.out.println();
                    Constants.PRESS_ENTER_KEY_TO_CONTINUE();
                    System.out.println();
                    break;

                case "4":

                    System.out.print("Course id: ");
                    courseId = Constants.IN.nextLine();
                    if (Database.courses.containsKey(courseId)) {
                        System.out.println();
                        System.out.println(Database.courses.get(courseId));
                        System.out.println();
                    } else {
                        System.out.println(Constants.COLOR_WRAPPER("Course id does not exist!", Constants.RED));
                        System.out.println();
                    }
                    Constants.PRESS_ENTER_KEY_TO_CONTINUE();
                    System.out.println();
                    break;

                case "5":

                    System.out.println();
                    System.out.println(Constants.COLOR_WRAPPER("Current personal Information : ", Constants.WHITE_BOLD));
                    System.out.println();
                    System.out.println(Database.accounts.get(user.getUuid()));
                    System.out.println();

                    exitSubMenu = false;
                    while (!exitSubMenu) {

                        String fieldToModify;
                        System.out.println(" 1. Modify USERNAME");
                        System.out.println(" 2. Modify ADDRESS");
                        System.out.println(" 3. Modify PHONE NUMBER");
                        System.out.println(" 4. Modify DATE OF BIRTH");
                        System.out.println(" 5. Modify EMAIL ADDRESS");
                        System.out.println(" 6. Modify PASSWORD");
                        System.out.println(" 7. Return");
                        System.out.println();

                        System.out.print(Constants.COLOR_WRAPPER("Select number: ", Constants.WHITE_BOLD));
                        // If the enter is empty or illegal then remain unchanged, which is defined in Validation class.
                        fieldToModify = Constants.IN.nextLine();
                        switch (fieldToModify) {
                            case "1":
                                System.out.print(Constants.COLOR_WRAPPER("New USERNAME", Constants.WHITE_BOLD) + " " +
                                        "(must be not empty and " +
                                        "must be unique): ");
                                String newUsername = Constants.IN.nextLine();

                                Database.accounts.get(user.getUuid()).setUsername(newUsername);
                                System.out.println();
                                System.out.println(Database.accounts.get(user.getUuid()));
                                System.out.println();
                                Database.accountUpdateCommit(user.getUuid(), Constants.USER_FIELD.USERNAME);
                                Constants.PRESS_ENTER_KEY_TO_CONTINUE();
                                System.out.println();
                                break;
                            case "2":
                                System.out.print(Constants.COLOR_WRAPPER("New ADDRESS", Constants.WHITE_BOLD) + " " +
                                        "(must be not empty): ");
                                String newAddress = Constants.IN.nextLine();

                                Database.accounts.get(user.getUuid()).setAddress(newAddress);
                                System.out.println();
                                System.out.println(Database.accounts.get(user.getUuid()));
                                System.out.println();
                                Database.accountUpdateCommit(user.getUuid(), Constants.USER_FIELD.ADDRESS);
                                Constants.PRESS_ENTER_KEY_TO_CONTINUE();
                                System.out.println();
                                break;
                            case "3":
                                System.out.print(Constants.COLOR_WRAPPER("New PHONENUMBER", Constants.WHITE_BOLD) +
                                        " (Format: " +
                                        "XXX-XXX-XXXX): ");
                                String newPhoneNumber = Constants.IN.nextLine();

                                Database.accounts.get(user.getUuid()).setPhoneNumber(newPhoneNumber);
                                System.out.println();
                                System.out.println(Database.accounts.get(user.getUuid()));
                                System.out.println();
                                Database.accountUpdateCommit(user.getUuid(), Constants.USER_FIELD.PHONE);
                                Constants.PRESS_ENTER_KEY_TO_CONTINUE();
                                System.out.println();
                                break;
                            case "4":
                                System.out.print(Constants.COLOR_WRAPPER("New DATE OF BIRTH", Constants.WHITE_BOLD) + " : ");
                                String newYear;
                                while (true) {
                                    System.out.print("YEAR (>= " + Constants.MIN_YEAR + " and <= " + Constants.CURRENT_YEAR + "): ");
                                    newYear = Constants.IN.nextLine();
                                    if (Validation.isYearLegal(newYear)) {
                                        break;
                                    }
                                    System.out.println(Constants.INVALID_INPUT);
                                }
                                String newMonth;
                                while (true) {
                                    System.out.print("MONTH : ");
                                    newMonth = Constants.IN.nextLine();
                                    if (Validation.isMonthLegal(newMonth)) {
                                        break;
                                    }
                                    System.out.println(Constants.INVALID_INPUT);
                                }
                                String createDay;
                                while (true) {
                                    System.out.print("DAY : ");
                                    createDay = Constants.IN.nextLine();
                                    if (Validation.isDayLegal(Integer.parseInt(newYear), Integer.parseInt(newMonth),
                                            createDay)) {
                                        break;
                                    }
                                    System.out.println(Constants.INVALID_INPUT);
                                }

                                Database.accounts.get(user.getUuid()).setDateOfBirth(new DateOfBirth(
                                        Integer.parseInt(newYear),
                                        Integer.parseInt(newMonth),
                                        Integer.parseInt(createDay)));

                                Database.accountUpdateCommit(user.getUuid(), Constants.USER_FIELD.DATE_OF_BIRTH);
                                Constants.PRESS_ENTER_KEY_TO_CONTINUE();
                                System.out.println();
                                break;
                            case "5":
                                System.out.print(Constants.COLOR_WRAPPER("New EMAIL ADDRESS", Constants.WHITE_BOLD) +
                                        " : ");
                                String newEmailAddress = Constants.IN.nextLine();

                                Database.accounts.get(user.getUuid()).setEmailAddress(newEmailAddress);
                                System.out.println();
                                System.out.println(Database.accounts.get(user.getUuid()));
                                System.out.println();
                                Database.accountUpdateCommit(user.getUuid(), Constants.USER_FIELD.EMAIL);
                                Constants.PRESS_ENTER_KEY_TO_CONTINUE();
                                System.out.println();
                                break;
                            case "6":

                                String newPassword =
                                        Menu.enterPassword(Constants.COLOR_WRAPPER("New PASSWORD",
                                                Constants.WHITE_BOLD) + " (the length " +
                                                "must be >= " + Constants.MIN_PASSWORD_LENGTH + "): ");

                                Database.accounts.get(user.getUuid()).setPassword(newPassword);
                                System.out.println();
                                Database.accountUpdateCommit(user.getUuid(), Constants.USER_FIELD.PASSWORD);
                                Constants.PRESS_ENTER_KEY_TO_CONTINUE();
                                System.out.println();
                            case "7":
                                exitSubMenu = true;
                                break;
                            default:
                                System.out.println(Constants.INVALID_INPUT);
                                Constants.PRESS_ENTER_KEY_TO_CONTINUE();
                        }

                    }

                    break;

                case "6":
                    return;

                default:
                    System.out.println(Constants.INVALID_INPUT);
                    System.out.println();
            }
        }

    }

}
