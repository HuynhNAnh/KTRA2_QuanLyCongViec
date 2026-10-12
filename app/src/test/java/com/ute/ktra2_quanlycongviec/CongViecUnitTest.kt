package com.ute.ktra2_quanlycongviec

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CongViecUnitTest {

    @Test
    fun testKhoiTaoCongViecMacDinh() {
        val congViec = CongViec(
            maCongViec = "CV01",
            tenCongViec = "Làm bài kiểm tra Android",
            moTa = "Kiểm tra 2 môn Lập trình Android"
        )
        assertEquals("CV01", congViec.maCongViec)
        assertEquals("Làm bài kiểm tra Android", congViec.tenCongViec)
        assertEquals("Kiểm tra 2 môn Lập trình Android", congViec.moTa)
        assertEquals("Chưa hoàn thành", congViec.trangThai)
        assertFalse(congViec.laDaHoanThanh())
    }

    @Test
    fun testLayThongTinCongViec() {
        val congViec = CongViec(
            maCongViec = "CV02",
            tenCongViec = "Học Kotlin",
            moTa = "Ôn tập Intent và Activity Result API",
            trangThai = "Chưa hoàn thành"
        )
        val thongTin = congViec.layThongTinCongViec()
        assertTrue(thongTin.contains("CV02"))
        assertTrue(thongTin.contains("Học Kotlin"))
        assertTrue(thongTin.contains("Ôn tập Intent và Activity Result API"))
        assertTrue(thongTin.contains("Chưa hoàn thành"))
    }

    @Test
    fun testHamLaDaHoanThanh() {
        val congViec = CongViec(maCongViec = "CV03", tenCongViec = "Nộp bài kiểm tra", moTa = "Nộp link GitHub")
        assertFalse(congViec.laDaHoanThanh())

        congViec.trangThai = "Đã hoàn thành"
        assertTrue(congViec.laDaHoanThanh())
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
