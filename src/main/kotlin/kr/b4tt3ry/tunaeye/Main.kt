package kr.b4tt3ry.tunaeye

import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {
    companion object {
        private lateinit var instance: Main
        @JvmStatic fun getInstance(): Main {
            return instance
        }
    }

    override fun onEnable() {
        instance = this
    }

}