package kr.b4tt3ry.tunaeye

import com.google.gson.GsonBuilder
import kr.b4tt3ry.tunaeye.objects.Config
import kr.b4tt3ry.tunaeye.objects.toObject
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main: JavaPlugin() {
    companion object {
        private lateinit var instance: Main
        lateinit var config: Config
        @JvmStatic fun getInstance(): Main {
            return instance
        }
    }

    override fun onEnable() {
        instance = this
        val configFile = File(dataFolder, "config.json")
        if (configFile.exists()) {
            Main.config = configFile.readText().toObject()
        } else {
            File(dataFolder, "config.json").createNewFile()
            Main.config = Config()
        }
        configFile.writeText(Main.config.toPrettyJSON())
    }

}