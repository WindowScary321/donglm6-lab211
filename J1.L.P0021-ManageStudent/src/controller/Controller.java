package controller;

import bo.ManageStudent;
import bo.StudentInputer;
import entity.Report;
import entity.Student;
import java.util.ArrayList;
import java.util.List;
import utils.Validator;

/* Dieu phoi nhap lieu, xu ly nghiep vu va hien thi ket qua cho nguoi dung*/
public class Controller {
    private static final int MINIMUM_STUDENTS = 5;
    private final ManageStudent studentManager; // Tao doi tuong ManageStudent se tao them danh sach ben class nay
    
    // Khi ham dc goi, khoi tao doi tuong ManageStudent
    public Controller() {
        studentManager = new ManageStudent();
    }

    /* Ham inputStudent
     * Muc dich: Nhap day du mot ban ghi, dung chung cho Create va Update
     * Cach thuc: Nhap ID truoc; neu da ton tai thi lay ten cu, nguoc lai nhap ten;
     * sau do nhap hoc ky va chon mon hoc
     * Dau vao: thong tin nguoi dung nhap tu ban phim
     * Dau ra: Student moi, chua duoc them vao danh sach
     */
    private Student inputStudent() {
        StudentInputer inputer = new StudentInputer();
        inputer.inputID();
        Student student = inputer.getStudent();
        ArrayList<Student> sameId = studentManager.getListStudentById(student.getId());
        if (sameId.isEmpty()) {
            inputer.inputStudentName();
        } else {
            student.setId(sameId.get(0).getId());
            student.setStudentName(sameId.get(0).getStudentName());
            System.out.println("Name: " + student.getStudentName());
        }
        inputer.inputSemester();
        inputer.inputCourseName();
        return student;
    }

    /* Ham createStudent
     * Muc dich: Them sinh vien, tao du it nhat 5 ban ghi truoc khi hoi dung
     * Cach thuc: Lap nhap va them; neu trung thi bao loi va nhap lai. Tu moc 5
     * ban ghi tro len, sau moi lan them thanh cong hoi Y/N; N quay lai menu
     * Dau vao: cac ban ghi va lua chon Y/N tu ban phim
     * Dau ra: them cac ban ghi hop le vao danh sach, khong dem lan nhap sai
     */
    public void createStudent() {
        System.out.println("------------ Create Student ------------");
        while (true) {
            Student student = inputStudent();
            try {
                studentManager.add(student);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
            System.out.println("Student added successfully.");
            if (studentManager.size() >= MINIMUM_STUDENTS) {
                String choice = Validator.getString("Do you want to continue (Y/N)? ",
                        "Please enter Y or N.", "[YyNn]");
                if (choice.equalsIgnoreCase("N")) {
                    return;
                }
            }
        }
    }

    /* Ham findAndSort
     * Muc dich: Tim sinh vien theo ten va hien thi ket qua tang dan theo ten
     * Cach thuc: Lay danh sach phu hop, kiem tra rong, sap xep ban sao va in bang
     * Dau vao: ten hoac mot phan ten tu ban phim
     * Dau ra: hien thi cac ban ghi phu hop hoac bao loi khong tim thay
     */
    public void findAndSort() {
        studentManager.checkNotEmpty();
        String name = Validator.getString("Enter Student Name to find: ",
                "Student Name must not be empty.", ".+");
        ArrayList<Student> result = studentManager.getListStudentByName(name);
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Student name not found.");
        }
        studentManager.sortStudentsByName(result);
        displayStudents(result);
    }

    /* Ham updateOrDelete
     * Muc dich: Tim ID, chon dung ban ghi roi cap nhat hoac xoa
     * Cach thuc: Hien thi tat ca ban ghi cung ID voi so thu tu, kiem tra lua chon;
     * U nhap thong tin moi va goi update, D goi delete voi dung ban ghi da chon
     * Dau vao: ID, so thu tu, U/D va thong tin moi neu cap nhat
     * Dau ra: thay doi mot ban ghi hoac bao loi, giu nguyen du lieu neu that bai
     */
    public void updateOrDelete() {
        studentManager.checkNotEmpty();
        String id = Validator.getStudentId("Enter ID to find (S/s followed by digits): ");
        ArrayList<Student> result = studentManager.getListStudentById(id);
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Student ID not found.");
        }
        displayStudents(result);
        int record = Validator.getInt("Enter record number: ",
                "Record number must be from 1 to " + result.size() + ".",
                "Record number must be an integer.", 1, result.size());
        Student selected = result.get(record - 1);
        System.out.println("Selected: " + selected);
        String choice = Validator.getString("Do you want to update (U) or delete (D) student? ",
                "Please enter U or D.", "[UuDd]");
        if (choice.equalsIgnoreCase("U")) {
            studentManager.update(selected, inputStudent());
            System.out.println("Student updated successfully.");
        } else {
            studentManager.delete(selected);
            System.out.println("Student deleted successfully.");
        }
    }

    /* In bang theo danh sach duoc truyen vao; so thu tu dung de chon ban ghi. */
    private void displayStudents(List<Student> students) {
        String format = "| %-4s | %-12s | %-25s | %-12s | %-11s |%n";
        System.out.printf(format, "No.", "ID", "Student Name", "Semester", "Course Name");
        // Duyet qua taom no danh sach sinh vien
        for (int i = 0; i < students.size(); i++) {
            // Lay du lieu cua tung nguoi mot roi in ra
            Student student = students.get(i);
            System.out.printf(format, i + 1, student.getId(), student.getStudentName(),
                    student.getSemester(), student.getCourseName().getLanguage());
        }
    }

    /* Ham report
     * Muc dich: Hien thi tong so lan hoc tung mon cua moi sinh vien
     * Cach thuc: Lay ket qua thong ke tu manager, in moi nhom mot dong;
     * viec dem theo ID va mon hoc da duoc xu ly trong lop nghiep vu
     * Dau vao: danh sach hien tai trong studentManager
     * Dau ra: bang Student Name, Course, Total; bao loi neu danh sach rong
     */
    public void report() {
        ArrayList<Report> result = studentManager.report();
        String format = "| %-25s | %-11s | %-5s |%n";
        System.out.printf(format, "Student Name", "Course", "Total");
        for (Report row : result) {
            System.out.printf(format, row.getStudentName(), row.getCourseName().getLanguage(),
                    row.getTotal());
        }
    }
}
