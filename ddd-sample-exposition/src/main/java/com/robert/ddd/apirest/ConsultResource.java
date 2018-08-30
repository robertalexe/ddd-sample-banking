package com.robert.ddd.apirest;

import com.robert.ddd.account.AccountId;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClientId;
import com.robert.ddd.consult.ConsultAccount;
import com.robert.ddd.consult.ConsultAtmClient;
import com.robert.ddd.representation.ATMClientRepresentation;
import com.robert.ddd.representation.AccountRepresentation;
import com.robert.ddd.representation.DomainToRepresentationMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ConsultResource {

    @Autowired
    private ConsultAccount consultAccount;
    @Autowired
    private ConsultAtmClient consultClient;

    @RequestMapping(value = "/consult/client/{atmClientId}", method = RequestMethod.GET)
    @ApiOperation(value = "Consult the ATM Client. Input: userId. Hardcoded users: 503587, 503588")
    public ATMClientRepresentation consultClient(@PathVariable(value = "atmClientId") String atmClientId) {
        ATMClient atmClient = consultClient.viewClient(new ATMClientId(atmClientId));
        return DomainToRepresentationMapper.toRepresentation(atmClient);
    }

    @RequestMapping(value = "/consult/account/{atmClientId}/{accountId}", method = RequestMethod.GET)
    @ApiOperation(value = "Changes the ATM Client's PIN. Input: userId and accountId. Example: 503587/A2435643 or 503587/A2435645 or 503588/A2435644")
    public AccountRepresentation consultAccount(@PathVariable(value = "atmClientId") String atmClientId, @PathVariable(value = "accountId") String accountId) {
        return DomainToRepresentationMapper.parseAccount(consultAccount.viewAccount(new AccountId(new ATMClientId(atmClientId), accountId)));
    }


}
