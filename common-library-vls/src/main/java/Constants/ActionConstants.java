package Constants;

public class ActionConstants {
    public static class AdminAccount {
        public static String ADMIN_ROLE = "Quản trị";
        public static String MANAGER_ROLE = "Quản lý";
    }

    public static class PostStatus {
        public static String CLOSED = "Đóng";
        public static String OPEN = "Mở";
        public static String COMPLETE = "Hoàn tất";
        public static String CANCELLED = "Huỷ";
    }

    public static class PostMethod {
        public static String TRADE = "Đổi";
        public static String FREE = "Free";
    }

    public static class UserHandling {
        public static String BLOCK_USER = "Khoá tài khoản";
        public static String UNBLOCK_USER = "Mở khoá";
    }

    public static class PostHandling {
        public static String DELETE_POST = "Xoá bài đăng";
        public static String HIDE_POST = "Ẩn bài đăng";
    }
}
