package com.robert.ddd.transaction;

import com.robert.ddd.DDD;

@DDD.ValueObject
public enum TransactionType {
    WITHDRAW, DEPOSIT, TRANSFER
}
