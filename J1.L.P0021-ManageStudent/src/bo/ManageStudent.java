package bo;

import entity.Course;
import entity.Report;
import entity.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import utils.Validator;

/* Xu ly nghiep vu, khong nhap du lieu hoac in thong bao ra console*/
public class ManageStudent {
    private final List<Student> list; // Danh sach luu du lieu chinh cua chuong trinh

    /* Ham ManageStudent (constructor)
     * Muc dich: Khoi tao doi tuong quan ly voi danh sach sinh vien rong
     * Cach thuc: Tao ArrayList<Student> moi va gan cho thuoc tinh list
     * Dau vao: khong co
     * Dau ra: doi tuong ManageStudent co danh sach san sang de them ban ghi
     */
    public ManageStudent() {
        list = new ArrayList<>();
    }

    /* Ham size
     * Muc dich: Lay so luong ban ghi sinh vien hien dang quan ly
     * Cach thuc: Goi size cua list, khong dem so ID phan biet
     * Dau vao: khong co tham so, su dung danh sach list hien tai
     * Dau ra: so nguyen bieu thi so ban ghi trong danh sach
     */
    public int size() {
        return list.size();
    }

    /* Ham getList
     * Muc dich: Lay danh sach sinh vien ma khong cho ben ngoai sua du lieu goc
     * Cach thuc: Tao ArrayList moi, duyet list va them ban sao cua tung Student
     * Dau vao: khong co tham so, su dung danh sach list hien tai
     * Dau ra: danh sach cac ban sao Student, rong neu chua co ban ghi
     */
    public ArrayList<Student> getList() {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : list) {
            result.add(copyStudent(student));
        }
        return result;
    }

    /* Ham copyStudent
     * Muc dich: Sao chep mot ban ghi de tranh chia se Student co the bi sua doi
     * Cach thuc: Tao Student moi voi cung ID, ten, hoc ky va mon hoc
     * Dau vao: student ban ghi khac null can sao chep
     * Dau ra: doi tuong Student moi co thong tin giong ban ghi dau vao
     */
    private Student copyStudent(Student student) {
        return new Student(student.getId(), student.getStudentName(),
                student.getSemester(), student.getCourseName());
    }

    /* Ham requireText
     * Muc dich: Kiem tra truong van ban bat buoc va loai bo khoang trang hai dau
     * Cach thuc: Tu choi null hoac chuoi rong sau trim, nem loi kem ten truong;
     * neu hop le thi tra ve value.trim()
     * Dau vao: value gia tri can kiem tra, fieldName ten truong dung trong loi
     * Dau ra: chuoi hop le da trim; nem IllegalArgumentException neu khong hop le
     */
    private String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be empty.");
        }
        return value.trim();
    }

    /* Ham prepareStudent
     * Muc dich: Kiem tra va chuan hoa ban ghi truoc khi them hoac cap nhat
     * Cach thuc: Kiem tra ID dung dang S/s va chu so; tu choi null, chuoi rong,
     * mon hoc null. Neu ID da ton tai thi dung lai cach viet ID va ten cu;
     * tao ban sao de khong sua doi tuong dau vao
     * Dau vao: student ban ghi do lop goi cung cap
     * Dau ra: ban ghi hop le, da cat khoang trang va dong nhat ten theo ID
     */
    private Student prepareStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student must not be null.");
        }
        String id = Validator.checkStudentId(student.getId());
        String name = requireText(student.getStudentName(), "Student Name");
        String semester = requireText(student.getSemester(), "Semester");
        if (student.getCourseName() == null) {
            throw new IllegalArgumentException("Course must be Java, .Net or C/C++.");
        }
        for (Student current : list) {
            if (current.getId().equalsIgnoreCase(id)) {
                id = current.getId();
                name = current.getStudentName();
                break;
            }
        }
        return new Student(id, name, semester, student.getCourseName());
    }

    /* Ham sameRecord
     * Muc dich: Xac dinh hai ban ghi co cung mot lan dang ky mon hoc hay khong
     * Cach thuc: Kiem tra second khac null, so sanh ID va hoc ky khong phan biet
     * hoa thuong, dong thoi so sanh mon hoc bang hang so enum
     * Dau vao: first ban ghi hop le trong list, second ban ghi can doi chieu
     * Dau ra: true neu cung ID, hoc ky va mon hoc; nguoc lai false
     */
    private boolean sameRecord(Student first, Student second) {
        return second != null
                && first.getId().equalsIgnoreCase(second.getId())
                && first.getSemester().equalsIgnoreCase(second.getSemester())
                && first.getCourseName() == second.getCourseName();
    }

    /* Ham getIndexRecord
     * Muc dich: Tim vi tri cua mot ban ghi trong danh sach dang quan ly
     * Cach thuc: Duyet list tu dau den cuoi, dung sameRecord de doi chieu.
     * tra ve ngay index dau tien phu hop, het danh sach thi tra ve -1
     * Dau vao: student ban ghi can tim theo ID, hoc ky va mon hoc
     * Dau ra: index cua ban ghi tim thay; -1 neu khong ton tai hoac student null
     */
    private int getIndexRecord(Student student) {
        for (int i = 0; i < list.size(); i++) {
            if (sameRecord(list.get(i), student)) {
                return i;
            }
        }
        return -1;
    }

    /* Ham isExisted
     * Muc dich: Kiem tra mot ban ghi dang ky mon hoc da ton tai hay chua
     * Cach thuc: Goi getIndexRecord va kiem tra ket qua lon hon hoac bang 0
     * Dau vao: student ban ghi can kiem tra theo ID, hoc ky va mon hoc
     * Dau ra: true neu tim thay ban ghi tuong ung, nguoc lai false
     */
    public boolean isExisted(Student student) {
        return getIndexRecord(student) >= 0;
    }

    /* Ham add
     * Muc dich: Them mot ban ghi dang ky mon hoc, khong cho phep trung lap
     * Cach thuc: Chuan hoa du lieu, kiem tra bo ba ID/hoc ky/mon hoc, roi them vao list
     * Dau vao: student thong tin can them
     * Dau ra: true neu them thanh cong; nem IllegalArgumentException neu du lieu sai
     */
    public boolean add(Student student) {
        Student newStudent = prepareStudent(student);
        if (isExisted(newStudent)) {
            throw new IllegalArgumentException("This record already exists.");
        }
        return list.add(newStudent);
    }

    /* Ham update
     * Muc dich: Thay the dung mot ban ghi, giu nguyen du lieu neu cap nhat that bai
     * Cach thuc: Tim vi tri cu, kiem tra ban ghi moi voi tat ca vi tri khac;
     * bo qua vi tri dang sua de cho phep nhap lai nguyen thong tin cu
     * Dau vao: oldStudentRecord ban ghi cu, newStudentRecord thong tin moi
     * Dau ra: cap nhat list hoac nem ngoai le neu rong, khong tim thay, trung lap
     */
    public void update(Student oldStudentRecord, Student newStudentRecord) {
        checkNotEmpty();
        int index = getIndexRecord(oldStudentRecord);
        if (index < 0) {
            throw new IllegalArgumentException("Student record not found.");
        }
        Student newStudent = prepareStudent(newStudentRecord);
        for (int i = 0; i < list.size(); i++) {
            if (i != index && sameRecord(list.get(i), newStudent)) {
                throw new IllegalArgumentException("New record is duplicate.");
            }
        }
        // Chi thay doi danh sach sau khi tat ca kiem tra deu thanh cong
        list.set(index, newStudent);
    }

    /* Ham delete
     * Muc dich: Xoa mot ban ghi duoc chon, khong xoa toan bo sinh vien cung ID
     * Cach thuc: Tim index theo ID/hoc ky/mon hoc va remove dung index do
     * Dau vao: student ban ghi can xoa
     * Dau ra: true neu xoa thanh cong, nem ngoai le neu khong tim thay
     */
    public boolean delete(Student student) {
        checkNotEmpty();
        int index = getIndexRecord(student);
        if (index < 0) {
            throw new IllegalArgumentException("Student record not found.");
        }
        list.remove(index);
        return true;
    }

    /* Ham getListStudentById
     * Muc dich: Lay tat ca cac ban ghi dang ky mon hoc cua mot ID sinh vien
     * Cach thuc: Kiem tra ID dung dang S/s va chu so, duyet list va so sanh
     * equalsIgnoreCase; them ban sao cua moi Student phu hop vao ket qua
     * Dau vao: id ma sinh vien can tim, duoc cat khoang trang hai dau
     * Dau ra: danh sach cac ban sao Student cung ID, rong neu khong tim thay;
     * nem IllegalArgumentException neu ID null, rong hoac sai dinh dang
     */
    public ArrayList<Student> getListStudentById(String id) {
        String searchId = Validator.checkStudentId(id);
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : list) {
            if (student.getId().equalsIgnoreCase(searchId)) {
                result.add(copyStudent(student));
            }
        }
        return result;
    }

    /* Ham getListStudentByName
     * Muc dich: Tim theo ten hoac mot phan ten, khong phan biet hoa thuong
     * Cach thuc: Doi ten va tu khoa ve chu thuong, dung contains de kiem tra
     * Dau vao: name ten hoac tu khoa khong rong
     * Dau ra: danh sach ban sao cua cac ban ghi phu hop, rong neu khong tim thay
     */
    public ArrayList<Student> getListStudentByName(String name) {
        String keyword = requireText(name, "Student Name").toLowerCase(Locale.ROOT);
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : list) {
            if (student.getStudentName().toLowerCase(Locale.ROOT).contains(keyword)) {
                result.add(copyStudent(student));
            }
        }
        return result;
    }

    /* Ham sortStudentsByName
     * Muc dich: Sap xep danh sach ket qua theo toan bo Student Name tang dan
     * Cach thuc: Dung Collections.sort va cai dat compare cua Comparator<Student>
     * Dau vao: students danh sach can sap xep, khong phai danh sach goc
     * Dau ra: thay doi thu tu cac phan tu trong danh sach duoc truyen vao
     */
    public void sortStudentsByName(List<Student> students) {
        Collections.sort(students, new Comparator<Student>() {
            /* Ham compare
             * Muc dich: Xac dinh thu tu giua hai ban ghi sinh vien theo ten
             * Cach thuc: So sanh toan bo Student Name bang compareToIgnoreCase
             * Dau vao: first va second la hai ban ghi can so sanh
             * Dau ra: so am, 0 hoac so duong neu ten first dung truoc, bang
             * hoac dung sau ten second theo thu tu tu dien khong phan biet hoa thuong
             */
            @Override
            public int compare(Student first, Student second) {
                return first.getStudentName().compareToIgnoreCase(second.getStudentName());
            }
        });
    }

    /* Ham report
     * Muc dich: Thong ke so lan hoc tung mon cua moi sinh vien qua cac hoc ky
     * Cach thuc: Dung Map hai tang ID -> mon hoc -> dong bao cao. TreeMap so sanh
     * ID khong phan biet hoa thuong; EnumMap phan biet ba mon hoc. Gap nhom moi
     * thi tao Report, gap lai nhom do thi tang Total. Sap xep ket qua theo ten
     * Dau vao: danh sach sinh vien hien tai
     * Dau ra: danh sach Report moi, khong thay doi danh sach goc hay cong don lan cu
     */
    public ArrayList<Report> report() {
        checkNotEmpty();
        Map<String, Map<Course, Report>> groups = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        ArrayList<Report> result = new ArrayList<>();
        // Duyet qua toan bo danh sach sinh vien 
        for (Student student : list) {
            Map<Course, Report> courses = groups.get(student.getId());
            if (courses == null) {
                courses = new EnumMap<>(Course.class);
                groups.put(student.getId(), courses);
            }
            Report row = courses.get(student.getCourseName());
            if (row == null) {
                row = new Report(student.getId(), student.getStudentName(), student.getCourseName());
                courses.put(student.getCourseName(), row);
                result.add(row);
            } else {
                row.increaseTotal();
            }
        }
        Collections.sort(result, new Comparator<Report>() {
            /* Ham compare
             * Muc dich: Xac dinh thu tu giua hai dong bao cao theo ten sinh vien
             * Cach thuc: So sanh Student Name bang compareToIgnoreCase,
             * khong dung ten lam khoa de gop cac nhom bao cao
             * Dau vao: first va second la hai dong Report can so sanh
             * Dau ra: so am, 0 hoac so duong neu ten first dung truoc, bang
             * hoac dung sau ten second theo thu tu tu dien khong phan biet hoa thuong
             */
            @Override
            public int compare(Report first, Report second) {
                return first.getStudentName().compareToIgnoreCase(second.getStudentName());
            }
        });
        return result;
    }

    /* Ham checkNotEmpty
     * Muc dich: Chan cac thao tac can du lieu khi danh sach sinh vien dang rong
     * Cach thuc: Kiem tra list.isEmpty, nem IllegalStateException neu khong co ban ghi
     * Dau vao: khong co tham so, su dung danh sach list hien tai
     * Dau ra: khong tra ve gia tri; ket thuc binh thuong neu co du lieu,
     * nem ngoai le voi thong bao List is empty. neu danh sach rong
     */
    public void checkNotEmpty() {
        if (list.isEmpty()) {
            throw new IllegalStateException("List is empty.");
        }
    }
}
