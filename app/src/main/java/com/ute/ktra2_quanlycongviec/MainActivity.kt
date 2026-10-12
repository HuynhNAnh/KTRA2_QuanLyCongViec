package com.ute.ktra2_quanlycongviec

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var txtThongTinSinhVien: TextView
    private lateinit var edtTenCongViec: TextInputEditText
    private lateinit var edtMoTaCongViec: TextInputEditText
    private lateinit var btnTaoCongViec: Button
    private lateinit var btnXemDanhSach: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtThongTinSinhVien = findViewById(R.id.txtThongTinSinhVien)
        edtTenCongViec = findViewById(R.id.edtTenCongViec)
        edtMoTaCongViec = findViewById(R.id.edtMoTaCongViec)
        btnTaoCongViec = findViewById(R.id.btnTaoCongViec)
        btnXemDanhSach = findViewById(R.id.btnXemDanhSach)

        // Hien thi danh sach mau qua Toast khi nhan btnXemDanhSach
        btnXemDanhSach.setOnClickListener {
            Toast.makeText(
                this,
                "Danh sách công việc mẫu:\n1. Ôn tập Android\n2. Hoàn thiện bài KTRA2",
                Toast.LENGTH_LONG
            ).show()
        }

        // Xu ly nut Tao cong viec va chuyen sang man hinh chi tiet
        btnTaoCongViec.setOnClickListener {
            chuyenSangChiTiet()
        }
    }

    private fun chuyenSangChiTiet() {
        val tenCongViec = edtTenCongViec.text.toString()
        val moTa = edtMoTaCongViec.text.toString()

        val intent = Intent(this, ChiTietCongViecActivity::class.java).apply {
            putExtra("EXTRA_TEN_CONG_VIEC", tenCongViec)
            putExtra("EXTRA_MO_TA", moTa)
        }
        startActivity(intent)
    }
}