package com.example.bankservice.controllers;

import com.example.bankservice.dto.TransferRequest;
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

@Tag(name = "Bank API", description = "API for bank operations")
@RequestMapping("/api/bank")
public interface BankApi {

    @PostMapping("/transfer")
    @Operation(
            summary = "Transfer money",
            method = "POST",
            tags = {"Bank API"},
            description = "Transfer money from one account to another using the provided transfer request",
            operationId = "transferMoney"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer successful",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Double.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public double transferMoney(@Valid @Schema(implementation = TransferRequest.class) @RequestBody TransferRequest transferRequest);
}
