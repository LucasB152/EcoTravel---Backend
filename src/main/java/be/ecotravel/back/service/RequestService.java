package be.ecotravel.back.service;

import be.ecotravel.back.entity.Request;
import be.ecotravel.back.entity.RequestStatusEnum;
import be.ecotravel.back.entity.User;
import be.ecotravel.back.repository.RequestRepository;
import be.ecotravel.back.repository.UserRepository;
import be.ecotravel.back.request.dto.RequestCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private final RequestRepository requestRepo;

    private final UserRepository userRepo;

    @Autowired
    public RequestService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepo = requestRepository;
        this.userRepo = userRepository;
    }

    public void createRequest(RequestCreationDto requestDto) {
        User user = userRepo.getReferenceById(requestDto.userId());
        Request request = new Request(requestDto.text(), RequestStatusEnum.WAITING, user);
        requestRepo.save(request);
    }

}
