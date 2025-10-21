package com.myproject.student.management.service;

import com.myproject.student.management.dao.RelativeDAO;
import com.myproject.student.management.model.Relative;
import java.sql.SQLException;
import java.util.List;

public class RelativeService {
    private RelativeDAO relativeDAO;

    public RelativeService() {
        this.relativeDAO = new RelativeDAO();
    }

    public List<Relative> getAllRelatives() {
        return relativeDAO.getAllRelatives();
    }
    
    // --- CÁC PHƯƠNG THỨC ĐÃ ĐƯỢC BỔ SUNG ---

    /**
     * Tìm một nhân thân dựa vào ID.
     * @param relativeId ID của nhân thân cần tìm.
     * @return đối tượng Relative nếu tìm thấy.
     */
    public Relative findRelativeById(int relativeId) {
        return relativeDAO.findRelativeById(relativeId);
    }

    /**
     * Thêm một nhân thân mới.
     * @param relative Đối tượng Relative chứa thông tin cần thêm.
     * @throws SQLException
     */
    public void addRelative(Relative relative) throws SQLException {
        // Bạn có thể thêm các logic kiểm tra dữ liệu ở đây trước khi gọi DAO
        // Ví dụ: if (relative.getFullName().isEmpty()) { ... }
        relativeDAO.addRelative(relative);
    }

    /**
     * Cập nhật thông tin một nhân thân.
     * @param relative Đối tượng Relative chứa thông tin cần cập nhật.
     * @throws SQLException
     */
    public void updateRelative(Relative relative) throws SQLException {
        relativeDAO.updateRelative(relative);
    }

    /**
     * Xóa một nhân thân dựa vào ID.
     * @param relativeId ID của nhân thân cần xóa.
     * @throws SQLException
     */
    public void deleteRelative(int relativeId) throws SQLException {
        relativeDAO.deleteRelative(relativeId);
    }
}