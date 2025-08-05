package com.rezagames.echomaze.screens

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.rezagames.echomaze.components.MovementComponent
import com.rezagames.echomaze.components.PlayerInputComponent
import com.rezagames.echomaze.components.PositionComponent
import com.rezagames.echomaze.components.RenderComponent
import com.rezagames.echomaze.components.SizeComponent
import com.rezagames.echomaze.systems.CameraSystem
import com.rezagames.echomaze.systems.MovementSystem
import com.rezagames.echomaze.systems.PlayerInputSystem
import com.rezagames.echomaze.systems.RenderSystem
import com.rezagames.echomaze.utils.GameConstants.PLAYER_HEIGHT
import com.rezagames.echomaze.utils.GameConstants.PLAYER_STARTING_POSITION_X
import com.rezagames.echomaze.utils.GameConstants.PLAYER_STARTING_POSITION_Y
import com.rezagames.echomaze.utils.GameConstants.PLAYER_WIDTH
import com.rezagames.echomaze.utils.GameConstants.WORLD_HEIGHT
import com.rezagames.echomaze.utils.GameConstants.WORLD_WIDTH
import com.rezagames.echomaze.utils.GameContext
import ktx.ashley.entity
import ktx.ashley.with

class GameScreen(
    gameContext: GameContext
) : Screen {
    private val batch: SpriteBatch = gameContext.batch
    private val engine: Engine = gameContext.engine
    private val renderSystem: RenderSystem = gameContext.renderSystem
    private val playerTexture: Texture = gameContext.playerTexture
    private val playerInputSystem: PlayerInputSystem = gameContext.playerInputSystem
    private val movementSystem: MovementSystem = gameContext.movementSystem
    private val orthographicCamera: OrthographicCamera = OrthographicCamera()
    private val viewPort: Viewport = FitViewport(WORLD_WIDTH.toFloat(), WORLD_HEIGHT.toFloat(),orthographicCamera)


    override fun show() {
        val playerEntity = createPlayer(engine,playerTexture)
        engine.entity {
            playerEntity
        }
        engine.addSystem(CameraSystem(orthographicCamera))
        engine.addSystem(renderSystem)
        engine.addSystem(playerInputSystem)
        engine.addSystem(movementSystem)


    }

    override fun render(delta: Float) {

        //a way to clean the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f) // A dark blue color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        viewPort.apply()
        batch.projectionMatrix = orthographicCamera.combined
        engine.update(delta)

    }

    override fun resize(width: Int, height: Int) {

        if (width <= 0 || height <= 0) return

        viewPort.update(width, height,true)

    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
    }

    fun createPlayer(engine: Engine, texture: Texture) {
        engine.entity {
            with<SizeComponent> { width = PLAYER_WIDTH; height = PLAYER_HEIGHT }
            with<PositionComponent> { positionVector.set(PLAYER_STARTING_POSITION_X, PLAYER_STARTING_POSITION_Y) }
            with<RenderComponent> { this.texture = texture }
            with<PlayerInputComponent> { }
            with<MovementComponent> { }
        }
    }
}


