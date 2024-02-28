package com.example.bolnica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.bolnica.databinding.ActivityMainBinding
import com.example.bolnica.databinding.PopisBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class pregled : AppCompatActivity(){
    lateinit var binding: PopisBinding
    private val database: DatabaseReference = FirebaseDatabase.getInstance("https://bonica-5eece-default-rtdb.firebaseio.com//").getReference("korisnici")
    private var tekstlist = ArrayList<tekst>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PopisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dodaj.setOnClickListener{
            val tempime_lijecnik=binding.unesime.text.toString()
            val tempprezime_lijecnik =binding.unesprezime.text.toString()
            val temptermin =binding.time.text.toString()
            val temppacijent =binding.pacijent.text.toString()
            var tempid=0
            if (!tekstlist.isEmpty()) tempid=tekstlist[tekstlist.size-1].id+1

            tekstlist.add(tekst(tempid,tempime_lijecnik,tempprezime_lijecnik,temptermin,temppacijent))

            database.setValue(tekstlist)

        }

        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                tekstlist.clear()
                try {
                    val a:List<tekst> = snapshot.children.map{dataSnapshot -> dataSnapshot.getValue(tekst::class.java)!!  }

                    tekstlist.addAll(a)

                }catch (e:Exception){}

                binding.lista.apply{
                    LayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = textAdapter (tekstlist, this@pregled)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}
