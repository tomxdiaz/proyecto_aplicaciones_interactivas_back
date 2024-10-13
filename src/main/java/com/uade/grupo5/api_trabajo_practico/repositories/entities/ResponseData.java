package com.uade.grupo5.api_trabajo_practico.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData <T> {
  private Boolean ok;
  private String error;
  private T data;

  public static <T> ResponseData<T> success(T data) {
    return new ResponseData<>(true, null, data);
}

public static <T> ResponseData<T> error(String errorMessage) {
    return new ResponseData<>(false, errorMessage, null);
}
}
