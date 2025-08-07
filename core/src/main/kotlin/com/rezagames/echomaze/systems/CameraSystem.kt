package com.rezagames.echomaze.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.math.Vector3
import com.rezagames.echomaze.components.PlayerInputComponent
import com.rezagames.echomaze.components.PositionComponent
import com.rezagames.echomaze.utils.GameConstants.CAMERA_LAZINESS
import com.rezagames.echomaze.utils.GameConstants.PLAYER_HEIGHT
import com.rezagames.echomaze.utils.GameConstants.PLAYER_WIDTH

class CameraSystem(
    private val camera: OrthographicCamera,
    private val gameMap: TiledMap
) : IteratingSystem(
    Family.all(
        PositionComponent::class.java,
        PlayerInputComponent::class.java
    ).get()
) {
    private val mapWidth = gameMap.properties.get("width", Int::class.java) * gameMap.properties.get("tilewidth", Int::class.java)
    private val mapHeight = gameMap.properties.get("height", Int::class.java) * gameMap.properties.get("tileheight", Int::class.java)

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val position = PositionComponent.mapper[entity]
        val targetPosition = Vector3(
            position.positionVector.x + (PLAYER_WIDTH / 2),
            position.positionVector.y + (PLAYER_HEIGHT / 2),
            0f
        )
        camera.position.lerp(targetPosition, CAMERA_LAZINESS)
    }

    override fun update(deltaTime: Float) {
        super.update(deltaTime)

        val camHalfWidth = camera.viewportWidth * 0.5f
        val camHalfHeight = camera.viewportHeight * 0.5f

        camera.position.x = camera.position.x.coerceIn(camHalfWidth, mapWidth - camHalfWidth)
        camera.position.y = camera.position.y.coerceIn(camHalfHeight, mapHeight - camHalfHeight)

        camera.update()
    }
}
