package com.rezagames.echomaze.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.rezagames.echomaze.components.MovementComponent
import com.rezagames.echomaze.components.PlayerInputComponent
import com.rezagames.echomaze.components.PositionComponent
import com.rezagames.echomaze.components.RenderComponent
import com.rezagames.echomaze.components.SizeComponent
import ktx.ashley.entity
import ktx.ashley.with

class MovementSystem : IteratingSystem(
    Family.all(
        MovementComponent::class.java,
        PositionComponent::class.java
    ).get()
) {

    override fun processEntity(entity: Entity?, deltaTime: Float) {

        PositionComponent.mapper.get(entity).positionVector.add(
            MovementComponent.mapper.get(entity).speed.cpy().scl(deltaTime)
        )
    }
}
