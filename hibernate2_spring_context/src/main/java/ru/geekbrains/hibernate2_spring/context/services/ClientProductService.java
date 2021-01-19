package ru.geekbrains.hibernate2_spring.context.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.hibernate2_spring.context.DAO.ClientDBRepository;
import ru.geekbrains.hibernate2_spring.context.DAO.ProductDBRepository;

@Service
@RequiredArgsConstructor
public class ClientProductService {
    private final ProductDBRepository productInMemoryRepository;
    private final ClientDBRepository clientDBRepository;

    public void getClientsByProducts(Long id) {
        System.out.println(productInMemoryRepository.findClientsByProductId(id));
    }

    public void getProductsByClint(Long id) {
        System.out.println(clientDBRepository.findProductsByClientId(id));
    }
}