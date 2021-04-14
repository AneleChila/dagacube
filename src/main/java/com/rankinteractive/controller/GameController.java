package com.rankinteractive.controller;

import com.rankinteractive.exception.BadRequestException;
import com.rankinteractive.exception.InvalidRequestException;
import com.rankinteractive.exception.errors.ErrorCodes;
import com.rankinteractive.model.Transaction;
import com.rankinteractive.request.DeductionOrDepositRequest;
import com.rankinteractive.request.GetTransactionsRequest;
import com.rankinteractive.service.CommonService;
import com.rankinteractive.validator.AmountOperationValidator;
import com.rankinteractive.validator.GetTransactionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rankinteractive.exception.errors.ErrorCodes.INVALID_REQUEST;

/**
 * @author anelechila
 */
@RestController
@RequestMapping("/dagacube")
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private CommonService commonService;
    private GetTransactionValidator getTransactionValidator;
    private AmountOperationValidator amountOperationValidator;



    public GameController(CommonService commonService, GetTransactionValidator getTransactionValidator, AmountOperationValidator amountOperationValidator) {
        this.commonService = commonService;
        this.amountOperationValidator = amountOperationValidator;
        this.getTransactionValidator = getTransactionValidator;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public int getbalance(@PathVariable Long id) {

        try {
            return commonService.getBalance(id);

        } catch (Exception e) {
             throw new BadRequestException(ErrorCodes.GENERAL_SYSTEM_ERR.getResponseDesc());
        }
    }

    @RequestMapping(value = "/deduct", method = RequestMethod.POST)
    public boolean deductAmount(@RequestBody DeductionOrDepositRequest request,
                                                      BindingResult bindingResult) {

        amountOperationValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors())
            throw new InvalidRequestException(INVALID_REQUEST.getResponseDesc(), bindingResult);

        return commonService.deductMoney(request.getPlayerId(),
                request.getTransactionId(), request.getAmount(), request.getPromoCode());
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public boolean depositAmount(@RequestBody DeductionOrDepositRequest request,
                                BindingResult bindingResult) {

        amountOperationValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors())
            throw new InvalidRequestException(INVALID_REQUEST.getResponseDesc(), bindingResult);

        return commonService.depositMoney(request.getPlayerId(),
                request.getTransactionId(), request.getAmount());
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public List<Transaction> getTransctionsList(@RequestBody GetTransactionsRequest request,
                                                BindingResult bindingResult) {

        getTransactionValidator.validate(request, bindingResult);
        if (bindingResult.hasErrors())
            throw new InvalidRequestException(INVALID_REQUEST.getResponseDesc(), bindingResult);

        try {
            return commonService.getTransctionsList(request.getUsername(), request.getPassword());

        } catch (Exception e) {
            throw new BadRequestException(ErrorCodes.GENERAL_SYSTEM_ERR.getResponseDesc());
        }
    }
}