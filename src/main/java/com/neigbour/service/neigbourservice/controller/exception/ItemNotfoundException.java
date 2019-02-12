package com.neigbour.service.neigbourservice.controller.exception;

public class ItemNotfoundException extends RuntimeException{


    public ItemNotfoundException(Long id){
        super("Cound not find item with id " + id);
    }


}
