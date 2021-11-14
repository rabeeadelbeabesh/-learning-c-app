package com.example.room

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import org.w3c.dom.Text

@RealmClass
open class DataBase :RealmObject(){
    @PrimaryKey
    var id:Int=0
    var index_name:String?= null
    var image_url:String?=null
    var lesson:String?=null
}