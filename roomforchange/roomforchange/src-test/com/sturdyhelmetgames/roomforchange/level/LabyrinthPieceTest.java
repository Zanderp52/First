package com.sturdyhelmetgames.roomforchange.level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mockito;

import com.sturdyhelmetgames.roomforchange.level.Level.LevelTileType;

public class LabyrinthPieceTest {

	@Test
	public void testCreateLabyrinthPiece() {
		final LevelTileType[][] tileTypes = new LevelTileType[PieceTemplate.WIDTH][PieceTemplate.HEIGHT];
		for (int x = 0; x < PieceTemplate.WIDTH; x++) {
			for (int y = 0; y < PieceTemplate.HEIGHT; y++) {
				tileTypes[x][y] = LevelTileType.GROUND;
			}
		}

		PieceTemplate pieceTemplate = Mockito.mock(PieceTemplate.class);
		RoomObjectTemplate roomObjectTemplate = Mockito
				.mock(RoomObjectTemplate.class);
		Mockito.when(pieceTemplate.getTileTypes()).thenReturn(tileTypes);

		LabyrinthPiece piece = new LabyrinthPiece(pieceTemplate,
				roomObjectTemplate);
		assertNotNull(piece);
		assertEquals(PieceTemplate.WIDTH, piece.getTiles().length);
		assertEquals(PieceTemplate.HEIGHT, piece.getTiles()[0].length);
		for (int x = 0; x < PieceTemplate.WIDTH; x++) {
			for (int y = 0; y < PieceTemplate.HEIGHT; y++) {
				assertEquals(LevelTileType.GROUND, piece.getTiles()[x][y].type);
			}
		}

		Mockito.verify(pieceTemplate);
		// Mockito.verify(roomObjectTemplate);

	}
}