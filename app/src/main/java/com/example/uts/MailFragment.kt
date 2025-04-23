package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.uts.databinding.FragmentMailBinding

class MailFragment : Fragment() {

    private var _binding: FragmentMailBinding? = null
    private val binding get() = _binding!!

    // Flag untuk menandai apakah pengguna kembali dari intent email
    private var isReturningFromEmail = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // Tombol kirim email
        binding.bsendemailmenu.setOnClickListener {
            val email = binding.eTemailmenu.text.toString()
            val subject = binding.eTsubjectmenu.text.toString()
            val message = binding.eTmessagemenu.text.toString()

            if (email.isNotEmpty() && subject.isNotEmpty() && message.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "message/rfc822"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, message)
                }

                try {
                    startActivity(Intent.createChooser(intent, "Pilih aplikasi email"))
                    isReturningFromEmail = true
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Tidak ada aplikasi email yang tersedia", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Metode ini dipanggil setiap kali fragment kembali aktif dari intent
    override fun onResume() {
        super.onResume()
        if (isReturningFromEmail) {
            Toast.makeText(requireContext(), "Email berhasil dikirim", Toast.LENGTH_SHORT).show()
            isReturningFromEmail = false

            // Mengosongkan EditText setelah kembali ke fragment
            binding.eTemailmenu.setText("")
            binding.eTsubjectmenu.setText("")
            binding.eTmessagemenu.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
