package kr.b4tt3ry.tunaeye.objects

import com.google.gson.Gson
import com.google.gson.GsonBuilder

interface JSONConvertable {
    fun toJSON(): String = Gson().toJson(this)
    fun toPrettyJSON(): String = GsonBuilder().setPrettyPrinting().create().toJson(this)
}

inline fun <reified T: JSONConvertable> String.toObject(): T = Gson().fromJson(this, T::class.java)