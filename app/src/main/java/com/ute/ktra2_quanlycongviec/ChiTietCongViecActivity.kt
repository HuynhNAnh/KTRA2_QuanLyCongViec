package com.ute.ktra2_quanlycongviec

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var txtTenCongViecChiTiet: TextView
    private lateinit var txtMoTaCongViecChiTiet: TextView
    private lateinit var btnChinhSua: Button

    private lateinit var chinhSuaLauncher: ActivityResultLauncher<Intent>

    private var tenHienTai: String = ""
    private var moTaHienTai: String = ""

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
        btnChinhSua = findViewById(R.id.btnChinhSua)

        // Nhan du lieu tu Intent va hien thi
        tenHienTai = intent.getStringExtra("EXTRA_TEN_CONG_VIEC") ?: ""
        moTaHienTai = intent.getStringExtra("EXTRA_MO_TA") ?: ""

        txtTenCongViecChiTiet.text = tenHienTai
        txtMoTaCongViecChiTiet.text = moTaHienTai

        // Dang ky Activity Result API va nhan ket qua tra ve
        chinhSuaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val tenMoi = data?.getStringExtra("EXTRA_TEN_CONG_VIEC")
                val moTaMoi = data?.getStringExtra("EXTRA_MO_TA")

                if (tenMoi != null && moTaMoi != null) {
                    capNhatThongTin(tenMoi, moTaMoi)
                }
            }
        }

        btnChinhSua.setOnClickListener {
            chuyenSangChinhSua()
        }
    }

    private fun chuyenSangChinhSua() {
        val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
            putExtra("EXTRA_TEN_CONG_VIEC", tenHienTai)
            putExtra("EXTRA_MO_TA", moTaHienTai)
        }
        chinhSuaLauncher.launch(intent)
    }

    private fun capNhatThongTin(tenMoi: String, moTaMoi: String) {
        tenHienTai = tenMoi
        moTaHienTai = moTaMoi
        txtTenCongViecChiTiet.text = tenHienTai
        txtMoTaCongViecChiTiet.text = moTaHienTai
    }
}
