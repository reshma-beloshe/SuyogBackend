//package com.SuyogHospital.Config;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.PostConstruct;
//
//@Component
//public class DataMigrationUtility {
//
//    @Autowired
//    @Qualifier("oldJdbcTemplate")
//    private JdbcTemplate oldJdbcTemplate;
//
//    @Autowired
//    @Qualifier("newJdbcTemplate")
//    private JdbcTemplate newJdbcTemplate;
//
//    @PostConstruct
//    public void migrateData() {
//        String selectQuery = "SELECT id, added_by, approved_by, date, description ,designation_name, is_approved, status, time, updated_by FROM suyog.tbl_designation";
//
//        List<Map<String, Object>> oldData = oldJdbcTemplate.queryForList(selectQuery);
//
//        for (Map<String, Object> row : oldData) {
//            try {
//                Object approvedBy = row.get("approved_by");
//
//                // Check if the 'approved_by' field is valid (Boolean or 0/1)
//                if (approvedBy == null || isBitCompatible(approvedBy)) {
//                    String insertQuery = "INSERT INTO suyog.tbl_designation " +
//                            "(id, added_by, approved_by, date, description ,designation_name, is_approved, status, time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//                    newJdbcTemplate.update(
//                            insertQuery,
//                            row.get("id"),
//                            row.get("added_by"),
//                            convertToBit(approvedBy),
//                            row.get("date"),
//                            row.get("designation_name")
//                    );
//                } else {
//                    System.out.println("❌ Skipped record (invalid approved_by): " + approvedBy);
//                }
//
//            } catch (Exception ex) {
//                System.err.println("❌ Error inserting record with ID " + row.get("id") + ": " + ex.getMessage());
//            }
//        }
//
//        System.out.println("✅ Data migration completed.");
//    }
//
//    private boolean isBitCompatible(Object value) {
//        if (value instanceof Boolean) return true;
//        if (value instanceof Number) {
//            int intValue = ((Number) value).intValue();
//            return intValue == 0 || intValue == 1;
//        }
//        return false;
//    }
//
//    private Object convertToBit(Object value) {
//        if (value instanceof Boolean) return (Boolean) value ? 1 : 0;
//        if (value instanceof Number) return ((Number) value).intValue() == 0 ? 0 : 1;
//        return null;
//    }
//}
