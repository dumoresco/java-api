package br.com.forttiori.mongodb.v1.exceptions;


// Extends RuntimeException -> execção que não necessariamente voce precisa tratar, obrigado a usar o try
public class EntityNotFoundException extends RuntimeException{

    // Contrutor que recebe a mensagem
    public EntityNotFoundException(String message){
        super(message);
    }

}
