package com.robert.ddd.representation;

import java.util.List;

public class ATMClientRepresentation {

    public String atmClientId;
    public String userName;
    public String pin;
    public String branchId;
    public String branchName;
    public String creationDate;
    public String lastLogin;
    public String clientType;
    public List<AccountRepresentation> accounts;
}
