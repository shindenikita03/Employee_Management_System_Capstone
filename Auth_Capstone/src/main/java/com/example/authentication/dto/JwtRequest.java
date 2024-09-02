package com.example.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="JwtRequest Model Info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

	    @NotBlank
	    @Schema(description = "Enter username")
	    private String username;

	    @NotBlank
	    @Schema(description = "Enter password")
	    private String password;

	   

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	}
