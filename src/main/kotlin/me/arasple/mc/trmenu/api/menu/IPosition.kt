package me.arasple.mc.trmenu.api.menu

import me.arasple.mc.trmenu.module.display.MenuSession

/**
 * @author Arasple
 * @date 2021/1/25 19:17
 */
interface IPosition {

    fun generatePosition(session: MenuSession)

    fun currentPosition(session: MenuSession)

}