package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.uts.databinding.ActivityLoginBinding

class LoginActivity : ComponentActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigasi ke BottomNavActivity setelah login berhasil
        binding.bSignInlogin.setOnClickListener {
            val intentHome = Intent(this, BottomNavActivity::class.java)  // Perbaikan: Navigasi ke activity yang mengelola fragmen
            startActivity(intentHome)

            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
        }

        // Navigasi ke Forgot Password
        binding.tVforgotpwlogin.setOnClickListener {
            val intentForgotPw = Intent(this, ForgotpwActivity::class.java)
            startActivity(intentForgotPw)
        }

        // Navigasi ke Register
        binding.tVdonthavepwlogin.setOnClickListener {
            val intentRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentRegister)
        }

        // Navigasi kembali ke MainActivity
        binding.iVBacklogin.setOnClickListener {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }

        // Toggle Password Visibility
        binding.iVmatalogin.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                // Tampilkan password
                binding.eTpwlogin.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.iVmatalogin.setImageResource(R.drawable.openeye)  // Pastikan file ini ada di res/drawable
            } else {
                // Sembunyikan password
                binding.eTpwlogin.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.iVmatalogin.setImageResource(R.drawable.closeeye)  // Pastikan file ini ada di res/drawable
            }
            // Pindahkan kursor ke akhir teks setelah perubahan
            binding.eTpwlogin.setSelection(binding.eTpwlogin.text.length)
        }
    }
}
