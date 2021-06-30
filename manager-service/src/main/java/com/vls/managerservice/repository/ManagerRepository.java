package com.vls.managerservice.repository;

import com.vls.managerservice.module.Manager_module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager_module, String> {
    @Query("SELECT info FROM Manager_module info")
    List<Manager_module> findall();

    @Query("SELECT info FROM Manager_module info where info.username = :name and info.pwd = :pwd")
    List<Manager_module> checked(@Param("name") String username, @Param("pwd") String password);

}
