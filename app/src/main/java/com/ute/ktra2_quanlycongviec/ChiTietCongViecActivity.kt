package com.ute.ktra2_quanlycongviec

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.checkbox.MaterialCheckBox

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var txtMaCongViecChiTiet: TextView
    private lateinit var txtTenCongViecChiTiet: TextView
    private lateinit var txtMoTaCongViecChiTiet: TextView
    private lateinit var txtTrangThaiChiTiet: TextView
    private lateinit var txtThongTinTomTat: TextView
    private lateinit var cbHoanThanh: MaterialCheckBox
    private lateinit var btnHoanThanh: Button
    private lateinit var btnChinhSua: Button

    private lateinit var chinhSuaLauncher: ActivityResultLauncher<Intent>

    private var congViecHienTai: CongViec = CongViec()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chi_tiet_cong_viec)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtMaCongViecChiTiet = findViewById(R.id.txtMaCongViecChiTiet)
        txtTenCongViecChiTiet = findViewById(R.id.txtTenCongViecChiTiet)
        txtMoTaCongViecChiTiet = findViewById(R.id.txtMoTaCongViecChiTiet)
        txtTrangThaiChiTiet = findViewById(R.id.txtTrangThaiChiTiet)
        txtThongTinTomTat = findViewById(R.id.txtThongTinTomTat)
        cbHoanThanh = findViewById(R.id.cbHoanThanh)
        btnHoanThanh = findViewById(R.id.btnHoanThanh)
        btnChinhSua = findViewById(R.id.btnChinhSua)

        // Nhan doi tuong CongViec tu Intent
        val receivedCongViec = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_CONG_VIEC", CongViec::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_CONG_VIEC") as? CongViec
        }

        if (receivedCongViec != null) {
            congViecHienTai = receivedCongViec
        }
        capNhatThongTin()

        // Dang ky Activity Result API nhan doi tuong CongViec da sua
        chinhSuaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val cvMoi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getSerializableExtra("EXTRA_CONG_VIEC", CongViec::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    result.data?.getSerializableExtra("EXTRA_CONG_VIEC") as? CongViec
                }

                if (cvMoi != null) {
                    congViecHienTai = cvMoi
                    capNhatThongTin()
                }
            }
        }

        // Xu ly nut Hoan thanh - cap nhat trang thai cong viec thanh da hoan thanh
        btnHoanThanh.setOnClickListener {
            congViecHienTai.trangThai = "Đã hoàn thành"
            capNhatThongTin()
            Toast.makeText(this, "Công việc đã được đánh dấu hoàn thành!", Toast.LENGTH_SHORT).show()
        }

        // Xu ly su kien checkbox danh dau hoan thanh
        cbHoanThanh.setOnCheckedChangeListener { _, isChecked ->
            congViecHienTai.trangThai = if (isChecked) "Đã hoàn thành" else "Chưa hoàn thành"
            capNhatTrangThaiUI()
            txtThongTinTomTat.text = congViecHienTai.layThongTinCongViec()
        }

        btnChinhSua.setOnClickListener {
            chuyenSangChinhSua()
        }
    }

    private fun chuyenSangChinhSua() {
        val intent = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
            putExtra("EXTRA_CONG_VIEC", congViecHienTai)
        }
        chinhSuaLauncher.launch(intent)
    }

    private fun capNhatThongTin() {
        txtMaCongViecChiTiet.text = congViecHienTai.maCongViec
        txtTenCongViecChiTiet.text = congViecHienTai.tenCongViec
        txtMoTaCongViecChiTiet.text = congViecHienTai.moTa
        txtThongTinTomTat.text = congViecHienTai.layThongTinCongViec()

        cbHoanThanh.isChecked = congViecHienTai.laDaHoanThanh()
        capNhatTrangThaiUI()
    }

    private fun capNhatTrangThaiUI() {
        // Hien thi trang thai dua tren ket qua cua ham laDaHoanThanh()
        if (congViecHienTai.laDaHoanThanh()) {
            txtTrangThaiChiTiet.text = "Đã hoàn thành"
            txtTrangThaiChiTiet.setTextColor(Color.parseColor("#15803D"))
            txtTrangThaiChiTiet.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DCFCE7"))
        } else {
            txtTrangThaiChiTiet.text = "Chưa hoàn thành"
            txtTrangThaiChiTiet.setTextColor(Color.parseColor("#B45309"))
            txtTrangThaiChiTiet.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FEF3C7"))
        }
    }
}
