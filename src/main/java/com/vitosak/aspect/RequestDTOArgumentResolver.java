package com.vitosak.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitosak.annotations.FromDTO;
import com.vitosak.annotations.RequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;




/**
 * A custom implementation of {@link HandlerMethodArgumentResolver} designed to resolve method parameters
 * annotated with {@link RequestDTO}. This resolver extracts the request body, deserializes it into a
 * map, and transforms the data into the desired object type using a static method annotated with {@link FromDTO}.
 *
 * This resolver simplifies and automates the process of converting JSON payload from HTTP requests
 * into strongly-typed objects, adhering to custom transformation logic defined in the targeted class.
 *
 * Key operations performed by this resolver:
 * - Determines whether a method parameter is annotated with {@link RequestDTO}.
 * - Reads the raw JSON body of the request and converts it into a {@link Map}.
 * - Searches for a static method annotated with {@link FromDTO} in the parameter's target class.
 * - Invokes the discovered transformation method to create an instance of the target type based on the deserialized data.
 * Exceptions:
 * - Throws {@link IllegalStateException} if the target type lacks a method annotated with {@link FromDTO}.
 * - Propagates exceptions from reflection or data binding operations.
 * Nemat da rabotit to so aspect deka spring si imat svoj poseben za controleri pa moras vaka.
 * Si grmit AI docs
 */
@Component
public class RequestDTOArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestDTO.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        assert servletRequest != null;
        String body = servletRequest.getReader().lines().collect(Collectors.joining());

        ObjectMapper mapper = new ObjectMapper();

        @SuppressWarnings("unchecked")
        Map<String, Object> dto = mapper.readValue(body, Map.class);

        Class<?> targetType = parameter.getParameterType();
        Method fromDtoMethod = Arrays.stream(targetType.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(FromDTO.class))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No method with @FromDTO found in " + targetType));

        return fromDtoMethod.invoke(null, dto);
    }
}
