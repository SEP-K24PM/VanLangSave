package com.vls.adminservice.repository;

import com.vls.adminservice.Entity.Admin_account;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface admin_accountRepository extends JpaRepository<Admin_account, String> {
    @Query("SELECT acc FROM Admin_account acc")
    List<Admin_account> findAll();


/*
    @Override
    public List<Admin_account> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Admin_account> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Admin_account> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String uuid) {

    }

    @Override
    public void delete(Admin_account admin_account) {

    }

    @Override
    public void deleteAll(Iterable<? extends Admin_account> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Admin_account> S save(S s) {
        return null;
    }

    @Override
    public <S extends Admin_account> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Admin_account> findById(String str) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String str) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Admin_account> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Admin_account> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Admin_account getOne(String str) {
        return null;
    }

    @Override
    public <S extends Admin_account> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Admin_account> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Admin_account> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Admin_account> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Admin_account> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Admin_account> boolean exists(Example<S> example) {
        return false;
    }
 */
}
