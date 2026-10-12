package com.ute.ktra2_quanlycongviec

import java.io.Serializable

class CongViec(
    var tenCongViec: String = "",
    var moTa: String = "",
    var trangThai: String = "Chưa hoàn thành"
) : Serializable {

    fun layThongTinCongViec(): String {
        return "Tên công việc: $tenCongViec\nMô tả: $moTa\nTrạng thái: $trangThai"
    }
}
