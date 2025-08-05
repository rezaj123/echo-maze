package com.rezagames.echomaze.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor

data class PositionComponent(
    var positionVector : Vector2 = Vector2(),
): Component {
    companion object {
        val mapper = mapperFor<PositionComponent>()
    }
}
