package com.vitosak.aspect;

import com.vitosak.annotations.FromDTO;
import com.vitosak.annotations.RequestDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;

@Aspect
//@Component
public class RequestDTOAspect {
//    @Autowired
//    private ApplicationContext applicationContext;


    @Around("execution(* *(.., @com.vitosak.annotations.RequestDTO (*), ..))")
    public Object injectRequestDTO(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
//        Method method = pjp.getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes()); // sa koristit ova radi interface metodi
        Method method = signature.getMethod();
        Parameter[] params = method.getParameters();
        Object[] originalArgs = pjp.getArgs();
        Annotation[][] paramAnnotations = method.getParameterAnnotations();

        System.out.println("METHOD PARAMS: " + Arrays.toString(params));
        System.out.println("METHOD ARGS (JP): " + Arrays.toString(originalArgs));

        int annotatedParamIdx = -1;
        RequestDTO requestDTO = null;

        for (int i = 0; i < paramAnnotations.length; i++) {
            for (int j = 0; j < paramAnnotations[i].length; j++) {
                if (paramAnnotations[i][j] instanceof RequestDTO) {
                    annotatedParamIdx = i;
                    requestDTO = (RequestDTO) paramAnnotations[i][j];
                    break;
                }
            }
        }
        assert annotatedParamIdx != -1;
        assert requestDTO != null;

        Object[] newArgs = new Object[originalArgs.length];


        for (int i = 0; i < params.length; i++) {
            if (i == annotatedParamIdx) {
                Method fromDtoMethod = getFromDtoMethod(params[i].getType());
                Object resultArg = fromDtoMethod.invoke(null,originalArgs[i]);
                newArgs[i] = resultArg;
            } else {
                newArgs[i] = originalArgs[i];
            }
        }

        return pjp.proceed(newArgs);

    }

    private Method getFromDtoMethod(Class<?> clazz) throws InvalidPropertiesFormatException {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(FromDTO.class))
                .findFirst().orElseThrow(() -> new InvalidPropertiesFormatException("No Method annotated with @FromDTO found"));
    }
}
