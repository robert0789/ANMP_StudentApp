package com.robert.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.robert.studentapp.databinding.FragmentStudentListBinding
import com.robert.studentapp.databinding.StudentListItemBinding
import com.robert.studentapp.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class StudentListAdapter(val studentList:ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(var binding: StudentListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        var binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.txtID.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name
        holder.binding.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail(holder.binding.txtID.text.toString())
            Navigation.findNavController(holder.itemView).navigate(action)
        }



        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener{
            picasso,uri, exception->
            exception.printStackTrace()
        }

        //callback untuk ngetahui image berhasil diload
        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imageView, object:Callback{
            override fun onSuccess() {
                holder.binding.progress.visibility = View.INVISIBLE
                holder.binding.imageView.visibility = View.VISIBLE

            }

            override fun onError(e: Exception?) {
                Log.e("picasso error", e.toString())
            }
        })




    }

    fun updateStudentList(newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}