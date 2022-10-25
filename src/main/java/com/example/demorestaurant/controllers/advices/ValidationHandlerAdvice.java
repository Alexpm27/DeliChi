package com.example.demorestaurant.controllers.advices;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/*ESTO SE VA A TIRAR CUANDO OCURRA UN ERROR NO IMPORTA EN QUE CONTROLLADOR SEA
* ES DECIR QUE CUALQUIER ERROR DE VALIDACION QUE EXITA EN UN CONTROLLADOR SI NO PASA LA VALIDACION VA A CAER ACA
* Y VA A DEVOLVER LOS ERRORES TAL COMO EN EL BASE RESPONSE PARA CONTROLLADOR*/

/*gracias al la notackion controlleradvice que es una notacion que se le impone a una clase
* que se va a anejar cualquier tipo de error que el controllador tire es decir si
* el controllador CommentController tiene algun error por ejemplo status 400 el controlleradvice
* va a ejecutarse de manera automatica es algo interno de Spring
* Porlo tanto toda aquella clase anota con controlleradvice va a ejecutarse cada vez que suceda algun
* tipo de error*/
@ControllerAdvice
public class ValidationHandlerAdvice extends ResponseEntityExceptionHandler {

    /*Es un metodo que se va a EJECUTAR CADA VEZ que una validacion no pase
    * cada que una vlidacion NO PASA nosotros siempre vamos a devolver un badrequest*/
    /*EL DEL TRABAJO DEL PROFE ESTABA ASI
    * este metodo va a limpiar All lo que obtuvimos y de esa manera lo unico que va a retornar
    * es cual es el es atributo que fallo y el mensaje con el error para que no tiene el error all feo y cada que haya un
    * error se va a tirar uno de este tipo BAD_REQUEST
    * un ejemplo es la email valida y la password vacia dond te mandara solo la password y el message que no debe de
    * estar vacio y viceversa el email va a decir que debe de ser de un formato valido
    * @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }*/
    /*con este metodo ahora retornas un baseresponse en casi de que sea un bad request y te mostrara
    * los atributos que fallaron y el message de por que fallaron, si los datos son correctos
    * se retornara un base response pero con los datos que son correctos y sera un estatus http diferente
    * en este caso de OK*/
    /*EXPLICACION DE LO QUE TIENE EL METODO
    * el primer para metro que recibe es MethodArgumentNotValidException es decir que ese metodo solo se va a
    * ejecutar cuando se tire una excepcion de ese tipo, es queire decir que la clase esta a la escucha de que
    * una excepcion suceda si el error sucese y es de MethodArgumentNotValidException este metodo se ejecuta
    * POR ESO MISMO ESA CLASE EXTIENDE DE ResponseEntityExceptionHandler dentro de esa clase existe el metodo
    * handleMethodArgumentNotValid por eso el @Override por que se sobre escribe ese metodo y nosotros somos
    * los que marcamos cuando algo no es validp
    *
    * ex es el error que esta sucediendo en ese momento que es el error de validacion que esta
    * sucediendo en ese momento que no paso contienen adentro un emtodo llamado getBindingResult()
    * es como todos los resultados de las validaciones que no pasaron y este contiene un getAllErrors()
    * que es una lista de objetos de tipo ObjectError y el profe paso por cada uno de ellos con el
    * metodo forEach() le paso la varible error -> {} y ya con eso paso por todos los errores de validacion
    * que esta sucediendo error contiene el nombre de cada uno de los elementos que marco error, para poder
    * obtenerlo hizo algo como un truco que que se llama tas que es como transformar un objeto a otro de manera
    * muy rapida ((FieldError) error) es decir que quiere obtener el campo de error que sucedio en ese momento
    * pone .getField() que viene del objeto FieldError que ese metodo se obtiene apartir de una clasa hija llamada
    * FieldError que extiende de ObjectError es decir que transforma el error en un objeto FieldError y es
    * correcto por que FieldError es una clase hija de ObjectError y ahi esta el objeto error y asi accedes al
    * metodo .getField() el cuale es el nombre del campo que marco error al momento de la validacion ejemplo email
    * = content y asi eso ya lo puedes transformar en un string fieldName y ahi guardas o cualquier campo o
    * atrbuto que no haya sido validado correctamente, despues el mensaje de error se obtiene del mismos error
    * mediante el metodo .getDefaultMessage() y esto devuelve el error de la validacion el que decia por ejemplo
    * "debe de ser un email valido o con formato correcto" esot se guarda en la variable String message y despues
    * mediante una estructura Map es como una estructura de datos que guarda informacion atravez de un llave y un valor
    * y asi mediante el errors.put(fieldName, message) te deja por el metodo put poner la llave y el valor y
    * ahora lo que devuelve es una estrutura Map que devuelve cual fue el campo que fallo y porque fallo
    * al final si sucede un error ese map errors se llena y ese map es el que se devuelve en el BaseResponse en el data
    * con sus valores de los demas atributos*/
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        /*map es una estructura de datos es como una tabla de 2 columnas
        * donde una es la llave y el otro es el valor*/
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        BaseResponse baseResponse = BaseResponse.builder()
                .data(errors)
                .message("Validation failed")
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.BAD_REQUEST).build();

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
