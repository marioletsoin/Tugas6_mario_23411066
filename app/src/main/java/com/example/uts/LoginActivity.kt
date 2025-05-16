package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.uts.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : ComponentActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Ketika tombol Sign In diklik, lakukan proses login dengan FirebaseAuth
        binding.bSignInlogin.setOnClickListener {
            val email = binding.eTemaillogin.text.toString().trim()
            val password = binding.eTpwlogin.text.toString().trim()

            if (email.isEmpty()) {
                binding.eTemaillogin.error = "Masukkan email"
                binding.eTemaillogin.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.eTemaillogin.error = "Email tidak valid"
                binding.eTemaillogin.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                binding.eTpwlogin.error = "Password minimal 6 karakter"
                binding.eTpwlogin.requestFocus()
                return@setOnClickListener
            }

            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                        // Arahkan ke BottomNavActivity (misalnya, aktivitas utama)
                        startActivity(Intent(this, BottomNavActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Navigasi ke Forgot Password
        binding.tVforgotpwlogin.setOnClickListener {
            startActivity(Intent(this, ForgotpwActivity::class.java))
        }

        // Navigasi ke RegisterActivity jika belum memiliki akun
        binding.tVdonthavepwlogin.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Navigasi kembali ke MainActivity
        binding.iVBacklogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Toggle tampilan password
        binding.iVmatalogin.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                binding.eTpwlogin.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.iVmatalogin.setImageResource(R.drawable.openeye)
            } else {
                binding.eTpwlogin.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.iVmatalogin.setImageResource(R.drawable.closeeye)
            }
            binding.eTpwlogin.setSelection(binding.eTpwlogin.text.length)
        }
    }
}
