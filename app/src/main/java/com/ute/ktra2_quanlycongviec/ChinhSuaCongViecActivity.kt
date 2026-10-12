package com.ute.ktra2_quanlycongviec

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class ChinhSuaCongViecActivity : AppCompatActivity() {

    private lateinit var edtTenCongViecSua: TextInputEditText
    private lateinit var edtMoTaCongViecSua: TextInputEditText
    private lateinit var btnLuu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chinh_sua_cong_viec)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtTenCongViecSua = findViewById(R.id.edtTenCongViecSua)
        edtMoTaCongViecSua = findViewById(R.id.edtMoTaCongViecSua)
        btnLuu = findViewById(R.id.btnLuu)

        // Nhan du lieu cu hien thi vao cac o nhap
        val tenHienTai = intent.getStringExtra("EXTRA_TEN_CONG_VIEC") ?: ""
        val moTaHienTai = intent.getStringExtra("EXTRA_MO_TA") ?: ""

        edtTenCongViecSua.setText(tenHienTai)
        edtMoTaCongViecSua.setText(moTaHienTai)

        // Xu ly nut Luu va tra ket qua ve man hinh chi tiet
        btnLuu.setOnClickListener {
            val tenMoi = edtTenCongViecSua.text.toString()
            val moTaMoi = edtMoTaCongViecSua.text.toString()

            val resultIntent = Intent().apply {
                putExtra("EXTRA_TEN_CONG_VIEC", tenMoi)
                putExtra("EXTRA_MO_TA", moTaMoi)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
