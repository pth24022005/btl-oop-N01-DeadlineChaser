package com.myproject.student.management.view;

/**
 * Interface này định nghĩa một hành động "làm mới dữ liệu".
 * Bất kỳ panel nào cần chức năng refresh sẽ implement interface này.
 */
public interface Refreshable {
    void refreshData();
}