package com.rezagames.echomaze.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.rezagames.echomaze.EchoMazeGame

import com.rezagames.echomaze.utils.GameConstants.WORLD_HEIGHT
import com.rezagames.echomaze.utils.GameConstants.WORLD_WIDTH

object Lwjgl3Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        if (StartupHelper.startNewJvmIfRequired()) return
        createApplication()
    }

    private fun createApplication(): Lwjgl3Application {
        return Lwjgl3Application(EchoMazeGame(), defaultConfiguration)
    }

    private val defaultConfiguration: Lwjgl3ApplicationConfiguration
        get() {
            val configuration = Lwjgl3ApplicationConfiguration()
            configuration.setTitle("EchoMaze")
            configuration.setDecorated(false)


            configuration.useVsync(true)

            configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1)


            configuration.setWindowedMode(WORLD_WIDTH,WORLD_HEIGHT)

            configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png")
            return configuration
        }
}
