package com.github.thanglequoc.FruitVendingMachine;

public class NotEnoughBalanceException extends RuntimeException {
    
    @Override
    public String getMessage() {
        return "You don't have enough balance make this purchase";
    }

}
