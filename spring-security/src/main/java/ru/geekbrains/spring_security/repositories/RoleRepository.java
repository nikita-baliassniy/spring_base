package ru.geekbrains.spring_security.repositories;


import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring_security.entities.Role;

@Repository
@Profile("dao")
public interface RoleRepository extends CrudRepository<Role, Long> {
}