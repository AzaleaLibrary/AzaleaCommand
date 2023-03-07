package net.azalealibrary.command;

import org.bukkit.ChatColor;
import org.bukkit.map.MinecraftFont;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class TextUtil {

    public static List<String> split(String text, int width) {
        List<String> words = Arrays.stream(text.split("\\s+")).collect(Collectors.toList());

        String raw = ChatColor.stripColor(text);
        int textWidth = MinecraftFont.Font.getWidth(raw);
        int lineCount = (int) Math.ceil((float) textWidth / width);
        List<String> lines = new ArrayList<>(Collections.nCopies(lineCount, ""));

        for (int i = 0; i < lines.size(); i++) {
            while (words.size() > 0 && lines.get(i).length() + words.get(0).length() <= width) {
                lines.add(i, lines.remove(i) + " " + words.remove(0));
            }
        }
        return lines.stream().map(String::trim).filter(l -> !l.isBlank()).collect(Collectors.toList());
    }

    public static List<String> matching(String text, List<String> completions) {
        List<String> matching = new ArrayList<>();
        StringUtil.copyPartialMatches(text, completions, matching);
        Collections.sort(matching);
        return matching;
    }
}
