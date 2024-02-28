package com.example.bolnica

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bolnica.databinding.TekstItemBinding

class textAdapter (
    private val tekstlista: ArrayList<tekst>,
    private val th: Context
    ): RecyclerView.Adapter<textAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): textAdapter.ViewHolder {
            val v= TekstItemBinding.inflate(LayoutInflater.from(th),parent,false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: textAdapter.ViewHolder, position: Int) {
            holder.bindItem(tekstlista[position],th)
        }

        override fun getItemCount(): Int {
            return tekstlista.size
        }

        class ViewHolder(private var itemBinding: TekstItemBinding):
            RecyclerView.ViewHolder(itemBinding.root){
            fun bindItem(tekst:tekst,th: Context){
                itemBinding.id.text=tekst.id.toString()
                itemBinding.ime.text=tekst.ime_lijecnik
                itemBinding.prezime.text=tekst.prezime_lijecnik
                itemBinding.vrijeme.text=tekst.termin
                itemBinding.pacjent.text=tekst.pacijent

            }
        }


    }