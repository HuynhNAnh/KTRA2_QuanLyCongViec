package com.ute.ktra2_quanlycongviec

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CongViecUnitTest {

    @Test
    fun testKhoiTaoCongViecMacDinh() {
        val congViec = CongViec(tenCongViec = "Làm bài kiểm tra Android", moTa = "Kiểm tra 2 môn Lập trình Android")
        assertEquals("Làm bài kiểm tra Android", congViec.tenCongViec)
        assertEquals("Kiểm tra 2 môn Lập trình Android", congViec.moTa)
        assertEquals("Chưa hoàn thành", congViec.trangThai)
    }

    @Test
    fun testLayThongTinCongViec() {
        val congViec = CongViec(
            tenCongViec = "Học Kotlin",
            moTa = "Ôn tập Intent và Activity Result API",
            trangThai = "Chưa hoàn thành"
        )
        val thongTin = congViec.layThongTinCongViec()
        assertTrue(thongTin.contains("Học Kotlin"))
        assertTrue(thongTin.contains("Ôn tập Intent và Activity Result API"))
        assertTrue(thongTin.contains("Chưa hoàn thành"))
    }

    @Test
    fun testCapNhatTrangThaiHoanThanh() {
        val congViec = CongViec(tenCongViec = "Nộp bài kiểm tra", moTa = "Nộp link GitHub")
        congViec.trangThai = "Đã hoàn thành"
        assertEquals("Đã hoàn thành", congViec.trangThai)
        assertTrue(congViec.layThongTinCongViec().contains("Đã hoàn thành"))
    }

    @Test
    fun testKiemTraDuLieuTenCongViec() {
        val tenRong = "   "
        val tenHopLe = "Học bài"
        assertTrue(tenRong.trim().isEmpty())
        assertFalse(tenHopLe.trim().isEmpty())
    }
}
