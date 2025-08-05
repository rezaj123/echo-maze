package com.rezagames.echomaze.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

data class HealthComponent(
    var health : Float = 100f,
): Component{
    companion object {
        val mapper = mapperFor<HealthComponent>()
    }
}
