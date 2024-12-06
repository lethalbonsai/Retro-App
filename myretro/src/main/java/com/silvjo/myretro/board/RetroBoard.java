package com.silvjo.myretro.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.List;
import java.util.UUID;


@Data
@Builder
public class RetroBoard {

    private UUID id;

    @NotNull
    @NotBlank(message = "A name must be provided")
    private String name;

    @Singular
    private List<Card> cards;
}
