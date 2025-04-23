package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts.databinding.FragmentRcviewBinding

class RcviewFragment : Fragment() {

    private var _binding: FragmentRcviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var pemainbolaAdapter: MyAdapterActivity
    private lateinit var listpemainbola: ArrayList<ItemDataActivity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRcviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi data pemain
        listpemainbola = ArrayList()
        listpemainbola.add(
            ItemDataActivity(
                R.drawable.cr7,
                "CRISTIANO RONALDO",
                "PORTUGAL",
                "Cristiano Ronaldo adalah salah satu pemain sepak bola terbaik dengan kemampuan luar biasa dalam mencetak gol."
            )
        )
        listpemainbola.add(
            ItemDataActivity(
                R.drawable.lmessi,
                "LIONEL MESSI",
                "ARGENTINA",
                "Lionel Messi dikenal dengan dribbling mempesona dan kreativitas dalam mengatur permainan."
            )
        )
        listpemainbola.add(
            ItemDataActivity(
                R.drawable.neymarjr,
                "NEYMAR JUNIOR",
                "BRAZIL",
                "Neymar Junior memiliki keterampilan teknis tinggi yang membuatnya menonjol di lapangan."
            )
        )
        listpemainbola.add(
            ItemDataActivity(
                R.drawable.mbappe,
                "KYLIAN MBAPPE",
                "FRANCE",
                "Kylian Mbappe dikenal dengan kecepatan dan ketajamannya dalam mencetak gol sebagai penyerang muda."
            )
        )
        listpemainbola.add(
            ItemDataActivity(
                R.drawable.vinijr,
                "VINICIUS JUNIOR",
                "BRAZIL",
                "Vinicius Junior menonjol dengan akselerasi dan kemampuan dribbling yang luar biasa."
            )
        )
        listpemainbola.add(
            ItemDataActivity(
                R.drawable.rodrygo,
                "RODRYGO",
                "BRAZIL",
                "Rodrygo menunjukkan potensi besar sebagai pemain serba bisa di lini serang."
            )
        )
        listpemainbola.add(
            ItemDataActivity(
                R.drawable.jude,
                "JUDE BELLINGHAM",
                "ENGLAND",
                "Jude Bellingham menjadi pilihan andalan berkat visi dan kekuatan fisiknya di tengah lapangan."
            )
        )
        listpemainbola.add(
            ItemDataActivity(
                R.drawable.modric,
                "LUKA MODRIC",
                "CROATIA",
                "Luka Modric dikenal dengan pengendalian bola dan kemampuan mengatur ritme permainan yang brilian."
            )
        )
        listpemainbola.add(
            ItemDataActivity(
                R.drawable.sramos,
                "SERGIO RAMOS",
                "SPAIN",
                "Sergio Ramos merupakan pemain bertahan berpengalaman yang juga mampu mencetak gol penting."
            )
        )

        // Setup adapter RecyclerView
        pemainbolaAdapter = MyAdapterActivity(listpemainbola)
        binding.PemainbolaRV.layoutManager = LinearLayoutManager(requireContext())
        binding.PemainbolaRV.setHasFixedSize(true)
        binding.PemainbolaRV.adapter = pemainbolaAdapter

        // Menangani klik item untuk membuka DetailActivity
        pemainbolaAdapter.onItemClick = { item ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("itemDetail", item)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Mencegah memory leaks
    }
}
