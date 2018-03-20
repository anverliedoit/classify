package io.ermdev.classify.data.entity

import javax.persistence.*

@Entity
@Table(name = "tbl_subject")
class Subject(@Id
              @GeneratedValue(strategy = GenerationType.AUTO)
              var id: Long = 0,

              var name: String = "",

              @OneToMany(mappedBy = "subject", cascade = [CascadeType.REMOVE])
              var lessons: Set<Lesson> = HashSet())