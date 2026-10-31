package main;

import controller.TaskManagement;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.Task;

public class Main {
    /* Ham main
     * Muc dich: Hien thi menu va dieu huong cac chuc nang quan ly task
     * Cach thuc: Doc lua chon, goi ham tuong ung va lap lai den khi chon Exit
     * Dau vao: args cac tham so dong lenh (khong su dung)
     * Dau ra: ko co
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManagement manager = new TaskManagement();
        try {
            while (true) {
                System.out.println("========= Task program =========");
                System.out.println("1. Add Task");
                System.out.println("2. Delete Task");
                System.out.println("3. Display Task");
                System.out.println("4. Exit");
                System.out.print("Please choose one option: ");
                // Chuyen lua chon sang so nguyen, bao loi va hien lai menu neu nhap sai
                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid option. Please enter 1 to 4.");
                    continue;
                }
                switch (choice) {
                    case 1 -> addTask(manager, scanner);
                    case 2 -> deleteTask(manager, scanner);
                    case 3 -> displayTasks(manager);
                    case 4 -> {
                        return;
                    }
                    default -> System.out.println("Invalid option. Please enter 1 to 4.");
                }
            }
        } catch (NoSuchElementException ex) {
            // Ket thuc neu khong con du lieu nhap
            System.out.println();
        }
    }

    /* Ham input
     * Muc dich: Nhap va kiem tra mot truong thong tin
     * Cach thuc: Hien thi loi nhac, doc mot dong bang scanner.nextLine,
     * goi validator.accept de kiem tra; neu gap IllegalArgumentException thi in loi va nhap lai,
     * neu hop le thi tra ve chuoi vua nhap, khong bat ngoai le EOF de ham main xu ly
     * Dau vao: scanner doi tuong doc du lieu, prompt loi nhac, validator ham kiem tra nhan mot chuoi
     * Dau ra: chuoi du lieu da vuot qua kiem tra, giu nguyen noi dung nguoi dung nhap
     */
    private static String input(Scanner scanner, String prompt, java.util.function.Consumer<String> validator) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine();
            try {
                validator.accept(value);
                return value;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /* Ham addTask
     * Muc dich: Nhap thong tin va them task
     * Cach thuc: Lan luot nhap ten, loai task, ngay, hai moc gio, assignee va reviewer qua ham input,
     * yeu cau nhap lai ngay truong bi sai; khi tat ca hop le thi goi manager.addTask
     * va hien thi ID task vua tao, neu co Exception thi in thong bao loi
     * Dau vao: manager doi tuong quan ly task, scanner doi tuong doc du lieu
     * Dau ra: ko co
     */
    private static void addTask(TaskManagement manager, Scanner scanner) {
        System.out.println("------------ Add Task ------------");
        String name = input(scanner, "Requirement Name: ", v -> manager.checkText(v, "Requirement Name"));
        String type = input(scanner, "Task Type: ", v -> manager.checkTaskType(v));
        String date = input(scanner, "Date: ", v -> manager.checkDate(v));
        // From phai nho hon 17.5 de con it nhat mot moc To hop le
        String from = input(scanner, "From: ", v -> {
            if (manager.checkTime(v) >= 17.5) {
                throw new IllegalArgumentException("Plan From must be less than 17.5.");
            }
        });
        // Kiem tra moc To hop le va lon hon From, neu sai chi yeu cau nhap lai To
        String to = input(scanner, "To: ", v -> {
            if (manager.checkTime(v) <= manager.checkTime(from)) {
                throw new IllegalArgumentException("Plan From must be less than Plan To.");
            }
        });
        String assignee = input(scanner, "Assignee: ", v -> manager.checkText(v, "Assignee"));
        String reviewer = input(scanner, "Reviewer: ", v -> manager.checkText(v, "Reviewer"));
        try {
            int id = manager.addTask(name, assignee, reviewer, type, date, from, to);
            System.out.println("Task added successfully. ID: " + id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /* Ham deleteTask
     * Muc dich: Nhap ID va xoa task tuong ung
     * Cach thuc: Dung Integer.valueOf kiem tra ID la so nguyen va nhap lai neu sai,
     * sau do goi manager.deleteTask de tim va xoa task; thong bao thanh cong hoac in loi,
     * neu ID la so nguyen nhung khong ton tai thi bao loi va quay lai menu
     * Dau vao: manager doi tuong quan ly task, scanner doi tuong doc du lieu
     * Dau ra: ko co
     */
    private static void deleteTask(TaskManagement manager, Scanner scanner) {
        System.out.println("------------ Del Task ------------");
        String id = input(scanner, "ID: ", v -> {
            try {
                Integer.valueOf(v.trim());
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("ID must be an integer.");
            }
        });
        try {
            manager.deleteTask(id);
            System.out.println("Task deleted successfully.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /* Ham displayTasks
     * Muc dich: Hien thi danh sach task theo ID tang dan
     * Cach thuc: Lay danh sach da sap xep bang manager.getDataTasks, neu rong thi thong bao,
     * nguoc lai in ten cot va duyet tung task de hien thi thong tin bang printf,
     * hien thi ten task type thay cho ID loai task va Time bang Plan To tru Plan From
     * Dau vao: manager quan ly danh sach task
     * Dau ra: ko co
     */
    private static void displayTasks(TaskManagement manager) {
        System.out.println("-------------------------------- Task --------------------------------");
        List<Task> tasks = manager.getDataTasks();
        if (tasks.isEmpty()) {
            System.out.println("No task to display.");
            return;
        }
        System.out.printf("%-5s %-20s %-12s %-12s %-8s %-15s %-15s%n",
                "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
        // Dung Locale.US de Time hien thi dau cham thap phan va mot chu so sau dau cham
        for (Task task : tasks) {
            System.out.printf(java.util.Locale.US, "%-5d %-20s %-12s %-12s %-8.1f %-15s %-15s%n",
                    task.getId(), task.getRequirementName(), task.getTaskTypeName(),
                    task.getDate(), task.getTime(), task.getAssignee(), task.getReviewer());
        }
    }
}
