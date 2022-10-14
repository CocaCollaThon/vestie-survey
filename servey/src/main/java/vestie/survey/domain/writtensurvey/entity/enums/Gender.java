package vestie.survey.domain.writtensurvey.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by ShinD on 2022/10/12.
 */
public enum Gender {
	MAN,
	WOMAN,
	;

	@JsonCreator
	public static Gender from(String gender){
		for(Gender g : Gender.values()){
			if(g.name().equals(gender)){
				return g;
			}
		}
		return null;
	}
}
