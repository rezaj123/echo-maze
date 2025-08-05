package com.rezagames.echomaze.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor

data class MovementComponent (
    var speed : Vector2 = Vector2.Zero
) : Component {
    companion object {
        val mapper = mapperFor<MovementComponent>()
    }
}
