package com.silvjo.myretro.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Card {

    private UUID id;

    @NotNull
    @NotBlank(message = "A comment must be provided")
    private String comment;

    @NotNull(message = "A type HAPPY|MEH|SAD must be provided")
    private CardType cardType;

}
