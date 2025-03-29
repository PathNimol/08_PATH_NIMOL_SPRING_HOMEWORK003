package org.nimol.springhomework003.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String message;
    private T payload;
    private HttpStatus status;
    private final LocalDateTime time = LocalDateTime.now();
}
