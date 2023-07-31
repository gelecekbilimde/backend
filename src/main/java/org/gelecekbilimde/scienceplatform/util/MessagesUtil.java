package org.gelecekbilimde.scienceplatform.util;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class MessagesUtil {
	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

	public MessagesUtil() {
		messageSource.setBasename("lang/messages");
		messageSource.setDefaultEncoding("ISO-8859-1");
	}

	public String getMessage(String key, String localeCode){
		if (localeCode == null){
			localeCode = "en";
		}
		return messageSource.getMessage(key, null, new Locale(localeCode));
	}

}
