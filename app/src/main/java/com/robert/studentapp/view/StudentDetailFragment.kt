package com.robert.studentapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.robert.studentapp.R
import com.robert.studentapp.databinding.FragmentStudentDetailBinding
import com.robert.studentapp.model.Student
import com.robert.studentapp.viewmodel.DetailViewModel
import com.robert.studentapp.viewmodel.ListViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StudentDetailFragment : Fragment(), ButtonUpdateClickListener {

    //jangan sampai salah tulis binding, nanti salah inflate layout
    private lateinit var binding: FragmentStudentDetailBinding

    private lateinit var viewModel:DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.student  = Student("","","","","https://cdn1-production-images-kly.akamaized.net/KjXPJSJZDztKc0jg1oeJkT7k3Rk=/800x450/smart/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/67198/original/telur-130421b.jpg")
        val studentId = arguments?.getString("studentID")
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if(studentId != null){
            viewModel.fetch(studentId)
            Log.d("student_id", studentId.toString())
            observeViewModel()
        }





    }

    fun observeViewModel(){


        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//            var student = it
            binding.student = it
            binding.listener = this

//            binding.txtID.setText(it.id)
//            binding.txtName.setText(it.name)
//            binding.txtBrd.setText(it.dob)
//            binding.txtPhone.setText(it.phone)
//
//            val picasso = Picasso.Builder(requireContext())
//            picasso.listener{
//                    picasso,uri, exception->
//                exception.printStackTrace()
//            }
//
//            //callback untuk ngetahui image berhasil diload
//            picasso.build().load(it.photoUrl).into(binding.imageView2, object:
//                Callback {
//                override fun onSuccess() {
//
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.e("picasso error", e.toString())
//                }
//            })


//            binding.btnUpdate?.setOnClickListener {
//                Observable.timer(1, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_person_24)
//                    }
//                val name = it.id.toString()
//                binding.txtID.isEnabled = true
//
//                Log.d("print_student", it.toString())
//                binding.txtID.setText(student.id.toString())
//                binding.txtName.setText(student.name)
//                binding.txtBrd.setText(student.dob)
//
//
//            }
        })


    }

    override fun onButtonUpdateClickListener(v: View) {
        Observable.timer(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(binding.student?.name.toString(),
                            "A new notification created",
                            R.drawable.baseline_person_24)
                    }
//        val name = binding.student.id.toString()
////        binding.txtID.isEnabled = true
//
//        Log.d("print_student", binding.student.toString())
//        binding.txtID.setText(student.id.toString())
//        binding.txtName.setText(student.name)
//        binding.txtBrd.setText(student.dob)
//
    }
}