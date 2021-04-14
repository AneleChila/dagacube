package com.rankinteractive.service;

import com.rankinteractive.config.DagacubeLogger;
import com.rankinteractive.exception.BadRequestException;
import com.rankinteractive.exception.errors.ErrorCodes;
import com.rankinteractive.model.Player;
import com.rankinteractive.model.Promotion;
import com.rankinteractive.model.Transaction;
import com.rankinteractive.model.TransactionTypeEnum;
import com.rankinteractive.repository.PlayerRepository;
import com.rankinteractive.repository.PromotionRepository;
import com.rankinteractive.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommonService {


    private PlayerRepository playerRepository;
    private TransactionRepository transactionRepository;
    private PromotionRepository promotionRepository;


    @Autowired
    public CommonService(PromotionRepository promotionRepository, PlayerRepository playerRepository,
                         TransactionRepository transactionRepository) {
        this.promotionRepository = promotionRepository;
        this.playerRepository = playerRepository;
        this.transactionRepository = transactionRepository;
    }

    public CommonService() {}

    @DagacubeLogger
    public int getBalance(Long playerId) {

        Optional<Player> optionalPlayer = getPlayer(playerId);

        if(optionalPlayer.isPresent()) {
            return optionalPlayer.get().getBalanceAmount();
        }

        throw new BadRequestException(ErrorCodes.AUTHENTICATION_FAILED.getResponseDesc());

    }

    private Optional<Player> getPlayer(Long playerId) {
        Optional<Player> optionalPlayer;
        try {
            optionalPlayer = playerRepository.findById(playerId);
        } catch (Exception e) {
            throw new BadRequestException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
        return optionalPlayer;
    }

    @DagacubeLogger
    public boolean deductMoney(Long playerId, Long transactionId, int amountPlayer, String promoCode) {

        checkDuplicate(transactionId);

        Optional<Player> optionalPlayer = getPlayer(playerId);

        if(optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();

            refreshPromoIfNewPromoCode(promoCode);

            Promotion promotion = promotionRepository.findByActivity(true);

            if(promotion == null || promotion.getAvailableWagers() <= 0) {
                int amount = player.getBalanceAmount() - amountPlayer;

                if (amount < 0) {
                    throw new BadRequestException("teapot(418)");
                }

                player.setBalanceAmount(amount);
                player.setLastModifiedDate(new Date());
                savePlayer(player);
            } else {
                promotion.setAvailableWagers(promotion.getAvailableWagers() - 1 );
                promotion.setLastModifiedDate(new Date());
                promotionRepository.save(promotion);
            }

            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionTypeEnum.WAGERING.getType());
            transaction.setPlayerId(player.getId());
            transaction.setTransactionId(transactionId);
            transaction.setCreateDate(new Date());
            transaction.setLastModifiedDate(new Date());

            saveTransaction(transaction);

        }

        return true;

    }

    private void refreshPromoIfNewPromoCode(String promoCode) {
        Promotion promotion;
        if(promoCode != null && promoCode.equals("paper")) {

            promotion = promotionRepository.findByActivity(true);
            promotion.setAvailableWagers(5);

            promotionRepository.save(promotion);
        }
    }

    @DagacubeLogger
    public boolean depositMoney(Long playerId, Long transactionId, int amountPlayer) {

        checkDuplicate(transactionId);

        Optional<Player> optionalPlayer = getPlayer(playerId);

        if(optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            int amount = player.getBalanceAmount() + amountPlayer;

            player.setBalanceAmount(amount);
            player.setLastModifiedDate(new Date());
            savePlayer(player);

            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionTypeEnum.WAGERING.getType());
            transaction.setPlayerId(player.getId());
            transaction.setTransactionId(transactionId);
            transaction.setCreateDate(new Date());
            transaction.setLastModifiedDate(new Date());

            saveTransaction(transaction);

        }

        return true;

    }

    @DagacubeLogger
    public List<Transaction> getTransctionsList(String username, String password) {
         Player player = playerRepository.findByCredentials(username,password);

         if(player == null)
             throw new BadRequestException(ErrorCodes.AUTHENTICATION_FAILED.getResponseDesc());

         List<Transaction> transactions = transactionRepository.findByPlayerId(player.getId());

         if(transactions.size() < 10)
             return transactions;

         return transactions.stream().limit(10).collect(Collectors.toList());

    }

    private void saveTransaction(Transaction transaction) {
        try{
            transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new BadRequestException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    private void savePlayer(Player player) {
        try{
            playerRepository.save(player);
        } catch (Exception e) {
            throw new BadRequestException(ErrorCodes.GENERAL_DATABASE_ERR.getResponseDesc());
        }
    }

    private void checkDuplicate(Long transactionId) {
        Transaction transaction = transactionRepository.findByTransactionId(transactionId);

        if(transaction != null) {
            throw new BadRequestException(ErrorCodes.DUPLICATE_FIELD.getResponseDesc());
        }
    }


}
