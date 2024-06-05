package com.example.bankservice.controllers;

import com.example.bankservice.dto.FIOSearchRequest;
import com.example.bankservice.entity.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Search API", description = "API for searching clients")
@RequestMapping("/api/search")
public interface SearchApi {

    @GetMapping("/byBirthdate/{birthdate}")
    @Operation(
            summary = "Search clients by birthdate",
            method = "GET",
            tags = {"Search API"},
            description = "Retrieve a list of clients by birthdate",
            operationId = "searchByBirthdate",
            parameters = @Parameter(name = "birthdate", description = "Birthdate to search for", required = true, example = "2000-01-01")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Client.class)))}),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Client> searchByBirthdate(@PathVariable String birthdate);

    @GetMapping("/byPhone/{phone}")
    @Operation(
            summary = "Search clients by phone",
            method = "GET",
            tags = {"Search API"},
            description = "Retrieve a list of clients by phone",
            operationId = "searchByPhone",
            parameters = @Parameter(name = "phone", description = "Phone number to search for", required = true, example = "+1234567890")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Client.class)))}),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Client> searchByPhone(@PathVariable String phone);

    @GetMapping("/byEmail/{email}")
    @Operation(
            summary = "Search clients by email",
            method = "GET",
            tags = {"Search API"},
            description = "Retrieve a list of clients by email",
            operationId = "searchByEmail",
            parameters = @Parameter(name = "email", description = "Email address to search for", required = true, example = "example@example.com")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Client.class)))}),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Client> searchByEmail(@PathVariable String email);

    @GetMapping("/byFIO")
    @Operation(
            summary = "Search clients by FIO",
            method = "GET",
            tags = {"Search API"},
            description = "Retrieve a list of clients by full name (FIO)",
            operationId = "searchByFIO"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Client.class)))}),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Client> searchByFIO(@RequestBody FIOSearchRequest fioSearchRequest);
}
