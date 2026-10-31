package controller;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Task;

public class TaskManagement {
    // Tao mang luu tru du lieu chinh cua chuong trinh
    private final ArrayList<Task> tasks = new ArrayList<>();

    /* Ham addTask
     * Muc dich: Kiem tra thong tin va them task vao danh sach
     * Cach thuc: Kiem tra tung truong truoc khi tao task, ID bang ID task cuoi cong 1
     * Dau vao: ten, nguoi thuc hien, reviewer, loai task, ngay va hai moc gio
     * Dau ra: ID cua task vua them; bao Exception neu du lieu khong hop le
     */
    public int addTask(String requirementName, String assignee, String reviewer,
            String taskTypeID, String date, String planFrom, String planTo) throws Exception {
        String name = checkText(requirementName, "Requirement Name");
        String assigned = checkText(assignee, "Assignee");
        String reviewed = checkText(reviewer, "Reviewer");
        int type = checkTaskType(taskTypeID);
        String validDate = checkDate(date);
        double from = checkTime(planFrom);
        double to = checkTime(planTo);
        // Neu thoi gian from hon to thi bat loi va gui ve main
        if (from >= to) {
            throw new IllegalArgumentException("Plan From must be less than Plan To.");
        }
        int id = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;
        tasks.add(new Task(id, type, name, validDate, from, to, assigned, reviewed));
        return id;
    }

    /* Ham deleteTask
     * Muc dich: Xoa task theo ID
     * Cach thuc: Chuyen ID sang so nguyen, tim va xoa task tuong ung
     * Dau vao: id dang chuoi
     * Dau ra: ko co; bao Exception neu ID khong hop le hoac khong ton tai
     */
    public void deleteTask(String id) throws Exception {
        // Tao bien tam
        int taskId;
        // Neu dau vao ko phai so thap phan thi bao loi cho nguoi dung 
        try {
            taskId = Integer.parseInt(id.trim());
        } catch (NullPointerException | NumberFormatException ex) {
            throw new IllegalArgumentException("ID must be an integer.");
        }
        // Tim kiem bang id cua task, neu ton tai thi xoa task
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                tasks.remove(task);
                return;
            }
        }
        // Ko ton tai task, bao loi
        throw new IllegalArgumentException("Task does not exist.");
    }

    /* Ham getDataTasks
     * Muc dich: Lay danh sach task theo thu tu ID tang dan
     * Cach thuc: Tao ban sao danh sach tasks, dung Comparator.comparingInt de sap xep theo ID,
     * tra ve ban sao de ham goi khong them hoac xoa truc tiep tren danh sach goc
     * Dau vao: ko co
     * Dau ra: ban sao danh sach task da sap xep
     */
    public List<Task> getDataTasks() {
        ArrayList<Task> result = new ArrayList<>(tasks);
        result.sort(Comparator.comparingInt(Task::getId)); // sap xep theo thu tu tang dan
        return result;
    }

    /* Ham checkText
     * Muc dich: Kiem tra cac truong van ban bat buoc khong duoc de trong
     * Cach thuc: Dung trim de loai bo khoang trang o hai dau, kiem tra chuoi rong,
     * bat NullPointerException neu value la null va bao loi kem ten truong
     * Dau vao: value chuoi can kiem tra, field ten truong de hien thi trong thong bao loi
     * Dau ra: chuoi da loai bo khoang trang o hai dau; bao IllegalArgumentException neu null hoac rong
     */
    public String checkText(String value, String field) {
        try {
            String text = value.trim();
            if (text.isEmpty()) {
                throw new IllegalArgumentException(field + " must not be empty.");
            }
            return text;
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException(field + " must not be empty.");
        }
    }

    /* Ham checkTaskType
     * Muc dich: Kiem tra ID loai task thuoc du lieu co dinh tu 1 den 4
     * Cach thuc: Loai bo khoang trang o hai dau, dung Integer.parseInt de chuyen sang so nguyen,
     * bat NullPointerException va NumberFormatException neu khong chuyen doi duoc,
     * sau do kiem tra gia tri trong khoang 1 den 4 tuong ung Code, Test, Design va Review
     * Dau vao: value chuoi ID loai task can kiem tra
     * Dau ra: ID loai task hop le; bao IllegalArgumentException neu du lieu khong hop le
     */
    public int checkTaskType(String value) {
        int type;
        try {
            type = Integer.parseInt(value.trim());
        } catch (NullPointerException | NumberFormatException ex) {
            throw new IllegalArgumentException("Task Type must be an integer from 1 to 4.");
        }
        if (type < 1 || type > 4) {
            throw new IllegalArgumentException("Task Type must be from 1 to 4.");
        }
        return type;
    }

    /* Ham checkDate
     * Muc dich: Kiem tra ngay ton tai va dung dinh dang dd-MM-yyyy
     * Cach thuc: Goi checkText de kiem tra chuoi, dung regex kiem tra 2 chu so ngay,
     * 2 chu so thang va 4 chu so nam, sau do dung SimpleDateFormat voi setLenient(false)
     * de kiem tra ngay ton tai, dung ParsePosition de dam bao da doc het chuoi ngay
     * Dau vao: value chuoi ngay can kiem tra, khong bat buoc lon hon ngay hien tai
     * Dau ra: chuoi ngay hop le; bao IllegalArgumentException neu rong, sai dinh dang hoac ngay khong ton tai
     */
    public String checkDate(String value) {
        checkText(value, "Date"); // Van kiem tra null, rong hoac chi gom dau cach
        String date = value;     // Giu nguyen chuoi de kiem tra dinh dang
        // Kiem tra dau vao co phai dd-mm-yyyy bang regex
        if (!date.matches("[0-9]{2}-[0-9]{2}-[0-9]{4}")) {
            throw new IllegalArgumentException("Date must be in dd-MM-yyyy format.");
        }
        // Them format co dinh cho ngay thang
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        format.setLenient(false);
        // Ngay thang phai ton tai trong lich, tranh 31-02 hoac 36-12
        ParsePosition position = new ParsePosition(0);
        if (format.parse(date, position) == null || position.getIndex() != date.length()) {
            throw new IllegalArgumentException("Date is invalid.");
        }
        return date; // tra ve ngay thang hop le
    }

    /* Ham checkTime
     * Muc dich: Kiem tra moc gio lam viec tu 8.0 den 17.5 voi buoc 0.5
     * Cach thuc: Loai bo khoang trang o hai dau, dung Double.parseDouble de chuyen sang so thuc,
     * bat NullPointerException va NumberFormatException neu khong chuyen doi duoc,
     * loai bo NaN va vo cuc bang Double.isFinite, kiem tra pham vi va time * 2 phai la so nguyen
     * Dau vao: value chuoi moc gio Plan From hoac Plan To can kiem tra
     * Dau ra: moc gio hop le; bao IllegalArgumentException neu du lieu khong hop le
     */
    public double checkTime(String value) {
        // Dat bien tam 
        double time;
        try {
            // Chuyen sang Double de bat loi
            time = Double.parseDouble(value.trim());
        } catch (NullPointerException | NumberFormatException ex) {
            throw new IllegalArgumentException("Time must be a number.");
        }
        // Neu thoi gian nho hon 8, lon hon 17.5 hoac ko nhay buoc 0.5 thi bao loi
        if (!Double.isFinite(time) || time < 8.0 || time > 17.5 || time * 2 != Math.floor(time * 2)) {
            throw new IllegalArgumentException("Time must be from 8.0 to 17.5 in steps of 0.5.");
        }
        return time; // tra ve thoi gian hop le
    }
}
