package Constants;

public class AdminApiConstants {
    public static class PostManage {
        private static String BASE = "http://admin-post-management-service/";
        public static String HIDE_POST = BASE+"hide";
        public static String SOFT_DELETE_POST = BASE+"delete";
    }

    public static class ReportManage {
        private static String BASE = "http://admin-report-management-service/";
        public static String LIST_REPORT = BASE+"list";
        public static String DETAILS = BASE+"details";
        public static String HANDLE = BASE+"handle";
    }

    public static class UserManage {
        private static String BASE = "http://admin-user-management-service/";
        public static String LIST_USER = BASE+"list";
        public static String BLOCK_USER = BASE+"block";
    }
}
