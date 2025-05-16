package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.uts.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : ComponentActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Tombol Sign Up: Validasi input & registrasi user ke Firebase
        binding.bSignUpregis.setOnClickListener {
            val name = binding.eTnameregis.text.toString().trim()
            val email = binding.eTemailregis.text.toString().trim()
            val password = binding.eTpwregis.text.toString().trim()
            val confirmPassword = binding.eTconfirmpwregis.text.toString().trim()

            // Validasi sederhana
            if (name.isEmpty()) {
                binding.eTnameregis.error = "Masukkan nama"
                binding.eTnameregis.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                binding.eTemailregis.error = "Masukkan email"
                binding.eTemailregis.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.eTemailregis.error = "Email tidak valid"
                binding.eTemailregis.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                binding.eTpwregis.error = "Password minimal 6 karakter"
                binding.eTpwregis.requestFocus()
                return@setOnClickListener
            }
            if (password != confirmPassword) {
                binding.eTconfirmpwregis.error = "Konfirmasi password tidak cocok"
                binding.eTconfirmpwregis.requestFocus()
                return@setOnClickListener
            }

            // Registrasi user ke Firebase
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registrasi sukses!", Toast.LENGTH_SHORT).show()
                        // Misalnya, arahkan ke LoginActivity setelah registrasi
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Navigasi ke LoginActivity jika sudah memiliki akun
        binding.tVSignInregis.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Navigasi kembali (tombol back)
        binding.iVBackregis.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Toggle tampilan password (EditText untuk password)
        binding.iVmatapwregis.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                binding.eTpwregis.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.iVmatapwregis.setImageResource(R.drawable.openeye)
            } else {
                binding.eTpwregis.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.iVmatapwregis.setImageResource(R.drawable.closeeye)
            }
            binding.eTpwregis.setSelection(binding.eTpwregis.text.length)
        }

        // Toggle tampilan untuk konfirmasi password
        binding.iVmatacpwregis.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                binding.eTconfirmpwregis.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.iVmatacpwregis.setImageResource(R.drawable.openeye)
            } else {
                binding.eTconfirmpwregis.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.iVmatacpwregis.setImageResource(R.drawable.closeeye)
            }
            binding.eTconfirmpwregis.setSelection(binding.eTconfirmpwregis.text.length)
        }
    }
}
