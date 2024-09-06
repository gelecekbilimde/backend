package org.gelecekbilimde.scienceplatform.common.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ListUtil {

	@SuppressWarnings({"unchecked", "This method is unused by the application directly but Spring is using it in the background."})
	public static <C> List<C> to(Object object, Class<C> clazz) {
		return (List<C>) object;
	}

}
