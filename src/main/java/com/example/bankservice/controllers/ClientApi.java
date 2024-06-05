package com.example.bankservice.controllers;

import com.example.bankservice.dto.AddEmailRequest;
import com.example.bankservice.dto.AddPhoneRequest;
import com.example.bankservice.dto.ChangeEmailRequest;
import com.example.bankservice.dto.ChangePhoneRequest;
import com.example.bankservice.entity.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Tag(name = "Client API", description = "API for managing clients")
@RequestMapping("/api/client")
@Validated
public interface ClientApi {

    @GetMapping
    @Operation(
            summary = "Get all clients",
            method = "GET",
            tags = {"Client API"},
            description = "Retrieve a list of all clients",
            operationId = "getClients"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Client.class)))}),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Client> getClients();

    @DeleteMapping("/deleteEmail/{id}/{email}")
    @Operation(
            summary = "Delete email from client",
            method = "DELETE",
            tags = {"Client API"},
            description = "Delete an email from a client by ID",
            operationId = "deleteEmailFromClient",
            parameters = {
                    @Parameter(name = "id", description = "ID of the client", required = true, example = "1"),
                    @Parameter(name = "email", description = "Email to delete", required = true, example = "example@example.com")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email deleted",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
            @ApiResponse(responseCode = "404", description = "Client or email not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Set<String> deleteEmailFromClient(@PathVariable Long id, @PathVariable String email);

    @DeleteMapping("/deletePhone/{id}/{phone}")
    @Operation(
            summary = "Delete phone from client",
            method = "DELETE",
            tags = {"Client API"},
            description = "Delete a phone from a client by ID",
            operationId = "deletePhoneFromClient",
            parameters = {
                    @Parameter(name = "id", description = "ID of the client", required = true, example = "1"),
                    @Parameter(name = "phone", description = "Phone to delete", required = true, example = "+1234567890")
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Phone deleted",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
            @ApiResponse(responseCode = "404", description = "Client or phone not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Set<String> deletePhoneFromClient(@PathVariable Long id, @PathVariable String phone);

    @PutMapping("/changePhone")
    @Operation(
            summary = "Change phone of client",
            method = "PUT",
            tags = {"Client API"},
            description = "Change the phone number of a client",
            operationId = "changePhoneFromClient"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Phone changed",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Set<String> changePhoneFromClient(@Valid @RequestBody ChangePhoneRequest changePhoneRequest);

    @PutMapping("/changeEmail")
    @Operation(
            summary = "Change email of client",
            method = "PUT",
            tags = {"Client API"},
            description = "Change the email address of a client",
            operationId = "changeEmailFromClient"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email changed",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Set<String> changeEmailFromClient(@Valid @RequestBody ChangeEmailRequest changeEmailRequest);

    @PostMapping("/addPhone")
    @Operation(
            summary = "Add phone to client",
            method = "POST",
            tags = {"Client API"},
            description = "Add a new phone number to a client",
            operationId = "addPhone"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Phone added",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Set<String> addPhone(@Valid @RequestBody AddPhoneRequest addPhoneRequest);

    @PostMapping("/addEmail")
    @Operation(
            summary = "Add email to client",
            method = "POST",
            tags = {"Client API"},
            description = "Add a new email address to a client",
            operationId = "addEmail"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email added",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = String.class)))}),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Set<String> addEmail(@Valid @RequestBody AddEmailRequest addEmailRequest);
}
