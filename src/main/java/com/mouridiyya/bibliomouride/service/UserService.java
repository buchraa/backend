package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.*;
import com.mouridiyya.bibliomouride.entity.security.User;

import com.mouridiyya.bibliomouride.model.payload.request.SignupRequest;
import com.mouridiyya.bibliomouride.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.mouridiyya.bibliomouride.model.payload.request.SignupRequest;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> manageUsers(Integer pageNo, Integer pageSize, String sortBy)

    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<User> pagedResult = userRepository.findAll(paging);
        return pagedResult;
    }


    public User get(long id) {

        return userRepository.findById(id).get();
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }






}
