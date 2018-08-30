package com.robert.ddd.administration;

import com.robert.ddd.DDD;
import com.robert.ddd.ObjectNotFoundException;
import com.robert.ddd.atm.*;
import com.robert.ddd.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@DDD.ApplicationService
@Service
@Transactional
public class ChangeATMClientPin {

    private ATMClients atmClients;

    public ChangeATMClientPin(ATMClients atmClients) {
        this.atmClients = atmClients;
    }

    public ATMClient changeATMClientPin(ATMClientId atmClientId, int newPin) {
        ATMClient actualClient = atmClients.get(atmClientId).orElseThrow(ObjectNotFoundException::new);

        actualClient.updateIdentificationInformation(
                // new IdentificationInformationBuilder(actualClient.getIdentificationInformation).withClient(
                new IdentificationInformation(
                        // new ClientBuilder(client).withPin(newPin).build()
                        new Client(
                                actualClient.getIdentificationInformation().getClient().getUserName(),
                                newPin
                        ),
                        actualClient.getIdentificationInformation().getBankBranch()
                )
        );

        actualClient.updateGeneralDetails(
                new GeneralDetails(
                        actualClient.getGeneralDetails().getAccountCreationDate(),
                        LocalDateTime.now(),
                        actualClient.getGeneralDetails().getClientType()
                )
        );
        return atmClients.add(actualClient);
    }
}
