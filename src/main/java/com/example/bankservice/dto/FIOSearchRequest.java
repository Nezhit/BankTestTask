package com.example.bankservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FIOSearchRequest {
    private String name;
    private String surname;
    private String papaname;
}
