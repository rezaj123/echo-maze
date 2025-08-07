package com.rezagames.echomaze.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.rezagames.echomaze.components.MovementComponent
import com.rezagames.echomaze.components.PositionComponent
import com.rezagames.echomaze.components.SizeComponent
import kotlin.math.floor

class CollisionSystem(
    private val gameMap: TiledMap,
    private val hitboxOffsetX: Float,
    private val hitboxOffsetY: Float,
    private val hitboxWidth: Float,
    private val hitboxHeight: Float
) : IteratingSystem(
    Family.all(
        MovementComponent::class.java,
        PositionComponent::class.java,
        SizeComponent::class.java
    ).get()
) {
    private val wallLayer = gameMap.layers.get("walls") as TiledMapTileLayer
    private val tileWidth = wallLayer.tileWidth
    private val tileHeight = wallLayer.tileHeight

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val position = PositionComponent.mapper[entity].positionVector
        val speed = MovementComponent.mapper[entity].speed

        position.x += speed.x * deltaTime

        val hitboxX = position.x + hitboxOffsetX
        val hitboxY = position.y + hitboxOffsetY

        if (speed.x > 0) {
            val tileX = floor((hitboxX + hitboxWidth) / tileWidth).toInt()
            val tileYTop = floor((hitboxY + hitboxHeight) / tileHeight).toInt()
            val tileYBottom = floor(hitboxY / tileHeight).toInt()

            if (isCellBlocked(tileX, tileYTop) || isCellBlocked(tileX, tileYBottom)) {
                position.x = (tileX * tileWidth) - hitboxWidth - hitboxOffsetX - 0.01f
                speed.x = 0f
            }
        } else if (speed.x < 0) {
            val tileX = floor(hitboxX / tileWidth).toInt()
            val tileYTop = floor((hitboxY + hitboxHeight) / tileHeight).toInt()
            val tileYBottom = floor(hitboxY / tileHeight).toInt()

            if (isCellBlocked(tileX, tileYTop) || isCellBlocked(tileX, tileYBottom)) {
                position.x = ((tileX + 1) * tileWidth) - hitboxOffsetX
                speed.x = 0f
            }
        }

        position.y += speed.y * deltaTime

        val newHitboxY = position.y + hitboxOffsetY
        val newHitboxX = position.x + hitboxOffsetX

        if (speed.y > 0) {
            val tileY = floor((newHitboxY + hitboxHeight) / tileHeight).toInt()
            val tileXLeft = floor(newHitboxX / tileWidth).toInt()
            val tileXRight = floor((newHitboxX + hitboxWidth) / tileWidth).toInt()

            if (isCellBlocked(tileXLeft, tileY) || isCellBlocked(tileXRight, tileY)) {
                position.y = (tileY * tileHeight) - hitboxHeight - hitboxOffsetY - 0.01f
                speed.y = 0f
            }
        } else if (speed.y < 0) {
            val tileY = floor(newHitboxY / tileHeight).toInt()
            val tileXLeft = floor(newHitboxX / tileWidth).toInt()
            val tileXRight = floor((newHitboxX + hitboxWidth) / tileWidth).toInt()

            if (isCellBlocked(tileXLeft, tileY) || isCellBlocked(tileXRight, tileY)) {
                position.y = ((tileY + 1) * tileHeight) - hitboxOffsetY
                speed.y = 0f
            }
        }
    }

    private fun isCellBlocked(x: Int, y: Int): Boolean {
        val cell = wallLayer.getCell(x, y)
        return cell?.tile?.properties?.containsKey("collidable") == true
    }
}
