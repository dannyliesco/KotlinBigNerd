sealed class StudentStatus{
    object NotEnrolled : StudentStatus()
    object GRADUATED : StudentStatus()
    class Active(val course_id : String) : StudentStatus()
}
