package com.example.bankservice.controllers;

import com.example.bankservice.dto.ClientCreateDto;
import com.example.bankservice.dto.ClientRsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Register API", description = "API for registering new clients")
@RequestMapping("/api/register")
public interface RegisterApi {

    @PostMapping
    @Operation(
            summary = "Register a new client",
            method = "POST",
            tags = {"Register API"},
            description = "Register a new client using the provided DTO",
            operationId = "registerClient"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client registered",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ClientRsDto register(@Valid @Schema(implementation = ClientCreateDto.class) @RequestBody ClientCreateDto clientCreateDto);
}
