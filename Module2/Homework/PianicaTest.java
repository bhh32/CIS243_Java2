import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

/**
 * PianicaTest contains basic unit tests for Pianica.
 * It makes sure all keys exist, the expected constant is unchanged,
 * and all non-keys don't exist (i.e. they are not accepted).
 * It does some rudimentary checks on time, and on playKey/sample/tic for each key.
 *
 * Note a test failure that says "further investigation" may be a false failure due
 * to random values strangely aligning or implementation details different than
 * those typically seen. Check such results with a re-run. If they disappear,
 * blame the random value generator (and check your code to be sure...); if
 * they are due to your implementation choices, mention them in your report.
 *
 * This runs in the homework directory, it assumes a good Note implementation
 * that passed its tests is in use here as well.
 *
 * It does not check that exceptions have messages associated with them.
 *
 * @author agarripoli
 * @version 2018-01-15 initial version
 * @version 2018-01-16 fix playNote tests - no exception expected
 * @version 2019-01-14 fix playBadStrings test - may be any exception
 *
 */
public class PianicaTest {

	private static final double EPSILON = 1E-12;

	private static final String KEYS = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

	// if correctly calculated, these are the frequencies.
	// note, your code must calculate them, not use these provided values
	private static final double[] frequencies = { 110.0, 116.54094037952248, 123.47082531403103, 130.8127826502993,
			138.59131548843604, 146.8323839587038, 155.56349186104046, 164.81377845643496, 174.61411571650194,
			184.9972113558172, 195.99771799087463, 207.65234878997256, 220.0, 233.08188075904496, 246.94165062806206,
			261.6255653005986, 277.1826309768721, 293.6647679174076, 311.1269837220809, 329.6275569128699,
			349.2282314330039, 369.9944227116344, 391.99543598174927, 415.3046975799451, 440.0, 466.1637615180899,
			493.8833012561241, 523.2511306011972, 554.3652619537442, 587.3295358348151, 622.2539674441618,
			659.2551138257398, 698.4564628660078, 739.9888454232688, 783.9908719634985, 830.6093951598903, 880.0 };

	// note your code is expected to manipulate the KEYS string
	// I'm using a character array so I don't show you the solution.
	// don't use a character array for your solution.
	private static final char[] NOTES = { 'q', '2', 'w', 'e', '4', 'r', '5', 't', 'y', '7', 'u', '8', 'i', '9', 'o',
			'p', '-', '[', '=', 'z', 'x', 'd', 'c', 'f', 'v', 'g', 'b', 'n', 'j', 'm', 'k', ',', '.', ';', '/', '\'',
			' ' };
	private static final char[] NOTES_SORTED; // for fast lookup
	static {
		// can't alter a constant, so use a temporary variable...
		char[] temp = Arrays.copyOf(NOTES, NOTES.length);
		Arrays.sort(temp);
		NOTES_SORTED = temp;
	}

	@Test
	public void testKeyboard() {
		assertEquals("Keyboard must not be changed", KEYS, Pianica.KEYS);
	}

	@Test
	public void testEachNote() {
		Pianica pianica = new Pianica();

		// cycle through all of them, want no exceptions
		for (int note = -24; note <= 12; note++) {
			pianica.playNote(note);
		}
		// success is really the lack of an exception being thrown
		assertNotNull(pianica); // pianica still there...
	}

	// need to check it had an informative message
	@Test
	public void testBadNoteLow() {
		Pianica pianica = new Pianica();

		// bad pitch will not change sample since nothing plucked.
		double sample = pianica.sample();
		pianica.playNote(13);

		assertEquals(sample,pianica.sample(),EPSILON);
	}

	// need to check it had an informative message
	@Test
	public void testBadNoteHigh() {
		Pianica pianica = new Pianica();

		// bad pitch will not change sample since nothing plucked.
		double sample = pianica.sample();
		pianica.playNote(13);

		assertEquals(sample,pianica.sample(),EPSILON);
	}

	@Test
	public void testEachKey() {
		// capture all failures, if any, then report
		String result = "";
		Pianica pianica = new Pianica();

		for (char key : NOTES) {
			if (!pianica.hasKey(key)) {
				result += key;
			}
		}
		assertEquals("Failed to find these expected keys: " + result, 0, result.length());
	}

	@Test
	public void testHasStringsBadKeys() {
		// capture all failures, if any, then report
		String result = "";
		Pianica pianica = new Pianica();

		// ascii is 0-127.
		// anything not in strings is bad. Run through whole ascii set...
		for (char c = 0; c < 128; c++) {
			if (!inStrings(c)) {
				if (pianica.hasKey(c)) {
					result+=", "+c;
				}
			}
		}
		assertEquals("Found these unexpected keys" + result, 0, result.length());
	}

	@Test
	public void testPlayBadKeys() {
		// capture all failures, if any, then report
		String result = "";
		Pianica pianica = new Pianica();

		// ascii is 0-127.
		// anything not in strings is bad. Run through whole ascii set...
		for (char c = 0; c < 128; c++) {
			boolean caught = false;
			if (!inStrings(c)) {
				try {
					pianica.playKey(c);
				} catch (Exception e) {
					caught = true;
				} finally {
					if (!caught) {
						result += "," + c;
					}
				}
			}
		}
		assertEquals("Bad keys did not cause exceptions: " + result+"; further investigation required", 0, result.length());
	}

	private boolean inStrings(char letter) {
		return Arrays.binarySearch(NOTES_SORTED, letter) >= 0;
	}

	@Test
	public void testTimeBeforeTic() {
		Pianica pianica = new Pianica();

		assertEquals(0, pianica.time());
	}

	@Test
	public void testTimeAfterOneTic() {
		Pianica pianica = new Pianica();
		pianica.tic();

		assertEquals(1, pianica.time());
	}

	@Test
	public void testTimeAfterTenTics() {
		Pianica pianica = new Pianica();
		for (int i = 0; i < 10; i++) {
			pianica.tic();
		}

		assertEquals(10, pianica.time());
	}

	@Test
	public void testFirstSample() {
		Pianica pianica = new Pianica();

		// no plucks yet so all 0 still
		assertEquals(0.0, pianica.sample(), EPSILON);
	}

	@Test
	public void testPlaySampleTicAllGoodNotes() {
		// capture all failures, if any, then report
		String result = "";

		for (char key : NOTES) {
			//new pianica each time so each key seen on its own
			Pianica pianica = new Pianica();
			pianica.playKey(key);
			double sample1 = pianica.sample();
			pianica.tic();
			double sample2 = pianica.sample();
			if (Math.abs(sample1 - sample2) < EPSILON) {
				result += key;
			}
		}
		assertEquals("Sample unchanged on these keys requires further investigation: " + result, 0, result.length());
	}
}
