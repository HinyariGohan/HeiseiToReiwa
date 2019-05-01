package net.hinyari.gohancountdown

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import java.net.URL
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Controller : Initializable {

    @FXML
    private lateinit var labelToReiwa : Label

    @FXML
    private lateinit var labelFromReiwa : Label

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        startCounting()
    }

    private val service = Executors.newSingleThreadScheduledExecutor()
    
    private var time = LocalDateTime.now()
    
    private var reiwaTime = LocalDateTime.of(2019, 5, 1, 0, 0)
    
    @FXML
    private fun startCounting() {
        println("カウント開始")
        service.scheduleWithFixedDelay({
            
            Platform.runLater {
                reloadDisplay()
            }

        },0L,1L,TimeUnit.SECONDS)
    }
    
    private fun reloadDisplay() {
        time = LocalDateTime.now()
        
        val dur = Duration.between(time, reiwaTime)
        labelToReiwa.text = if(dur.seconds < 0) "Happy New Reiwa!" else parseDuration(Duration.between(time, reiwaTime))
        labelFromReiwa.text = parseDuration(Duration.between(reiwaTime, time))
    }
    
    private fun parseDuration(duration: Duration) : String {
        val h = String.format("%02d", duration.seconds / 3600)
        var m = String.format("%02d", (duration.seconds / 60) % 60)
        var s = String.format("%02d", duration.seconds % 60)
        var before = ""
        if (m.toInt() < 0 || s.toInt() < 0) {
            m = String.format("%02d", Math.abs(duration.seconds/ 60) % 60)
            s = String.format("%02d", Math.abs(duration.seconds % 60))
            before = " 前"
        } 
        
        
        return "$h:$m:$s$before"
    }
    
    
}
