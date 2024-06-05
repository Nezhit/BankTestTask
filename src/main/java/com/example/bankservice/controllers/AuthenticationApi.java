package com.example.bankservice.controllers;

import com.example.bankservice.dto.AuthRsDto;
import com.example.bankservice.dto.LoginRequest;
import com.example.bankservice.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authentication API", description = "API for client authentication")
@RequestMapping("/api/auth")
public interface AuthenticationApi {

    @PostMapping
    @Operation(
            summary = "Authenticate a client",
            method = "POST",
            tags = {"Authentication API"},
            description = "Authenticate a client using the provided login request",
            operationId = "authClient"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String authClient(@Valid @Schema(implementation = LoginRequest.class) @RequestBody LoginRequest loginRequest);
}