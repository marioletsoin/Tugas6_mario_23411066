package com.example.uts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uts.databinding.ActivityBottomnavBinding

class BottomNavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomnavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomnavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atur toolbar
        setSupportActionBar(binding.idToolbar)

        // Agar warna asli ikon digunakan (tidak ditint)
        binding.bottomNavigation.itemIconTintList = null

        // Set tampilan awal ke RcviewFragment saat activity pertama kali dibuat
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, RcviewFragment())
                .commit()
        }

        // Mengatur navigasi di BottomNavigationView
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            val selectedFragment = when (menuItem.itemId) {
                R.id.nav_home -> RcviewFragment()
                R.id.nav_mail -> MailFragment()
                R.id.nav_profil -> ProfileFragment()
                else -> null
            }

            // Jika ada fragmen yang dipilih, ganti fragmen
            selectedFragment?.let { fragment ->
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainer.id, fragment)
                    .commit()
            }
            true
        }
    }
}
