package org.gelecekbilimde.scienceplatform.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Degree {
	DEGREE_ILKOKUL(1),
	DEGREE_ORTA_OKUL(2),
	DEGREE_LISE(3),
	DEGREE_ON_LISANS(4),
	DEGREE_LISANS(5),
	DEGREE_YUKSEK_LISANS(6),
	DEGREE_DOKTORA(7)

	;


	@Getter
	private final int degree;
}
