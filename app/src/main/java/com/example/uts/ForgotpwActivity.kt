package com.example.uts

import android.content.Intent
import android.os.Bundle
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
import com.example.uts.databinding.ActivityForgotpwBinding
import com.example.uts.databinding.ActivityLoginBinding
import com.example.uts.ui.theme.UTSTheme

class ForgotpwActivity : ComponentActivity() {
    private lateinit var binding: ActivityForgotpwBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgotpw)

        binding = ActivityForgotpwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iVBackforgotpw.setOnClickListener {
            val intentHome = Intent(this, LoginActivity::class.java)
            startActivity(intentHome)
        }

        binding.breset.setOnClickListener {
            val intentHome = Intent(this, LoginActivity::class.java)
            startActivity(intentHome)

            Toast.makeText(this, "Link untuk Reset Password telah dikirim", Toast.LENGTH_SHORT).show()
        }
    }
}

