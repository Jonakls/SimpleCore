package me.jonakls.simplecore.handlers;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

public class HoverHandler {

    private TextComponent hoverFormat;

    public void setHover(String text, String clickOption, String clickAction, String hoverText){

        hoverFormat = new TextComponent(text);

        hoverFormat.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(hoverText).create()));

        hoverFormat.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(clickOption), clickAction));

    }

    public void setHoverList(String text, String clickOption, String clickAction, List<String> hoverText){

        hoverFormat = new TextComponent(text);

        ComponentBuilder component = new ComponentBuilder("");

        for (int i = 0; i < hoverText.size(); i++) {
            if (i != 0) component.append("\n");
            component.append(hoverText.get(i));
        }
        hoverFormat.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(component).create()));

        hoverFormat.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(clickOption), clickAction));

    }

    public TextComponent getHoverFormat(){
        return hoverFormat;
    }


}
