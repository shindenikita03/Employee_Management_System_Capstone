package com.example.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description="JwtResponse Model Info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	
	@Schema(description = "Enter token")
	private String token;

  
}
