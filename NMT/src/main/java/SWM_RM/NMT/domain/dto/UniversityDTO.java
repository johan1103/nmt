package SWM_RM.NMT.domain.dto;

import SWM_RM.NMT.domain.University;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniversityDTO {
    String name;

    public static UniversityDTO universityDtoConverter(University university){
        UniversityDTO universityDto=new UniversityDTO();
        universityDto.setName(university.getUniversityName());
        return universityDto;
    }
}
