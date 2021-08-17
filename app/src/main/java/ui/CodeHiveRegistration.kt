package ui

import Models.Course
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registration_form.R

class CodeHiveRegistration : AppCompatActivity() {
    lateinit var rvCourses:RecyclerView
    lateinit var btnLogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_hive_registration)
        displayCourses()
    }



    fun displayCourses(){
        var courseList= listOf(
            Course("iot","10t1","smart internet activities","Sir Barre"),
            Course("Android Development","kt601","Kotlin language","John Owuor"),
            Course("Front end web Development","web101","html/css/javascript languages","Purity Maina"),
            Course("Back end Development","py678","Python language","James Mwai")
        )

        rvCourses=findViewById(R.id.rvCourses)
        var CoursesAdapter= CoursesAdapter(courseList)
        rvCourses.apply {
            layoutManager= LinearLayoutManager(baseContext)
            rvCourses.adapter=CoursesAdapter
        }
    }}


