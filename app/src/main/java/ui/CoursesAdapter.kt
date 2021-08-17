package ui

import Models.Course
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registration_form.R

class CoursesAdapter( var courselister:List<Course>):RecyclerView.Adapter<coursesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): coursesViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.activity_course_adapter,parent,false)
        return coursesViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: coursesViewHolder, position: Int) {
        var currentCourse=courselister.get(position)
        holder.tvcoursename.text=currentCourse.CourseName
        holder.tvcoursecode.text=currentCourse.CourseCode
        holder.tvdescription.text=currentCourse.Description
        holder.tvinstructor.text=currentCourse.Instructor
    }

    override fun getItemCount(): Int {
        return courselister.count()
    }
}
class coursesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvcoursename=itemView.findViewById<TextView>(R.id.tvCourseName)
    var tvcoursecode=itemView.findViewById<TextView>(R.id.tvCourseCode)
    var tvdescription=itemView.findViewById<TextView>(R.id.tvDescription)
    var tvinstructor=itemView.findViewById<TextView>(R.id.tvInstructor)
}