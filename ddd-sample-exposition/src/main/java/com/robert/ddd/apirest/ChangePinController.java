package com.robert.ddd.apirest;

import com.robert.ddd.administration.ChangeATMClientPin;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClientId;
import com.robert.ddd.representation.ATMClientRepresentation;
import com.robert.ddd.representation.DomainToRepresentationMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ChangePinController {

    @Autowired
    private ChangeATMClientPin changePin;

    @RequestMapping(value = "/changepin/{atmClientId}/{newPin}", method = RequestMethod.PUT)
    @ApiOperation(value = "Changes the ATM Client's PIN. Input: userId. Hardcoded users: 503587, 503588")
    public ATMClientRepresentation changePin(@PathVariable String atmClientId, @PathVariable Integer newPin) {
        ATMClient atmClient = changePin.changeATMClientPin(new ATMClientId(atmClientId), newPin);
        return DomainToRepresentationMapper.toRepresentation(atmClient);
    }


}
