package uz.lee.news_app.occupation;

import org.springframework.stereotype.Service;
import uz.lee.news_app.custom_responses.exceptions.SourceAlreadyExistException;

import java.util.List;

@Service
public class OccupationService {

    private final OccupationsRepository occupationsRepository;

    public OccupationService(OccupationsRepository occupationsRepository) {
        this.occupationsRepository = occupationsRepository;
    }

    public String addOccupation(OccupationDto dto) {
        Boolean b = occupationsRepository.existsByNameIgnoreCase(dto.getName());
        if (b){
            throw new SourceAlreadyExistException("Occupation is already exist with name= " + dto.getName());
        }

        occupationsRepository.save(
                Occupations.builder()
                        .name(dto.getName())
                        .build()
        );

        return "Occupation saved with name= " + dto.getName();
    }

    public List<Occupations> getAllOccupations() {
        return occupationsRepository.findAll();
    }
}
