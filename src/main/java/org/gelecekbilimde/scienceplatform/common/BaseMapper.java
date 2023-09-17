package org.gelecekbilimde.scienceplatform.common;

import java.util.Collection;
import java.util.List;


public interface BaseMapper<S, T> {

	T map(S source);
	List<T> map(Collection<S> sources);

}
