package com.ute.ktra2_quanlycongviec

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class ChinhSuaCongViecActivity : AppCompatActivity() {

    private lateinit var edtTenCongViecSua: TextInputEditText
    private lateinit var edtMoTaCongViecSua: TextInputEditText
    private lateinit var btnLuu: Button

    private var congViecHienTai: CongViec = CongViec()

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

        // Nhan doi tuong CongViec cu
        val receivedCongViec = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_CONG_VIEC", CongViec::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_CONG_VIEC") as? CongViec
        }

        if (receivedCongViec != null) {
            congViecHienTai = receivedCongViec
        }

        edtTenCongViecSua.setText(congViecHienTai.tenCongViec)
        edtMoTaCongViecSua.setText(congViecHienTai.moTa)

        // Xu ly nut Luu va tra doi tuong CongViec qua Intent ket qua
        btnLuu.setOnClickListener {
            if (!kiemTraDuLieu()) return@setOnClickListener

            congViecHienTai.tenCongViec = edtTenCongViecSua.text.toString().trim()
            congViecHienTai.moTa = edtMoTaCongViecSua.text.toString().trim()

            val resultIntent = Intent().apply {
                putExtra("EXTRA_CONG_VIEC", congViecHienTai)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun kiemTraDuLieu(): Boolean {
        val tenMoi = edtTenCongViecSua.text?.toString()?.trim() ?: ""
        if (tenMoi.isEmpty()) {
            edtTenCongViecSua.error = "Tên công việc không được để trống!"
            edtTenCongViecSua.requestFocus()
            Toast.makeText(this, "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
