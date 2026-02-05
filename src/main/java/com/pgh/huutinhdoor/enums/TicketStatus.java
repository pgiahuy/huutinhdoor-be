package com.pgh.huutinhdoor.enums;

public enum TicketStatus {
    PENDING,    // Mới tiếp nhận thông tin khách
    SURVEYING,  // Đang đi đo đạc thực tế
    PROCESSING, // Đang gia công tại xưởng (cắt nhôm, tôi kính)
    SHIPPING,   // Đang vận chuyển ra công trình
    COMPLETED,  // Đã lắp đặt xong và bàn giao
    CANCELLED   // Khách hủy đơn
}
