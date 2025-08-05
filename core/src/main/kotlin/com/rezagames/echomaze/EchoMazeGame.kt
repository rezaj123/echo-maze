package com.rezagames.echomaze


import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

import com.rezagames.echomaze.screens.GameScreen
import com.rezagames.echomaze.systems.MovementSystem
import com.rezagames.echomaze.systems.PlayerInputSystem
import com.rezagames.echomaze.systems.RenderSystem

import com.rezagames.echomaze.utils.GameContext


/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class EchoMazeGame : Game() {

    lateinit var batch: SpriteBatch
    lateinit var engine: Engine
    lateinit var playerTexture : Texture
    lateinit var gameContext : GameContext




    override fun create() {
        batch = SpriteBatch()
        engine = Engine()
        playerTexture = Texture(Gdx.files.internal("images/player_idle.png"))

        gameContext = GameContext(batch,
            engine,
            RenderSystem(batch),
            playerTexture,
            PlayerInputSystem(),MovementSystem()
        )
        setScreen(GameScreen(gameContext))
    }
    override fun dispose() {
        super.dispose()
        batch.dispose()
        playerTexture.dispose()
    }
}
