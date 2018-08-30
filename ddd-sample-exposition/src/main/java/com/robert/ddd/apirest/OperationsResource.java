package com.robert.ddd.apirest;

import com.robert.ddd.account.AccountId;
import com.robert.ddd.atm.ATMClient;
import com.robert.ddd.atm.ATMClientId;
import com.robert.ddd.deposit.DepositInAccount;
import com.robert.ddd.representation.ATMClientRepresentation;
import com.robert.ddd.representation.DomainToRepresentationMapper;
import com.robert.ddd.transfer.TransferBetweenAccounts;
import com.robert.ddd.withdraw.WithdrawFromAccount;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
public class OperationsResource {

    @Autowired
    private DepositInAccount depositInAccount;
    @Autowired
    private WithdrawFromAccount withdrawFromAccount;
    @Autowired
    private TransferBetweenAccounts transferBetweenAccounts;

    @RequestMapping(value = "/operations/deposit/{atmClientId}/{accountId}/{amount}", method = RequestMethod.PUT)
    @ApiOperation(value = "Deposit some money$$$. Input: userId, accountId and amount. Example: 503587/A2435643/400")
    public ATMClientRepresentation deposit(@PathVariable(value = "atmClientId") String atmClientId, @PathVariable(value = "accountId") String accountId, @PathVariable(value = "amount") String amount) {
        ATMClient atmClient = depositInAccount.depositMoneyInAccount(new AccountId(new ATMClientId(atmClientId), accountId), new BigDecimal(amount));
        return DomainToRepresentationMapper.toRepresentation(atmClient);
    }

    @RequestMapping(value = "/operations/withdraw/{atmClientId}/{accountId}/{amount}", method = RequestMethod.PUT)
    @ApiOperation(value = "Withdraws some money$$$. Input: userId, accountId and amount. Example: 503587/A2435643/400")
    public ATMClientRepresentation withdraw(@PathVariable(value = "atmClientId") String atmClientId, @PathVariable(value = "accountId") String accountId, @PathVariable(value = "amount") String amount) {
        ATMClient atmClient = withdrawFromAccount.withdrawFromAccount(new AccountId(new ATMClientId(atmClientId), accountId), new BigDecimal(amount));
        return DomainToRepresentationMapper.toRepresentation(atmClient);
    }

    @RequestMapping(value = "/operations/transfer/{atmClientId1}/{accountId1}/{atmClientId2}/{accountId2}/{amount}", method = RequestMethod.PUT)
    @ApiOperation(value = "Transfer some money$$$. Input: userId-Source, accountId-Source, amount, userId-Destination, accountId-Destination. Example: 503587/A2435643/400/503587/A2435645")
    public ATMClientRepresentation transfer(@PathVariable(value = "atmClientId1") String atmClientId1, @PathVariable(value = "accountId1") String accountId1, @PathVariable(value = "amount") String amount,
                           @PathVariable(value = "atmClientId2") String atmClientId2, @PathVariable(value = "accountId2") String accountId2) {
        return DomainToRepresentationMapper.toRepresentation(transferBetweenAccounts.transferMoneyBetweenAccounts(
                new AccountId(new ATMClientId(atmClientId1), accountId1),
                new AccountId(new ATMClientId(atmClientId2), accountId2), new BigDecimal(amount)
            )
        );
    }
}
