package com.rezagames.echomaze


import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader

import com.rezagames.echomaze.screens.GameScreen
import com.rezagames.echomaze.systems.MovementSystem
import com.rezagames.echomaze.systems.PlayerInputSystem
import com.rezagames.echomaze.systems.RenderSystem

import com.rezagames.echomaze.utils.GameContext


/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class EchoMazeGame : Game() {

    lateinit var batch: SpriteBatch
    lateinit var engine: Engine
    lateinit var gameContext: GameContext
    lateinit var assetManager: AssetManager


    override fun create() {
        assetManager = AssetManager()
        batch = SpriteBatch()
        engine = Engine()
        assetManager.load("images/player_idle.png", Texture::class.java)
        assetManager.setLoader(TiledMap::class.java, TmxMapLoader(InternalFileHandleResolver()))
        assetManager.load("maps/level1.tmx", TiledMap::class.java)
        assetManager.finishLoading()
        gameContext = GameContext(
            batch,
            engine,
            RenderSystem(batch),
            PlayerInputSystem(),
            MovementSystem(),
            assetManager
        )
        setScreen(GameScreen(gameContext))
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()
        assetManager.dispose()
    }
}
