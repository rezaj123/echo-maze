package com.rezagames.echomaze.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector3
import com.rezagames.echomaze.components.PlayerInputComponent
import com.rezagames.echomaze.components.PositionComponent
import com.rezagames.echomaze.utils.GameConstants.CAMERA_LAZINESS
import com.rezagames.echomaze.utils.GameConstants.PLAYER_HEIGHT
import com.rezagames.echomaze.utils.GameConstants.PLAYER_WIDTH

class CameraSystem(
    private val camera: OrthographicCamera
) : IteratingSystem(

    Family.all(
        PositionComponent::class.java,
        PlayerInputComponent::class.java
    ).get()
) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val position = PositionComponent.mapper[entity]
        val vector3D =
            Vector3(position.positionVector.x + (PLAYER_WIDTH / 2), position.positionVector.y + (PLAYER_HEIGHT / 2), 0f)
        camera.position.lerp(vector3D, CAMERA_LAZINESS)
    }

    override fun update(deltaTime: Float) {
        super.update(deltaTime)
        camera.update()
    }

}
