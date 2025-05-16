package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uts.databinding.ActivityMainBinding
import com.example.uts.databinding.ActivityRegisterBinding
import com.example.uts.ui.theme.UTSTheme

class RegisterActivity : ComponentActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var isPasswordVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bSignUpregis.setOnClickListener {
            val intentHome = Intent(this, LoginActivity::class.java)
            startActivity(intentHome)

            Toast.makeText(this, "Registrasi sukses!", Toast.LENGTH_SHORT).show()
        }

        binding.tVSignInregis.setOnClickListener {
            val intentHome = Intent(this, LoginActivity::class.java)
            startActivity(intentHome)
        }

        binding.iVBackregis.setOnClickListener {
            val intentHome = Intent(this, LoginActivity::class.java)
            startActivity(intentHome)
        }

        // Toggle Password Visibility
        binding.iVmatapwregis.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                // Tampilkan password
                binding.eTpwregis.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.iVmatapwregis.setImageResource(R.drawable.openeye) // Pastikan file `openeye.png` ada di `res/drawable`
            } else {
                // Sembunyikan password
                binding.eTpwregis.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.iVmatapwregis.setImageResource(R.drawable.closeeye) // Pastikan file `closeeye.png` ada di `res/drawable`
            }
            // Pindahkan kursor ke akhir teks setelah perubahan
            binding.eTpwregis.setSelection(binding.eTpwregis.text.length)
        }

        // Toggle Confirm Password Visibility
        binding.iVmatacpwregis.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                // Tampilkan password
                binding.eTconfirmpwregis.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.iVmatacpwregis.setImageResource(R.drawable.openeye) // Pastikan file `openeye.png` ada di `res/drawable`
            } else {
                // Sembunyikan password
                binding.eTconfirmpwregis.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.iVmatacpwregis.setImageResource(R.drawable.closeeye) // Pastikan file `closeeye.png` ada di `res/drawable`
            }
            // Pindahkan kursor ke akhir teks setelah perubahan
            binding.eTconfirmpwregis.setSelection(binding.eTconfirmpwregis.text.length)
        }


    }
}

