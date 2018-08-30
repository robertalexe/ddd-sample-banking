package com.robert.ddd.representation;

import java.util.List;

public class AccountRepresentation {

    public String uniqueId;
    public String iban;
    public String currency;
    public String balance;
    public List<TransactionRepresentation> transactions;
}
