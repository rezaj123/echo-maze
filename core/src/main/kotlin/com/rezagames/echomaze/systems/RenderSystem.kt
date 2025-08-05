package com.rezagames.echomaze.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.rezagames.echomaze.components.PositionComponent
import com.rezagames.echomaze.components.RenderComponent
import com.rezagames.echomaze.components.SizeComponent

class RenderSystem(private val batch: SpriteBatch) : IteratingSystem(
    Family.all(
        PositionComponent::class.java,
        RenderComponent::class.java,
        SizeComponent::class.java
    ).get()
) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val position = PositionComponent.mapper[entity]
        val renderComponent = RenderComponent.mapper[entity]
        val size = SizeComponent.mapper[entity]

        batch.draw(
            renderComponent.texture,
            position.positionVector.x,
            position.positionVector.y,
            size.width,
            size.height
        )

    }

    override fun update(deltaTime: Float) {
        batch.begin()
        super.update(deltaTime)
        batch.end()
    }
}
