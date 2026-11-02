package bo;

import entity.Course;
import entity.Student;
import utils.Validator;

/* Tach thao tac nhap lieu khoi lop quan ly danh sach sinh vien */
public class StudentInputer {
    private final Student student;

    /* Ham StudentInputer (constructor)
     * Muc dich: Khoi tao doi tuong ho tro nhap mot ban ghi sinh vien
     * Cach thuc: Tao Student rong va gan cho thuoc tinh student
     * Dau vao: khong co
     * Dau ra: doi tuong StudentInputer chua mot Student san sang nhan du lieu
     */
    public StudentInputer() {
        student = new Student();
    }

    /* Ham inputID
     * Muc dich: Nhap ID sinh vien bat dau bang S/s va theo sau la cac chu so
     * Cach thuc: Dung Validator.getStudentId de kiem tra dinh dang va nhap lai
     * neu sai; hop le thi gan vao student qua setId, khong tu them S vao ID
     * Dau vao: ID nguoi dung nhap tu ban phim, vi du S1, s123, S001
     * Dau ra: cap nhat id cua student bang ID hop le da trim
     */
    public void inputID() {
        student.setId(Validator.getStudentId("Enter ID (S/s followed by digits): "));
    }

    /* Ham inputStudentName
     * Muc dich: Nhap ten sinh vien khong rong, chap nhan ca ten tieng Viet
     * Cach thuc: Dung Validator.getString voi regex .+ de nhap chuoi khong rong,
     * khong gioi han chu cai ASCII; hop le thi gan qua setStudentName
     * Dau vao: ten sinh vien nguoi dung nhap tu ban phim
     * Dau ra: cap nhat studentName cua student, da cat khoang trang hai dau
     */
    public void inputStudentName() {
        student.setStudentName(Validator.getString("Enter Student Name: ",
                "Student Name must not be empty.", ".+"));
    }

    /* Ham inputSemester
     * Muc dich: Nhap hoc ky cua ban ghi sinh vien, khong chap nhan de trong
     * Cach thuc: Dung Validator.getString de kiem tra chuoi va nhap lai neu sai;
     * hop le thi gan vao student qua setSemester
     * Dau vao: hoc ky nguoi dung nhap tu ban phim
     * Dau ra: cap nhat semester cua student bang chuoi hop le
     */
    public void inputSemester() {
        student.setSemester(Validator.getString("Enter Semester: ",
                "Semester must not be empty.", ".+"));
    }

    /* Ham inputCourseName
     * Muc dich: Chi cho phep chon mot trong ba mon hoc cua de
     * Cach thuc: Nhap so nguyen 1..3, doi sang Course va gan vao ban ghi dang nhap
     * Dau vao: du lieu nguoi dung nhap tu ban phim
     * Dau ra: cap nhat courseName cua student
     */
    public void inputCourseName() {
        int choice = Validator.getInt("Only three courses:\n"
                + "1. Java\n2. .Net\n3. C/C++\nEnter Course: ",
                "Please enter number 1 to 3.", "Course must be an integer.", 1, 3);
        student.setCourseName(Course.getCourse(choice));
    }

    /* Ham getStudent
     * Muc dich: Lay doi tuong Student ma StudentInputer dang nhap thong tin
     * Cach thuc: Tra ve truc tiep thuoc tinh student, khong tao ban sao
     * Dau vao: khong co tham so, su dung student cua doi tuong hien tai
     * Dau ra: doi tuong Student de Controller doc hoac bo sung thong tin
     */
    public Student getStudent() {
        return student;
    }
}
