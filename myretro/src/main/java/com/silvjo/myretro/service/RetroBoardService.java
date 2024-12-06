package com.silvjo.myretro.service;

import com.silvjo.myretro.board.Card;
import com.silvjo.myretro.board.RetroBoard;
import com.silvjo.myretro.exception.CardNotFoundException;
import com.silvjo.myretro.exception.RetroBoardNotFoundException;
import com.silvjo.myretro.persistance.RepositoryRetroBoardInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RetroBoardService {

    RepositoryRetroBoardInterface<RetroBoard, UUID> repository;

    public RetroBoard save(RetroBoard domain){
        if (domain.getCards() == null)
            domain.setCards(new ArrayList<>());
        return this.repository.save(domain);
    }

    public RetroBoard findById(UUID id){
        Optional<RetroBoard> retroBoard = this.repository.findById(id);
        if (!retroBoard.isPresent()) {
            throw new RetroBoardNotFoundException();
        }
        return this.repository.findById(id).get();
    }

    public Iterable<RetroBoard> findAll(){
        return this.repository.findAll();
    }

    public Iterable<Card> findAllCardsFromRetroBoard(UUID id){
        return this.findById(id).getCards();
    }

    public void delete(UUID id){
        this.repository.deleteById(id);
    }

    public Card addCardToRetroBoard(UUID id, Card card){
        if (card.getId() == null) {
            card.setId(UUID.randomUUID());
        }else {
            RetroBoard retroBoard = this.findById(id);
            List<Card> cardList = new ArrayList<>(retroBoard.getCards());
            cardList.add(card);

            retroBoard.setCards(cardList);
        }
        return card;
    }

    public Card findCardByIdFromRetroBoard(UUID id, UUID uuidCard){
        RetroBoard retroBoard = this.findById(id);
        Optional<Card> card = retroBoard.getCards().stream().filter(card1 -> card1.getId().equals(uuidCard)).findFirst();
        if (card.isPresent()) {
            return card.get();
        }else {
            throw new CardNotFoundException();
        }
    }

    public void removeCardFromRetroBoard(UUID id, UUID cardUUID){
        RetroBoard retroBoard = this.findById(id);
        List<Card> cardList = new ArrayList<>(retroBoard.getCards());
        cardList.removeIf(card -> card.getId().equals(cardUUID));
        retroBoard.setCards(cardList);
//        if (retroBoard == null) {
//            throw new RetroBoardNotFoundException("RetroBoard not found");
//        }
//        List<Card> cards = new ArrayList<>(retroBoard.getCards());
//        boolean removed = cards.removeIf(card -> card.getId().equals(cardUUID));
//        if (!removed) {
//            throw new CardNotFoundException("Card not found");
//        }
//        retroBoard.setCards(cards);
//        repository.save(retroBoard);
    }
}
