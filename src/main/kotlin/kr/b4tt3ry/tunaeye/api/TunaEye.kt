package kr.b4tt3ry.tunaeye.api

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class TunaEye {
    companion object {
        @JvmStatic fun send(level: Level, tag: String, message: String, destinations: Array<Destination>): Deferred<Boolean> {
            val result = CoroutineScope(Dispatchers.Default).async {
                val jsonArray = JsonArray()
                for (destination in destinations) {
                    val jsonObject = JsonObject()
                    jsonObject.addProperty("type", destination.type.toString())
                    jsonObject.addProperty("target", destination.target)
                    jsonArray.add(jsonObject)
                }
                val json = JsonObject()
                json.addProperty("tag", tag)
                json.addProperty("timestamp", System.currentTimeMillis() / 1000)
                json.addProperty("type", level.toString())
                json.addProperty("message", message)
                json.add("destinations", jsonArray)
                //val url = URL("${Main.config.url}/${Main.config.secretKey}/request")
                val url = URL("http://139.180.199.212:3000/AAAA/request")
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.doInput = true
                conn.doOutput = true
                conn.setRequestProperty("Content-Type", "application/json")
                conn.setRequestProperty("Content-Length", json.toString().length.toString())
                val dos = DataOutputStream(conn.outputStream)
                dos.write(json.toString().toByteArray(Charset.forName("UTF-8")))
                dos.flush()
                dos.close()
                if (conn.responseCode != 200) return@async false
                val br = BufferedReader(InputStreamReader(conn.inputStream))
                val response = br.readLine()
                if (!response.contains("OK")) return@async false
                return@async true
            }
            return result
        }
    }
}