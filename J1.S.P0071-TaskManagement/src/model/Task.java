package model;

public class Task {
    // Luu cac thong tin cua mot task de quan ly va hien thi
    private int id;
    private int taskTypeID;
    private String requirementName;
    private String date;
    private double planFrom;
    private double planTo;
    private String assignee;
    private String reviewer;

    /* Ham khoi tao Task
     * Muc dich: Tao doi tuong luu thong tin cua mot task
     * Cach thuc: Gan cac tham so vao cac thuoc tinh tuong ung; du lieu duoc kiem tra truoc khi tao task
     * Dau vao: id, taskTypeID, requirementName, date, planFrom, planTo, assignee va reviewer
     * Dau ra: doi tuong Task chua cac thong tin da nhan
     */
    public Task(int id, int taskTypeID, String requirementName, String date,
            double planFrom, double planTo, String assignee, String reviewer) {
        this.id = id;
        this.taskTypeID = taskTypeID;
        this.requirementName = requirementName;
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
        this.assignee = assignee;
        this.reviewer = reviewer;
    }

    // Cac ham getter lay thong tin da luu cua task
    public int getId() { return id; }
    public String getRequirementName() { return requirementName; }
    public String getDate() { return date; }
    public String getAssignee() { return assignee; }
    public String getReviewer() { return reviewer; }

    /* Ham getTaskTypeName
     * Muc dich: Lay ten task type tu ID co dinh
     * Cach thuc: Luu cac ten Code, Test, Design, Review trong mang,
     * lay ten tai vi tri taskTypeID - 1 vi chi so mang bat dau tu 0
     * Dau vao: ko co, su dung taskTypeID cua task (tu 1 den 4)
     * Dau ra: ten task type tuong ung
     */
    public String getTaskTypeName() {
        String[] types = {"Code", "Test", "Design", "Review"};
        return types[taskTypeID - 1];
    }

    /* Ham getTime
     * Muc dich: Tinh thoi gian thuc hien task
     * Cach thuc: Lay moc ket thuc planTo tru moc bat dau planFrom
     * Dau vao: ko co, su dung planFrom va planTo cua task
     * Dau ra: khoang thoi gian giua hai moc
     */
    public double getTime() {
        return planTo - planFrom;
    }
}
