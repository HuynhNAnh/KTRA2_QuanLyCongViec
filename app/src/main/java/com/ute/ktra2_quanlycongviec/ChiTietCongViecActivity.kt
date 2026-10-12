package com.ute.ktra2_quanlycongviec

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var txtTenCongViecChiTiet: TextView
    private lateinit var txtMoTaCongViecChiTiet: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chi_tiet_cong_viec)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtTenCongViecChiTiet = findViewById(R.id.txtTenCongViecChiTiet)
        txtMoTaCongViecChiTiet = findViewById(R.id.txtMoTaCongViecChiTiet)

        // Nhan du lieu tu Intent va hien thi
        val tenCongViec = intent.getStringExtra("EXTRA_TEN_CONG_VIEC") ?: ""
        val moTa = intent.getStringExtra("EXTRA_MO_TA") ?: ""

        txtTenCongViecChiTiet.text = tenCongViec
        txtMoTaCongViecChiTiet.text = moTa
    }
}
