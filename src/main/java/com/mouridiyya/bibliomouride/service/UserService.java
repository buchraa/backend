package com.mouridiyya.bibliomouride.service;

import com.google.common.collect.Lists;
import com.mouridiyya.bibliomouride.entity.*;
import com.mouridiyya.bibliomouride.entity.security.User;

import com.mouridiyya.bibliomouride.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


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



}
