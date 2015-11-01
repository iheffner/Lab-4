package poker.app.view;

import java.util.ArrayList;

import enums.eGame;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import poker.app.MainApp;
import pokerBase.Card;
import pokerBase.Deck;
import pokerBase.GamePlay;
import pokerBase.GamePlayPlayerHand;
import pokerBase.Hand;
import pokerBase.Player;
import pokerBase.Rule;

public class PokerTableController {

	boolean bP1Sit = false;
	boolean bP2Sit = false;
	boolean bP3Sit = false;
	boolean bP4Sit = false;

	// Reference to the main application.
	private MainApp mainApp;
	private GamePlay gme = null;
	private int iCardDrawn = 0;
	private int communityCardDrawn = 0;
	private int NbrCards = 0;
	private int NbrCommunityCards = 0;

	public ArrayList<GamePlayPlayerHand> handsInPlay = new ArrayList<GamePlayPlayerHand>();
	
	@FXML
	public AnchorPane APMainScreen;

	@FXML
	public HBox HboxCommonArea;

	@FXML
	public HBox HboxCommunityCards;

	//Player 1
	@FXML
	public HBox h1P1;

	@FXML
	public TextField txtP1Name;

	@FXML
	public Label lblP1Name;

	@FXML
	public ToggleButton btnP1SitLeave;

	//Player 2
	@FXML
	public VBox v2P2;

	@FXML
	public TextField txtP2Name;

	@FXML
	public Label lblP2Name;

	@FXML
	public ToggleButton btnP2SitLeave;

	//Player 3
	@FXML
	public HBox h3P3;

	@FXML
	public TextField txtP3Name;

	@FXML
	public Label lblP3Name;

	@FXML
	public ToggleButton btnP3SitLeave;

	//Player 4
	@FXML
	public VBox v4P4;

	@FXML
	public TextField txtP4Name;

	@FXML
	public Label lblP4Name;

	@FXML
	public ToggleButton btnP4SitLeave;

	@FXML
	public Button btnDraw;

	@FXML
	public Button btnPlay;

	@FXML
	public Button btnDrawCenter;
	
	@FXML
	public Label winnerAnnouncement;

	public PokerTableController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

	@FXML
	private void handleP1SitLeave() {

		int iPlayerPosition = 1;

		if (bP1Sit == false) {
			Player p = new Player(txtP1Name.getText(), iPlayerPosition);
			mainApp.AddPlayerToTable(p);
			lblP1Name.setText(txtP1Name.getText());
			lblP1Name.setVisible(true);
			btnP1SitLeave.setText("Leave");
			txtP1Name.setVisible(false);
			bP1Sit = true;
		} else {
			mainApp.RemovePlayerFromTable(iPlayerPosition);
			btnP1SitLeave.setText("Sit");
			txtP1Name.setVisible(true);
			lblP1Name.setVisible(false);
			bP1Sit = false;
		}

	}

	@FXML
	private void handleP2SitLeave() {

		int iPlayerPosition = 2;

		if (bP2Sit == false) {
			Player p = new Player(txtP2Name.getText(), iPlayerPosition);
			mainApp.AddPlayerToTable(p);
			lblP2Name.setText(txtP2Name.getText());
			lblP2Name.setVisible(true);
			btnP2SitLeave.setText("Leave");
			txtP2Name.setVisible(false);
			bP2Sit = true;
		} else {
			mainApp.RemovePlayerFromTable(iPlayerPosition);
			btnP2SitLeave.setText("Sit");
			txtP2Name.setVisible(true);
			lblP2Name.setVisible(false);
			bP2Sit = false;
		}

	}

	@FXML
	private void handleP3SitLeave() {

		int iPlayerPosition = 3;

		if (bP3Sit == false) {
			Player p = new Player(txtP3Name.getText(), iPlayerPosition);
			mainApp.AddPlayerToTable(p);
			lblP3Name.setText(txtP3Name.getText());
			lblP3Name.setVisible(true);
			btnP3SitLeave.setText("Leave");
			txtP3Name.setVisible(false);
			bP3Sit = true;
		} else {
			mainApp.RemovePlayerFromTable(iPlayerPosition);
			btnP3SitLeave.setText("Sit");
			txtP3Name.setVisible(true);
			lblP3Name.setVisible(false);
			bP3Sit = false;
		}
	}
	
	@FXML
	private void handleP4SitLeave() {

		int iPlayerPosition = 4;

		if (bP4Sit == false) {
			Player p = new Player(txtP4Name.getText(), iPlayerPosition);
			mainApp.AddPlayerToTable(p);
			lblP4Name.setText(txtP4Name.getText());
			lblP4Name.setVisible(true);
			btnP4SitLeave.setText("Leave");
			txtP4Name.setVisible(false);
			bP4Sit = true;
		} else {
			mainApp.RemovePlayerFromTable(iPlayerPosition);
			btnP4SitLeave.setText("Sit");
			txtP4Name.setVisible(true);
			lblP4Name.setVisible(false);
			bP4Sit = false;
		}
	}

	@FXML
	private void handleDraw() {
		iCardDrawn++;

		// Draw a card for each player seated
		for (Player p : mainApp.GetSeatedPlayers()) {
			Card c = gme.getGameDeck().drawFromDeck();

			//Player 1
			if (p.getiPlayerPosition() == 1) {
				GamePlayPlayerHand GPPH = gme.FindPlayerGame(gme, p);
				GPPH.addCardToHand(c);
				String strCard = "/res/img/" + c.getCardImg();
				ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));

				ImageView i = (ImageView) h1P1.getChildren().get(iCardDrawn - 1);
				ImageView iCardFaceDown = (ImageView) HboxCommonArea.getChildren().get(0);
				final ParallelTransition transitionForward = createTransition(i, iCardFaceDown,
						new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));

				transitionForward.play();
				// h1P1.getChildren().add(img);

				if (iCardDrawn == NbrCards) {
					GPPH.getHand().EvalHand();
					handsInPlay.add(GPPH);
				}
			}

			//Player 2
			if (p.getiPlayerPosition() == 2) {
				GamePlayPlayerHand GPPH = gme.FindPlayerGame(gme, p);
				GPPH.addCardToHand(c);
				String strCard = "/res/img/" + c.getCardImg();
				ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));

				ImageView i = (ImageView) v2P2.getChildren().get(iCardDrawn - 1);
				ImageView iCardFaceDown = (ImageView) HboxCommonArea.getChildren().get(0);
				final ParallelTransition transitionForward = createTransition(i, iCardFaceDown,
						new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));

				transitionForward.play();
				// h1P1.getChildren().add(img);

				if (iCardDrawn == NbrCards) {
					GPPH.getHand().EvalHand();
					handsInPlay.add(GPPH);
				}
			}

			//Player 3
			if (p.getiPlayerPosition() == 3) {
				GamePlayPlayerHand GPPH = gme.FindPlayerGame(gme, p);
				GPPH.addCardToHand(c);
				String strCard = "/res/img/" + c.getCardImg();
				ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));

				ImageView i = (ImageView) h3P3.getChildren().get(iCardDrawn - 1);
				ImageView iCardFaceDown = (ImageView) HboxCommonArea.getChildren().get(0);
				final ParallelTransition transitionForward = createTransition(i, iCardFaceDown,
						new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));

				transitionForward.play();
				// h1P1.getChildren().add(img);

				if (iCardDrawn == NbrCards) {
					GPPH.getHand().EvalHand();
					this.handsInPlay.add(GPPH);
				}
			}
			
			//Player 4
			if (p.getiPlayerPosition() == 4) {
				GamePlayPlayerHand GPPH = gme.FindPlayerGame(gme, p);
				GPPH.addCardToHand(c);
				String strCard = "/res/img/" + c.getCardImg();
				ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));

				ImageView i = (ImageView) v4P4.getChildren().get(iCardDrawn - 1);
				ImageView iCardFaceDown = (ImageView) HboxCommonArea.getChildren().get(0);
				final ParallelTransition transitionForward = createTransition(i, iCardFaceDown,
						new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));

				transitionForward.play();
				// h1P1.getChildren().add(img);

				if (iCardDrawn == NbrCards) {
					GPPH.getHand().EvalHand();
					handsInPlay.add(GPPH);
				}
			}
			
		}

		if (iCardDrawn == NbrCards) {
			btnDraw.setVisible(false);
			
			if (NbrCommunityCards != 0) {
				btnDrawCenter.setVisible(true);
			}
		}

	}

	@FXML
	private void handleDrawToCommunity() {
		communityCardDrawn++;

		Card c = gme.getGameDeck().drawFromDeck();
		String strCard = "/res/img/" + c.getCardImg();
		//ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
		//HboxCommunityCards.getChildren().add(img);

		ImageView i = (ImageView) HboxCommunityCards.getChildren().get(communityCardDrawn - 1);
		ImageView iCardFaceDown = (ImageView) HboxCommonArea.getChildren().get(0);
		final ParallelTransition transitionForward = createTransition(i, iCardFaceDown,
				new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));

		transitionForward.play();

		if (communityCardDrawn == NbrCommunityCards) {
			btnDrawCenter.setVisible(false);
		}

	}

	@FXML
	private void handlePlay() {
		// Clear all players hands
		HboxCommonArea.getChildren().clear();
		HboxCommunityCards.getChildren().clear();
		h1P1.getChildren().clear();
		v2P2.getChildren().clear();
		h3P3.getChildren().clear();
		v4P4.getChildren().clear();
		handsInPlay.clear();
		winnerAnnouncement.setText("");

		//Correct the spacing of cards on the East and West sides
		h1P1.setSpacing(-30);
		v2P2.setSpacing(-45);
		h3P3.setSpacing(-30);
		v4P4.setSpacing(-45);
		
		// Get the Rule, start the Game
		Rule rle = new Rule(eGame.FiveStud);
		gme = new GamePlay(rle);

		NbrCards = gme.getNbrOfCards();
		NbrCommunityCards = gme.getNbrCommunityCards();

		//For purpose of testing when the game is set to Five Card Stud:
		NbrCommunityCards = 5;

		// Add the seated players to the game
		for (Player p : mainApp.GetSeatedPlayers()) {
			gme.addPlayerToGame(p);
			GamePlayPlayerHand GPPH = new GamePlayPlayerHand();
			GPPH.setGame(gme);
			GPPH.setPlayer(p);
			GPPH.setHand(new Hand());
			gme.addGamePlayPlayerHand(GPPH);
		}

		// Add a deck to the game
		gme.setGameDeck(new Deck());

		btnDraw.setVisible(true);
		btnDrawCenter.setVisible(false);
		iCardDrawn = 0;
		communityCardDrawn = 0;

		String strCard = "/res/img/b1fv.png";

		if (bP1Sit == true) {
			for (int i = 0; i < gme.getNbrOfCards(); i++) {
				ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
				h1P1.getChildren().add(img);
			}
		}

		if (bP2Sit == true) {
			for (int i = 0; i < gme.getNbrOfCards(); i++) {
				ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
				img.setRotate(90);
				v2P2.getChildren().add(img);
			}
		}
		
		if (bP3Sit == true) {
			for (int i = 0; i < gme.getNbrOfCards(); i++) {
				ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
				
				h3P3.getChildren().add(img);
			}
		}
		
		if (bP4Sit == true) {
			for (int i = 0; i < gme.getNbrOfCards(); i++) {
				ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
				img.setRotate(-90);
				v4P4.getChildren().add(img);
			}
		}
		
		for (int i = 0; i < this.NbrCommunityCards; i++) {
			ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));

			HboxCommunityCards.getChildren().add(img);
		}

		ImageView imgBottomCard = new ImageView(
				new Image(getClass().getResourceAsStream("/res/img/b2fh.png"), 75, 75, true, true));
 
		HboxCommonArea.getChildren().add(imgBottomCard);
		
		//Draw for the number of cards being dealt according to the current game's rules
		for (int i = 0; i < NbrCards; i++) {
			handleDraw();
		}
		
		//Make an ArrayList for the hands to compare
		ArrayList<Hand> handsAsHands = new ArrayList<Hand>();
		
		for (GamePlayPlayerHand gpph : handsInPlay) {
			handsAsHands.add(gpph.getHand());
		}
		
		//Find the best hand of those in play
		Hand winningHand = Hand.PickBestHand(handsAsHands);
		
		//Establish the winning player
		Player winningPlayer = new Player("null", 0);
		
		//Match the winning hand to the winning player
		for (GamePlayPlayerHand gpph : handsInPlay) {
			if (gpph.getHand() == winningHand) {
				winningPlayer = gpph.getPlayer();
			}
		}
		
		//Print the winner to the console and the game itself
		String winner = winningPlayer.getPlayerName() + " wins!";
		System.out.println(winner);
		winnerAnnouncement.setText(winner);
		
	}

	private ParallelTransition createTransition(final ImageView iv, final ImageView ivStart, final Image img) {

		FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(.25), iv);
		fadeOutTransition.setFromValue(1.0);
		fadeOutTransition.setToValue(0.0);
		fadeOutTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				iv.setImage(img);
				;
			}

		});

		FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(.25), iv);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);

		/*
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), ivStart);

		translateTransition.setFromX(0);
		translateTransition.setToX(ivX - ivStartX1);
		translateTransition.setFromY(0);
		translateTransition.setToY(ivY - ivStartY1);

		translateTransition.setCycleCount(2);
		translateTransition.setAutoReverse(false);
		 */
		//		RotateTransition rotateTransition = new RotateTransition(Duration.millis(150), ivStart);
		//		rotateTransition.setByAngle(90f);
		//		rotateTransition.setCycleCount(1);
		//		rotateTransition.setAutoReverse(false);

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(fadeOutTransition, fadeInTransition);

		return parallelTransition;
	}
}