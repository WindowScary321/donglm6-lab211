package entity;

/* Cac mon hoc duoc o trong de bai*/
public enum Course {
    JAVA("Java"),
    DOT_NET(".Net"),
    C_CPP("C/C++");

    private final String language;

    Course(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    /* Ham getCourse
     * Muc dich: Doi lua chon tu menu thanh mon hoc tuong ung
     * Cach thuc: Anh xa 1, 2, 3 sang ba hang so enum, bao loi neu ngoai pham vi
     * Dau vao: type lua chon mon hoc
     * Dau ra: mon hoc duoc chon
     */
    public static Course getCourse(int type) {
        switch (type) {
            case 1 -> {
                return JAVA;
            }
            case 2 -> {
                return DOT_NET;
            }
            case 3 -> {
                return C_CPP;
            }
            default -> throw new IllegalArgumentException("Please enter number 1 to 3.");
        }
    }
}
