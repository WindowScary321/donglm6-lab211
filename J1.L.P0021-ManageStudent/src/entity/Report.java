package entity;

/* Mot dong bao cao, phan biet sinh vien bang ID thay vi chi dua vao ten*/
public class Report {
    private final String studentId;
    private final String studentName;
    private final Course courseName;
    private int total;

    public Report(String studentId, String studentName, Course courseName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
        total = 1;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Course getCourseName() {
        return courseName;
    }

    public int getTotal() {
        return total;
    }

    /** Cong them mot lan hoc khi gap lai cung ID va mon hoc. */
    public void increaseTotal() {
        total++;
    }
}
