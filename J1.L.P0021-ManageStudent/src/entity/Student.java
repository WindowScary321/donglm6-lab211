package entity;

/* Moi doi tuong luu mot lan dang ky mon hoc cua sinh vien trong mot hoc ky*/
public class Student {
    private String id;
    private String studentName;
    private String semester;
    private Course courseName;

    public Student() {
    }

    public Student(String id, String studentName, String semester, Course courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Course getCourseName() {
        return courseName;
    }

    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Student{" + id + ", " + studentName + ", " + semester
                + ", " + courseName.getLanguage() + '}';
    }
}
