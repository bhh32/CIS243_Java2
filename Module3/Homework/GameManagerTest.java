import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit Tests for Robin Hood's GameManager
 *
 * *** not guaranteed to be complete
 *
 * Written against the specification; captures several cases not caught by
 * RobinHood, but may not be 100% complete, and cannot test for things like
 * code style, proper exception messages, comments, good names on variables
 * and methods, clean design...
 *
 * @author agarripoli
 * @version 2018-01-22 initial
 *
 */
public class GameManagerTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testGameManagerNull() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(matchesRegex("...*")); // at least 2 characters
		new GameManager(null);
	}

	@Test
	public void testGameManagerEmptyList() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(matchesRegex("...*")); // at least 2 characters
		new GameManager(Collections.emptyList());
	}

	@Test
	public void testGameManagerListOrder() {
		GameManager pm = makeGame3Players();

		checkThiefRing(pm,"    Zach will steal from Sarah" + System.lineSeparator()
            + "    Sarah will steal from Grace" + System.lineSeparator()
            + "    Grace will steal from Zach" + System.lineSeparator());
	}

	@Test
	public void testIsGameOverOnePlayer() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		GameManager pm = new GameManager(players);

		assertTrue(pm.isGameOver());
	}

	@Test
	public void testIsGameOverTwoPlayers() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		players.add("Sarah");
		GameManager pm = new GameManager(players);

		assertFalse(pm.isGameOver());
	}

	@Test
	public void testIsGameOverTwoPlayerssteal() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		players.add("Sarah");
		GameManager pm = new GameManager(players);
		pm.steal("Sarah");

		assertTrue(pm.isGameOver());
	}

	@Test
	public void testIsGameOverThreePlayerssteal() {
		GameManager pm = makeGame3Players();
		pm.steal("Zach");

		assertFalse(pm.isGameOver());
	}

	@Test
	public void testPrintThiefRingWinner() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		GameManager pm = new GameManager(players);

		checkThiefRing(pm,"    Zach is Robin Hood!" + System.lineSeparator());
	}

	@Test
	public void testPrintStolenListNoneYet() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		GameManager pm = new GameManager(players);

		// set up to capture System.out
		final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
		PrintStream oldOut = System.out;
		System.setOut(new PrintStream(testOut));

		pm.printStolenList(); // writes to System.out

		// pull the output, then reset System.out
		String stole = testOut.toString();
		System.setOut(oldOut); // restore

		assertEquals("", stole);
	}

	@Test
	public void testPrintStolenListThreeSteals() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		players.add("Tim");
		players.add("Sam");
		players.add("Pam");
		GameManager pm = new GameManager(players);
		pm.steal("Tim");
		pm.steal("Pam");
		pm.steal("Sam");

		// set up to capture System.out
		final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
		PrintStream oldOut = System.out;
		System.setOut(new PrintStream(testOut));

		pm.printStolenList(); // writes to System.out

		// pull the output, then reset System.out
		String stole = testOut.toString();
		System.setOut(oldOut); // restore

		// line separator is \r\n on Windows, \n on Linux
		assertEquals("    Sam was stolen from by Zach" + System.lineSeparator()
            + "    Pam was stolen from by Sam" + System.lineSeparator()
            + "    Tim was stolen from by Zach" + System.lineSeparator(), stole);
	}

	@Test
	public void testWinnerGameNotOver() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		players.add("Sarah");
		GameManager pm = new GameManager(players);

		assertNull(pm.winner());
	}

	@Test
	public void testWinnerOnePlayer() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		GameManager pm = new GameManager(players);

		assertEquals("Zach", pm.winner());
	}

	@Test
	public void testWinnerGameWon() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		players.add("Sarah");
		GameManager pm = new GameManager(players);
		pm.steal("Sarah");

		assertEquals("Zach", pm.winner());
	}

	@Test
	public void testThiefRingContainsThievedPlayer() {
		GameManager pm = makeGame3Players1Steal();

		assertFalse(pm.thiefRingContains("Sarah"));
	}

	@Test
	public void testThiefRingContainsPlayer() {
		GameManager pm = makeGame3Players1Steal();

		assertTrue(pm.thiefRingContains("zach"));
	}

	@Test
	public void testThiefRingContainsLastPlayer() {
		GameManager pm = makeGame3Players1Steal();

		assertTrue(pm.thiefRingContains("gRACE"));
	}

	@Test
	public void testThiefRingContainsNonPlayer() {
		GameManager pm = makeGame3Players1Steal();

		assertFalse(pm.thiefRingContains("Betty"));
	}

	@Test
	public void testThiefRingContainsNull() {
		GameManager pm = makeGame3Players1Steal();

		assertFalse(pm.thiefRingContains(null));
	}

	@Test
	public void testThiefRingContainsEmptyString() {
		GameManager pm = makeGame3Players1Steal();

		assertFalse(pm.thiefRingContains(""));
	}

	@Test
	public void testStolenListContainsThievedPlayer() {
		GameManager pm = makeGame3Players1Steal();

		assertTrue(pm.stolenListContains("SaRah"));
	}

	@Test
	public void testStolenListContainsStillPlaying() {
		GameManager pm = makeGame3Players1Steal();

		assertFalse(pm.stolenListContains("Zach"));
	}

	@Test
	public void testStolenListContainsNonPlayer() {
		GameManager pm = makeGame3Players1Steal();

		assertFalse(pm.stolenListContains("Betty"));
	}

	@Test
	public void testStolenListContainsNull() {
		GameManager pm = makeGame3Players1Steal();

		assertFalse(pm.stolenListContains(null));
	}

	@Test
	public void testStolenListContainsEmptyString() {
		GameManager pm = makeGame3Players1Steal();

		assertFalse(pm.stolenListContains(""));
	}

	@Test
	public void testGameOver() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		GameManager pm = new GameManager(players);

		thrown.expect(IllegalStateException.class);
		thrown.expectMessage(matchesRegex("...*")); // at least 2 characters

		pm.steal("Zach");
	}

	@Test
	public void testNonPlayer() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		players.add("Sarah");
		GameManager pm = new GameManager(players);

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(matchesRegex("...*")); // at least 2 characters

		pm.steal("Betty");
	}

	@Test
	public void testGameOverAndNonPlayer() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		GameManager pm = new GameManager(players);

		// two possible exceptions, either one is fine...
		String message = "";
		try {
			pm.steal("Betty");
		} catch (IllegalStateException ise) {
			message = ise.getMessage();
		} catch (IllegalArgumentException iae) {
			message = iae.getMessage();
		}
		assertTrue(message.length() > 1);
	}

	@Test
	public void testFirstPlayer() {
		GameManager pm = makeGame3Players();
		pm.steal("Zach");

		checkThiefRing(pm, "    Sarah is targeting Grace" + System.lineSeparator()
				+ "    Grace is targeting Sarah"	+ System.lineSeparator());
	}

	@Test
	public void testLastPlayer() {
		GameManager pm = makeGame3Players();
		pm.steal("Grace");

		checkThiefRing(pm, "    Zach is targeting Sarah" + System.lineSeparator()
				+ "    Sarah is targeting Zach"	+ System.lineSeparator());
	}

	@Test
	public void testMidPlayer() {
		GameManager pm = makeGame3Players();
		pm.steal("Sarah");

		checkThiefRing(pm, "    Zach is targeting Grace" + System.lineSeparator()
				+ "    Grace is targeting Zach"	+ System.lineSeparator());
	}

	// https://stackoverflow.com/questions/27781380/junit-expected-exception-message-regular-expression#27781794
	private Matcher<String> matchesRegex(final String regex) {
		return new TypeSafeMatcher<String>() {
			@Override
			protected boolean matchesSafely(final String item) {
				return item.matches(regex);
			}

			@Override
			public void describeTo(Description arg0) {
			}
		};
	}

	private GameManager makeGame3Players1Steal() {
		GameManager pm = makeGame3Players();
		pm.steal("Sarah");
		return pm;
	}

	private GameManager makeGame3Players() {
		List<String> players = new LinkedList<>();
		players.add("Zach");
		players.add("Sarah");
		players.add("Grace");
		GameManager pm = new GameManager(players);
		return pm;
	}

	private void checkThiefRing(GameManager pm, String expect) {
		// set up to capture System.out
		final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
		PrintStream oldOut = System.out;
		System.setOut(new PrintStream(testOut));

		pm.printThiefRing(); // writes to System.out

		// pull the output, then reset System.out
		final String thiefRing = testOut.toString();
		System.setOut(oldOut); // restore

		// line separator is \r\n on Windows, \n on Linux
		assertEquals(expect, thiefRing);
	}

}
