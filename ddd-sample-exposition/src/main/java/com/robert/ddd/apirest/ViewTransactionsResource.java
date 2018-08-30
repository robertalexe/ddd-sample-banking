package com.robert.ddd.apirest;

import com.robert.ddd.account.AccountId;
import com.robert.ddd.atm.ATMClientId;
import com.robert.ddd.representation.DomainToRepresentationMapper;
import com.robert.ddd.representation.TransactionRepresentation;
import com.robert.ddd.transaction.ViewTransactionsForAccount;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ViewTransactionsResource {

    @Autowired
    private ViewTransactionsForAccount viewTransactionsForAccount;

    @RequestMapping(value = "/view/transactions/{atmClientId}/{accountId}", method = RequestMethod.GET)
    @ApiOperation(value = "Consults the transactions for a specific account. Input: userId, accountId. Example: 503587/A2435643")
    public List<TransactionRepresentation> consultClient(@PathVariable(value = "atmClientId") String atmClientId, @PathVariable(value = "accountId") String accountId) {
        return DomainToRepresentationMapper.parseTransactions(viewTransactionsForAccount.viewTransactions(new AccountId(new ATMClientId(atmClientId), accountId)));
    }
}
