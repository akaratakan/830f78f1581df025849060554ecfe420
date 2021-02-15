package com.atakanakar.a830f78f1581df025849060554ecfe420.commons

import java.io.Serializable


/**
 * Created by atakanakar on 15.02.2021.
 */
data class SpaceShip(
    val name: String,
    val health: Int,
    val ugs: Int,
    val eus: Int,
    val ds: Int): Serializable
