package net.hinyari.gohancountdown

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.StageStyle


fun main(args: Array<String>) {

    println("起動中です…")
    Application.launch(HTRMain::class.java, *args)

}

class HTRMain : Application() {
    
    companion object {
        lateinit var instance : HTRMain
        lateinit var stage : Stage
    }

    override fun start(primaryStage: Stage) {

        instance = this
        stage = primaryStage

        val root = FXMLLoader.load<Parent>(javaClass.classLoader.getResource("HTR.fxml"))

        primaryStage.title = "Heisei To Reiwa"
        primaryStage.scene = Scene(
                root
        )
        primaryStage.isResizable = false
        primaryStage.isAlwaysOnTop = true
        

        // 終了時処理
        primaryStage.setOnCloseRequest {
            Platform.exit()
            System.exit(0)
        }


        primaryStage.show()

        println("起動完了しました!")
    }


}


