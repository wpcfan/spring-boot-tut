package dev.local

import com.avos.avoscloud.AVOSCloud
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
    val appId = "ABTVy9loYSaIMc3EkaFRupTL-gzGzoHsz"
    val appKey = "mwywiweRadXf6CztkUNyUsPS"
    val masterKey = "QwmLD7GkwU27InX04BgUJUjM"
    AVOSCloud.initialize(appId, appKey, masterKey)
    AVOSCloud.setDebugLogEnabled(true)
}
