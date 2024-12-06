package com.silvjo.myretro;

import com.silvjo.myretro.board.Card;
import com.silvjo.myretro.board.CardType;
import com.silvjo.myretro.board.RetroBoard;
import com.silvjo.myretro.exception.CardNotFoundException;
import com.silvjo.myretro.exception.RetroBoardNotFoundException;
import com.silvjo.myretro.service.RetroBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collection;
import java.util.UUID;

@SpringBootTest
class MyretroApplicationTests {

	@Autowired
	RetroBoardService service;

	UUID retroBoardUUID = UUID.fromString("9DC9B71B-A07E-418B-B972-40225449AFF2");
	UUID cardUUID = UUID.fromString("B2A80A5-A0F5-4180-A6DC-80C84BC014C");
	UUID mehCardUUID = UUID.fromString("75A3905-D6BE-49AB-A3C4-EBE287B51539");

	@Test
	void saveRetroBoardTest(){
		RetroBoard retroBoard = service.save(RetroBoard.builder()
				.name("Gathering 2024").build());
				assertThat(retroBoard).isNotNull();
				assertThat(retroBoard.getId()).isNotNull();
	}

	@Test
	void findAllRetroBoardsTest(){
		Iterable<RetroBoard> retroBoards = service.findAll();
		assertThat(retroBoards).isNotNull();
		assertThat(retroBoards).isNotEmpty();
	}

	@Test
	void cardsRetroBoardNotFoundTest(){
		assertThatThrownBy(() -> {
			service.findAllCardsFromRetroBoard(UUID.randomUUID());
		}).isInstanceOf(RetroBoardNotFoundException.class);
	}

	@Test
	void findRetroBoardTest(){
		RetroBoard retroBoard = service.findById(retroBoardUUID);
		assertThat(retroBoard).isNotNull();
		assertThat(retroBoard.getCards()).isNotEmpty();
	}

	@Test
	void addCardToRetroBoard(){
		Card card = service.addCardToRetroBoard(retroBoardUUID, Card.builder()
				.comment("Amazing Session!")
				.cardType(CardType.HAPPY)
				.build());
		assertThat(card).isNotNull();
		assertThat(card.getId()).isNotNull();

		RetroBoard retroBoard = service.findById(retroBoardUUID);
		assertThat(retroBoard).isNotNull();
		assertThat(retroBoard.getCards()).isNotEmpty();
	}

	@Test
	void findAllCardsFromRetroBoardTest(){
		Iterable<Card> cardList = service.findAllCardsFromRetroBoard(retroBoardUUID);
		assertThat(cardList).isNotEmpty();
		assertThat(((Collection) cardList).size()).isGreaterThanOrEqualTo(3);
	}

	@Test
	void removeCardsFromRetroBoardTest(){
		service.removeCardFromRetroBoard(retroBoardUUID, cardUUID);

		RetroBoard retroBoard = service.findById(retroBoardUUID);
		assertThat(retroBoard).isNotNull();
		assertThat(retroBoard.getCards()).isNotEmpty();
		assertThat(retroBoard.getCards()).hasSizeLessThan(4);
	}

	@Test
	void findCardByIdInRetroBoardTest(){
		Card card = service.findCardByIdFromRetroBoard(retroBoardUUID, mehCardUUID);
		assertThat(card).isNotNull();
		assertThat(card.getId()).isEqualTo(mehCardUUID);
	}

	@Test
	void notFoundCardInRetroBoardTest(){
		assertThatThrownBy(() -> {
			service.findCardByIdFromRetroBoard(retroBoardUUID,UUID.randomUUID());
		}).isInstanceOf(CardNotFoundException.class);
	}

}
