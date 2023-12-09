package org.gelecekbilimde.scienceplatform.post.service.impl;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Helper {

    public String slugify(String text) {
        String normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        Pattern pattern = Pattern.compile("[^\\p{Alnum}]+");
        String slug = pattern.matcher(normalizedText).replaceAll("-").toLowerCase();
        return slug.replaceAll("^-+|-+$", "");
    }
}
