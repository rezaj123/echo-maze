package com.rezagames.echomaze.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import com.rezagames.echomaze.components.MovementComponent
import com.rezagames.echomaze.components.PlayerInputComponent
import com.rezagames.echomaze.utils.GameConstants.PLAYER_SPEED

class PlayerInputSystem : IteratingSystem(
    Family.all(
        MovementComponent::class.java,
        PlayerInputComponent::class.java
    ).get()
) {

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        var speedVector = Vector2(0f, 0f).apply {
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) y = 1f
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) y = -1f
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x = -1f
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x = 1f
        }
        speedVector = speedVector.nor().scl(PLAYER_SPEED)
        MovementComponent.mapper[entity].speed = speedVector
    }
}
