package com.robert.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.robert.studentapp.databinding.FilmListItemBinding
import com.robert.studentapp.databinding.FragmentStudentListBinding
import com.robert.studentapp.databinding.StudentListItemBinding
import com.robert.studentapp.model.Film
import com.robert.studentapp.model.Student

class FilmListAdapter(val filmList:ArrayList<Film>)
    : RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>() {
    class FilmViewHolder(var binding: FilmListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        var binding = FilmListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return FilmViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.binding.txtTitle.text = filmList[position].title
        holder.binding.txtGenre.text = filmList[position].genre.joinToString(separator = ", ")
        holder.binding.btnDetail.setOnClickListener {

        }




    }

    fun updateFilmList(newFilmList: ArrayList<Film>){
        filmList.clear()
        filmList.addAll(newFilmList)
        notifyDataSetChanged()

        // Log to check if the film list is updated
        Log.d("FilmListAdapter", "Film list updated: $filmList")
    }
}