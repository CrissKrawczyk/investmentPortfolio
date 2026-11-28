package pl.portfolio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.portfolio.businessLogic.validations.PositionValidations;
import pl.portfolio.businessLogic.validations.ValidatedObject;
import pl.portfolio.domain.model.Account;
import pl.portfolio.domain.model.Position;
import pl.portfolio.domain.repository.PositionRepository;

import java.util.List;
import java.util.Map;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public ValidatedObject<Position> createPosition(Position position) {
        PositionValidations positionValidations = new PositionValidations(position);
        Map<String, String> errors = positionValidations.getPositionErrors();
        if (!errors.isEmpty()) {
            return new ValidatedObject<>(position, errors);
        }
        return new ValidatedObject<>(positionRepository.save(position));
    }

    public List<Position> getAccountPositions(Account account) {
        return positionRepository.findAllByAccount(account);
    }

    public Position getPosition(Integer id) {
        return positionRepository.getReferenceById(id);
    }

}
