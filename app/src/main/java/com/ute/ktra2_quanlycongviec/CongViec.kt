package com.ute.ktra2_quanlycongviec

import java.io.Serializable

class CongViec(
    var maCongViec: String = "CV01",
    var tenCongViec: String = "",
    var moTa: String = "",
    var trangThai: String = "Chưa hoàn thành"
) : Serializable {

    fun laDaHoanThanh(): Boolean {
        return trangThai.equals("Đã hoàn thành", ignoreCase = true)
    }

    fun layThongTinCongViec(): String {
        return "Mã công việc: $maCongViec\nTên công việc: $tenCongViec\nMô tả: $moTa\nTrạng thái: $trangThai"
    }
}
