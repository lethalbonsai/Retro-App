package com.silvjo.myretro.persistance;

import com.silvjo.myretro.board.Card;
import com.silvjo.myretro.board.CardType;
import com.silvjo.myretro.board.RetroBoard;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RetroBoardRepository implements RepositoryRetroBoardInterface<RetroBoard, UUID> {

    private Map<UUID, RetroBoard> retroBoardMap = new HashMap<>() {{
        put(UUID.fromString("9DC9B71B-A07E-418B-B972-40225449AFF2"),
                RetroBoard.builder()
                        .id(UUID.fromString("9DC9B71B-A07E-418B-B972-40225449AFF2"))
                        .name("Spring Boot 3.0 Meeting")
                        .card(Card.builder()
                                .id(UUID.fromString("B2A80A5-A0F5-4180-A6DC-80C84BC014C"))
                                .comment("Happy to meet the team")
                                .cardType(CardType.HAPPY)
                                .build())
                        .card(Card.builder()
                                .id(UUID.fromString("011EF086-7645-4534-9512-B9BC4CCFB688"))
                                .comment("New projects")
                                .cardType(CardType.HAPPY)
                                .build())
                        .card(Card.builder()
                                .id(UUID.fromString("75A3905-D6BE-49AB-A3C4-EBE287B51539"))
                                .comment("When to meet again??")
                                .cardType(CardType.MEH)
                                .build())
                        .card(Card.builder()
                                .id(UUID.fromString("896C093D-1C50-49A3-A58A-6F1008789632"))
                                .comment("We need more time to finish")
                                .cardType(CardType.SAD)
                                .build())
                        .build()
        );
    }};

    @Override
    public RetroBoard save(RetroBoard domain) {
        if (domain.getId() == null) {
            domain.setId(UUID.randomUUID());
        }
        this.retroBoardMap.put(domain.getId(), domain);
        return domain;
    }

    @Override
    public Optional<RetroBoard> findById(UUID uuid) {
        return Optional.ofNullable(this.retroBoardMap.get(uuid));
    }

    @Override
    public Iterable<RetroBoard> findAll() {
        return this.retroBoardMap.values();
    }

    @Override
    public void deleteById(UUID uuid) {
        this.retroBoardMap.remove(uuid);
    }
}