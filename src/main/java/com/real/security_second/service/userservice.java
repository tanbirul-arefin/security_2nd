package com.real.security_second.service;

import com.real.security_second.Repository.userrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userservice {
    private final userrepository userrepository;
}
