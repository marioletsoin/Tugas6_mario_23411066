package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.uts.databinding.ActivityForgotpwBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotpwActivity : ComponentActivity() {
    private lateinit var binding: ActivityForgotpwBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForgotpwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Tombol Back akan mengarahkan pengguna kembali ke LoginActivity
        binding.iVBackforgotpw.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Tombol Reset: Validasi input dan kirim link reset password melalui FirebaseAuth
        binding.breset.setOnClickListener {
            val email = binding.textView3.text.toString().trim() // textView3 merupakan EditText untuk email
            if (email.isEmpty()) {
                binding.textView3.error = "Masukkan email"
                binding.textView3.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.textView3.error = "Email tidak valid"
                binding.textView3.requestFocus()
                return@setOnClickListener
            }

            firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Cek email untuk reset password", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, task.exception?.message ?: "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
