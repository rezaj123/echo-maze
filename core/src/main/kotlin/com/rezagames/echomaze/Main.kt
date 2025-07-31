package com.rezagames.echomaze

import com.badlogic.gdx.Game

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class Main : Game() {
    override fun create() {
        setScreen(FirstScreen())
    }
}
