package me.arasple.mc.trmenu.actions.ext;

import io.izzel.taboolib.internal.apache.lang3.math.NumberUtils;
import io.izzel.taboolib.module.locale.TLocale;
import io.izzel.taboolib.util.ArrayUtil;
import io.izzel.taboolib.util.Strings;
import io.izzel.taboolib.util.Variables;
import me.arasple.mc.trmenu.actions.BaseAction;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryEvent;

/**
 * @author Arasple
 * @date 2019/10/5 14:33
 */
public class IconActionTitle extends BaseAction {

    private String title;
    private String subtitle;
    private int fadein = -1;
    private int stay = -1;
    private int fadeout = -1;

    public IconActionTitle(String command) {
        super(command);

        for (Variables.Variable variable : new Variables(command).find().getVariableList()) {
            if (variable.isVariable()) {
                String[] x = variable.getText().split("=");
                if (x.length >= 2) {
                    String type = x[0].toLowerCase(), value = ArrayUtil.arrayJoin(x, 1);
                    switch (type) {
                        case "title":
                            this.title = value;
                            continue;
                        case "subtitle":
                            this.subtitle = value;
                            continue;
                        case "fadein":
                            this.fadein = NumberUtils.toInt(value, 20);
                            continue;
                        case "stay":
                            this.stay = NumberUtils.toInt(value, 40);
                            continue;
                        case "fadeout":
                            this.fadeout = NumberUtils.toInt(value, 20);
                            continue;
                        default:
                    }
                }
            }
        }
        this.fadein = fadein <= 0 ? 20 : fadein;
        this.fadeout = fadeout <= 0 ? 20 : fadeout;
        this.stay = stay <= 0 ? 40 : stay;

        // Title: <TITLE=你好啊><SUBTITLE=测试副标题><STAY=11><FADEINT=111><FADEOUT=134>
    }

    @Override
    public void onExecute(Player player, InventoryEvent e, String... args) {
        TLocale.Display.sendTitle(player,
                TLocale.Translate.setPlaceholders(player, Strings.replaceWithOrder(title, args)),
                TLocale.Translate.setPlaceholders(player, Strings.replaceWithOrder(subtitle, args)),
                fadein, stay, fadeout
        );
    }

}
