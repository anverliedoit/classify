package io.ermdev.classify.data.entity

import javax.persistence.*

@Entity
@Table(name = "tbl_lesson")
class Lesson(@Id
             @GeneratedValue(strategy = GenerationType.AUTO)
             var id: Long = 0,

             @ManyToOne(cascade = [CascadeType.PERSIST])
             @JoinColumn(name = "subject_id", nullable = false)
             var subject: Subject = Subject(),

             @ManyToOne(cascade = [CascadeType.PERSIST])
             @JoinColumn(name = "teacher_id", nullable = false)
             var teacher: Teacher = Teacher(),

             @ManyToMany(cascade = [CascadeType.ALL])
             @JoinTable(name = "tbl_student_lesson", joinColumns = [JoinColumn(name = "lesson_id")],
                     inverseJoinColumns = [(JoinColumn(name = "student_id"))])
             var students: MutableList<Student> = ArrayList())