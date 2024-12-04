package com.darkoum.darkoum.service.interfaces;

import com.darkoum.darkoum.dtos.request.AuthDtoRequest;
import com.darkoum.darkoum.dtos.response.AuthDtoResponse;

public interface AuthServiceInterface {

    AuthDtoResponse register(AuthDtoRequest authDtoRequest);

    AuthDtoResponse login(AuthDtoRequest authDtoRequest);
}
