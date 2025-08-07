package com.rezagames.echomaze.screens

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.rezagames.echomaze.components.MovementComponent
import com.rezagames.echomaze.components.PlayerInputComponent
import com.rezagames.echomaze.components.PositionComponent
import com.rezagames.echomaze.components.RenderComponent
import com.rezagames.echomaze.components.SizeComponent
import com.rezagames.echomaze.systems.CameraSystem
import com.rezagames.echomaze.systems.CollisionSystem
import com.rezagames.echomaze.systems.MovementSystem
import com.rezagames.echomaze.systems.PlayerInputSystem
import com.rezagames.echomaze.systems.RenderSystem
import com.rezagames.echomaze.utils.GameConstants.PLAYER_HEIGHT
import com.rezagames.echomaze.utils.GameConstants.PLAYER_HITBOX_HEIGHT
import com.rezagames.echomaze.utils.GameConstants.PLAYER_HITBOX_OFFSET_X
import com.rezagames.echomaze.utils.GameConstants.PLAYER_HITBOX_OFFSET_Y
import com.rezagames.echomaze.utils.GameConstants.PLAYER_HITBOX_WIDTH
import com.rezagames.echomaze.utils.GameConstants.PLAYER_STARTING_POSITION_X
import com.rezagames.echomaze.utils.GameConstants.PLAYER_STARTING_POSITION_Y
import com.rezagames.echomaze.utils.GameConstants.PLAYER_WIDTH
import com.rezagames.echomaze.utils.GameConstants.WORLD_HEIGHT_CAMERA
import com.rezagames.echomaze.utils.GameConstants.WORLD_WIDTH_CAMERA
import com.rezagames.echomaze.utils.GameContext
import ktx.ashley.entity
import ktx.ashley.with

class GameScreen(
    val gameContext: GameContext
) : Screen {
    private val batch: SpriteBatch = gameContext.batch
    private val engine: Engine = gameContext.engine
    private val renderSystem: RenderSystem = gameContext.renderSystem
    private lateinit var playerTexture: Texture
    private lateinit var level1Map: TiledMap
    private val playerInputSystem: PlayerInputSystem = gameContext.playerInputSystem
    private val movementSystem: MovementSystem = gameContext.movementSystem
    private lateinit var orthographicCamera: OrthographicCamera
    private lateinit var viewPort: Viewport
    private lateinit var orthogonalTiledMapRenderer: OrthogonalTiledMapRenderer

    override fun show() {
        orthographicCamera = OrthographicCamera()
        viewPort = FitViewport(WORLD_WIDTH_CAMERA, WORLD_HEIGHT_CAMERA, orthographicCamera)
        playerTexture = gameContext.assetManager.get("images/player_idle.png", Texture::class.java)
        level1Map = gameContext.assetManager.get("maps/level1.tmx", TiledMap::class.java)
        orthogonalTiledMapRenderer = OrthogonalTiledMapRenderer(level1Map)
        val collisionSystem = CollisionSystem(
            level1Map,
            PLAYER_HITBOX_OFFSET_X,
            PLAYER_HITBOX_OFFSET_Y,
            PLAYER_HITBOX_WIDTH,
            PLAYER_HITBOX_HEIGHT
        )
        createPlayer(engine, playerTexture)

        engine.addSystem(playerInputSystem)
        engine.addSystem(collisionSystem)
        engine.addSystem(movementSystem)
        engine.addSystem(renderSystem)
        engine.addSystem(CameraSystem(orthographicCamera,level1Map))


    }

    override fun render(delta: Float) {

        //a way to clean the screen
        Gdx.gl.glClearColor(0f, 60f / 255f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        viewPort.apply()
        orthographicCamera.update()

        orthogonalTiledMapRenderer.setView(orthographicCamera)
        orthogonalTiledMapRenderer.render()


        batch.projectionMatrix = orthographicCamera.combined

        engine.update(delta)

    }

    override fun resize(width: Int, height: Int) {

        if (width <= 0 || height <= 0) return

        viewPort.update(width, height, true)

    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        orthogonalTiledMapRenderer.dispose()
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


