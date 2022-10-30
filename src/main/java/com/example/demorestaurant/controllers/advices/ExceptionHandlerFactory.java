package com.example.demorestaurant.controllers.advices;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.entities.exceptions.UpchiapasException;
//import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.OperationNotSupportedException;

@ControllerAdvice
public class ExceptionHandlerFactory {
    /* EL PROFE LAS TENIA EN SU CLASE PERO LAS BORRO Y PUSO SOLO LAS QUE USAMOS
    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<BaseResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .status(HttpStatus.CONFLICT)
                .statusCode(409)
                .success(false)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<BaseResponse> handleResponseStatusException(ResponseStatusException exception) {
        return new ResponseEntity<>(BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .status(HttpStatus.NOT_FOUND)
                .statusCode(404)
                .success(false)
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<BaseResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<>(BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .status(HttpStatus.NOT_FOUND)
                .statusCode(404)
                .success(false)
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException exception) {
        return new ResponseEntity<>(BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .statusCode(500)
                .success(false)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredCodeException.class)
    private ResponseEntity<BaseResponse> handleExpiredCodeException(ExpiredCodeException exception) {
        return new ResponseEntity<>(BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .status(HttpStatus.PRECONDITION_FAILED)
                .statusCode(412)
                .success(false)
                .build(), HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(PreconditionFailedException.class)
    private ResponseEntity<BaseResponse> handlePreconditionFailedException(PreconditionFailedException exception) {
        return new ResponseEntity<>(BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .status(HttpStatus.PRECONDITION_FAILED)
                .statusCode(412)
                .success(false)
                .build(), HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(ForbiddenActionException.class)
    private ResponseEntity<BaseResponse> handleForbiddenActionException(ForbiddenActionException exception) {
        return new ResponseEntity<>(BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .status(HttpStatus.FORBIDDEN)
                .statusCode(403)
                .success(false)
                .build(), HttpStatus.FORBIDDEN);
    }*/
    //con el que se guio para hacer la de abajo
    //este se manada cuando los datos es una base de datos no son consistentes o algo que
    //no es correcto que esten haciendo con la base de datos
    /*
    este metodo es el mismo que se encuentra 2 metodos abajo pero con los del httpstatus diferente
    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<BaseResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                //aqui tambien no es necesario el Boolean.FALSE por que es lo mismo que false
                .success(false)
                //este pasa directamente el estatus xd pero es lo mismo
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }*/

    /*es una anotacion que maneja excepciones es decir que ese metodo solo se va a ejecutar cuando
    * caiga una de cierto tipo en este caso RunTimeException no olvidas que el parametro
    * del metodo debe de ser del mismo tipo del error que se esta manejando en este caso RunTimeException
    * este metodo lo que hace es crear un base response para las excepciones por lo que no se envia
    * data solamente message,success,httpstatus
    *
    * ESTE METODO SOLO SE VA A EJECUTAR CUANDO SUCEDA UNA EXCEPTION DE ESE TIPO EN ESTE CASO CUANDO
    * SE EJECUTO EN EL METODDO FROM DEL SERVICE
    * */

    //este tipo de exceptios se manda cuando se esta ejecutando tu codigo

    /*TODAS LA EXCEPTIONES HEREDAN DE RunTimeException
    * por lo que no es correcto usar RunTimeException por que si no all va a caer ahi
    * si quieres otra Exception borra el metodo que recibe un RunTimeException y crea otro
    * con la exceptcion especifica que quieras*/
    /*COMENTADA PARA QUE NO PASE SIEMPRE A ESTE OWO
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException exception) {
        BaseResponse errorResponse = BaseResponse.builder()
                //el message se lo paso en el metodo from del service
                .message(exception.getLocalizedMessage())
                //como es una exception debe de regresar un false
                .success(Boolean.FALSE)
                //el estatus puede cambiar dependiendo la necesidad o lo que tu quieras mandar owo
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }*/
    //este se manada cuando los datos es una base de datos no son consistentes o algo que
    //no es correcto que esten haciendo con la base de datos
    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<BaseResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
    //exception cual algo no esta programado todavia
    @ExceptionHandler(OperationNotSupportedException.class)
    private ResponseEntity<BaseResponse> handleOperationNotSupportedException(OperationNotSupportedException exception) {
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.CONFLICT)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
    /*esta es una exception personal es decir de las que tu creas */
    @ExceptionHandler(UpchiapasException.class)
    private ResponseEntity<BaseResponse> handleUpchiapasException(UpchiapasException exception) {
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<BaseResponse> handleCeoLoginException(NotFoundException exception) {
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}